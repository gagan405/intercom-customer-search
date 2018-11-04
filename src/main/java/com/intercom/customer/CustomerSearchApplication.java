package com.intercom.customer;

import java.util.Collections;
import java.util.List;

import com.beust.jcommander.IValueValidator;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intercom.customer.service.DistanceFilter;
import com.intercom.customer.service.DistanceFilter.FilterParams;
import com.intercom.customer.dto.Sort;
import com.intercom.customer.dto.Sort.SortField;
import com.intercom.customer.dto.Sort.SortOrder;
import com.intercom.customer.dto.CustomerSearchResponse;
import com.intercom.customer.model.GeoCoordinates;
import com.intercom.customer.repository.CustomerFileRepository;
import com.intercom.customer.repository.CustomerRepository;
import com.intercom.customer.repository.FileParseException;
import com.intercom.customer.repository.InvalidFilePathException;
import com.intercom.customer.service.CustomerService;
import com.intercom.customer.utils.CoordinateValidator;
import com.intercom.customer.utils.InvalidParameterException;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomerSearchApplication {

    @Parameter(names = {"--inputFilePath", "-i"},
        required = true,
        description = "Absolute path to input file with customer details")
    @Getter
    @Setter
    private String inputFile;

    @Parameter(names = {"--source-latitude"},
        description = "Latitude of the geo location from where distance is to be calculated. Default corresponds to Dublin.",
        validateValueWith = LatitudeValidator.class
    )
    @Getter
    @Setter
    private Double latitude = 53.339428;

    @Parameter(names = {"--source-longitude"},
        description = "Longitude of the geo location from where distance is to be calculated. Default corresponds to Dublin.",
        validateValueWith = LongitudeValidator.class
    )
    @Getter
    @Setter
    private Double longitude = -6.257664;

    @Parameter(names = {"--max-distance-kms"},
        description = "Maximum distance within which we search."
    )
    @Getter
    @Setter
    private double maxDistance = 100.0;

    @Parameter(names = {"--sort-field"},
        description = "Name of the field to sort."
    )
    @Getter
    @Setter
    private SortField sortField = SortField.USER_ID;

    @Parameter(names = {"--sort-order"},
        description = "Order of search results."
    )
    @Getter
    @Setter
    private SortOrder sortOrder = SortOrder.ASC;

    @Parameter(names = "--help", help = true)
    private boolean help = false;

    public static void main(String... args) {
        CustomerSearchApplication application = new CustomerSearchApplication();

        JCommander commander = JCommander.newBuilder()
            .addObject(application)
            .build();

        try {
            commander.parse(args);
        } catch (ParameterException e) {
            System.out.println("Missing or invalid parameters. use '--help' to see all available parameters.");
            System.out.println(e.getLocalizedMessage());
            System.exit(-1);
        }

        if (application.help) {
            commander.usage();
            return;
        }

        List<CustomerSearchResponse> responses = application.doSearch();
        System.out.println("Found " + responses.size() + " customers with the given criteria.");
        if (!responses.isEmpty()) {
            System.out.println("==============================================");
            responses.forEach(System.out::println);
        }
    }

    private List<CustomerSearchResponse> doSearch() {
        try {
            CustomerRepository repository = new CustomerFileRepository(inputFile, new ObjectMapper());
            CustomerService service = new CustomerService(repository);

            GeoCoordinates coordinates = new GeoCoordinates(latitude, longitude);
            DistanceFilter distanceFilter = new DistanceFilter(new FilterParams(coordinates, maxDistance));

            log.info("Doing the search");

            return service.findCustomers(distanceFilter, new Sort(sortField, sortOrder));

        } catch (InvalidFilePathException e) {
            System.out.println("ERROR: Incorrect input file path. No such file exists.");
            System.exit(1);
        } catch (FileParseException e) {
            System.out.println("ERROR: Invalid file contents. Cannot parse input file.");
            System.exit(2);
        }
        return Collections.emptyList();
    }

    /**
     * The below classes are wrappers around {@link CoordinateValidator}
     * as Jcommander needs IValueValidator interface to be implemented for validations.
     */

    public static class LatitudeValidator implements IValueValidator<Double> {
        @Override
        public void validate(String name, Double value) {
            try {
                CoordinateValidator.validateLatitude(value);
            } catch (InvalidParameterException e) {
                throw new ParameterException(e.getLocalizedMessage());
            }
        }
    }

    public static class LongitudeValidator implements IValueValidator<Double> {
        @Override
        public void validate(String name, Double value) {
            try {
                CoordinateValidator.validateLongitude(value);
            } catch (InvalidParameterException e) {
                throw new ParameterException(e.getLocalizedMessage());
            }
        }
    }
}
