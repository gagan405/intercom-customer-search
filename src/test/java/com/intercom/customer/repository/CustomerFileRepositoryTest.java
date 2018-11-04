package com.intercom.customer.repository;

import java.nio.file.Paths;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intercom.customer.model.Customer;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CustomerFileRepositoryTest {

    private static final String TEST_FILE = "customers.txt";
    private static final String INVALID_FILE = "invalid_customers.txt";
    private static final String INVALID_COORDINATES_FILE = "invalid_coordinates.txt";

    @Test
    public void given_validTextFile__when_parsed__then_customersAreReadCorrectly() {
        String resources = Paths.get(ClassLoader.getSystemResource(TEST_FILE).getFile()).toString();
        CustomerFileRepository customerFileRepository = new CustomerFileRepository(resources, new ObjectMapper());
        List<Customer> customers = customerFileRepository.getAllCustomers();
        assertThat(customers).isNotEmpty();
    }

    @Test
    public void given_nonExistingTextFile__when_parsed__then_throwExceptiom() {
        String nonExistingFile = Paths.get(ClassLoader.getSystemResource(TEST_FILE).getFile()).toString() + "/does/not/exist";
        assertThatThrownBy(() -> new CustomerFileRepository(nonExistingFile, new ObjectMapper()))
            .isInstanceOf(InvalidFilePathException.class);
    }

    @Test
    public void given_invalidTextFile__when_parsed__then_throwExceptiom() {
        String invalidFile = Paths.get(ClassLoader.getSystemResource(INVALID_FILE).getFile()).toString();
        CustomerFileRepository customerFileRepository = new CustomerFileRepository(invalidFile, new ObjectMapper());
        assertThatThrownBy(customerFileRepository::getAllCustomers)
            .isInstanceOf(FileParseException.class);
    }

    @Test
    public void given_invalidCoordinatesFile__when_parsed__then_throwExceptiom() {
        String invalidFile = Paths.get(ClassLoader.getSystemResource(INVALID_COORDINATES_FILE).getFile()).toString();
        CustomerFileRepository customerFileRepository = new CustomerFileRepository(invalidFile, new ObjectMapper());
        assertThatThrownBy(customerFileRepository::getAllCustomers)
            .isInstanceOf(FileParseException.class);
    }

}
