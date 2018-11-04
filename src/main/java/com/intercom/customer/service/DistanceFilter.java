package com.intercom.customer.service;

import com.intercom.customer.model.Customer;
import com.intercom.customer.model.GeoCoordinates;

import lombok.NonNull;
import lombok.Value;

import static com.intercom.customer.utils.DistanceCalculator.getHaversineDistance;

public class DistanceFilter implements CustomerFilter {

    private final FilterParams criteria;

    public DistanceFilter(final @NonNull FilterParams filterParams) {
        this.criteria = filterParams;
    }

    @Override
    public boolean filter(final @NonNull Customer customer) {
        return getHaversineDistance(customer.getCoordinates(), criteria.sourceCoordinates) <= criteria.maxDistanceInKms;
    }

    @Value
    public static class FilterParams {
        private GeoCoordinates sourceCoordinates;
        private double maxDistanceInKms;
    }
}
