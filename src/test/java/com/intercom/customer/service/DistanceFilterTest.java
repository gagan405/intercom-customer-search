package com.intercom.customer.service;

import com.intercom.customer.service.DistanceFilter.FilterParams;
import com.intercom.customer.model.Customer;
import com.intercom.customer.model.GeoCoordinates;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class DistanceFilterTest {

    private GeoCoordinates berlin = new GeoCoordinates(Double.valueOf("52.5200"), Double.valueOf("13.4050"));


    FilterParams params = new FilterParams(berlin, 256);
    DistanceFilter distanceFilter = new DistanceFilter(params);

    @Test
    public void testDistanceFilterWhenCustomerIsNearBy() {
        Customer customerFromHamburg = new Customer();
        customerFromHamburg.setLongitude("9.9937");
        customerFromHamburg.setLatitude("53.5511");
        Assertions.assertThat(distanceFilter.filter(customerFromHamburg)).isTrue();
    }

    @Test
    public void testDistanceFilterWhenCustomerIsFarAway() {
        Customer customerFromLondon = new Customer();
        customerFromLondon.setLongitude("-0.1278");
        customerFromLondon.setLatitude("51.5074");
        Assertions.assertThat(distanceFilter.filter(customerFromLondon)).isFalse();
    }

}