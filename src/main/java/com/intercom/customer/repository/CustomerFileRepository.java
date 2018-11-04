package com.intercom.customer.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intercom.customer.model.Customer;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomerFileRepository implements CustomerRepository {

    private final Path source;
    private final ObjectMapper objectMapper;

    public CustomerFileRepository(final @NonNull String path,
                                  final @NonNull ObjectMapper objectMapper) {
        Path filePath = Paths.get(path);
        if(!filePath.toFile().exists()) {
            throw new InvalidFilePathException("Invalid path for input file");
        }

        this.source = filePath;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<Customer> getAllCustomers() {
        try {
            List<String> lines = Files.readAllLines(source);
            return lines.stream()
                .map(this::extractCustomerDetails)
                .collect(Collectors.toList());

        } catch (IOException e) {
            throw new FileParseException("Failed to parse input file.", e);
        }
    }

    private Customer extractCustomerDetails(String line) {
        try {
            return objectMapper.readValue(line, Customer.class);
        } catch (IOException e) {
            throw new FileParseException("Cannot parse input file: Invalid Json.", e);
        }
    }
}
