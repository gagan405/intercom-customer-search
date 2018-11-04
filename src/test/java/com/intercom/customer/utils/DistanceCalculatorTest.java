package com.intercom.customer.utils;

import com.intercom.customer.model.GeoCoordinates;

import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;
import org.junit.Test;

public class DistanceCalculatorTest {

    @Test
    public void testGetHaversineDistanceSameLocation() {
        double distance = DistanceCalculator.getHaversineDistance(
            new GeoCoordinates(Double.valueOf("52.5200"), Double.valueOf("13.4050")),
            new GeoCoordinates(Double.valueOf("52.5200"), Double.valueOf("13.4050"))
        );

        Assertions.assertThat(distance).isZero();
    }

    @Test
    public void testGetHaversineDistanceDifferentLocations() {
        double distance = DistanceCalculator.getHaversineDistance(
            new GeoCoordinates(Double.valueOf("52.5200"), Double.valueOf("13.4050")),
            new GeoCoordinates(Double.valueOf("53.5511"), Double.valueOf("9.9937"))
        );

        Assertions.assertThat(distance).isCloseTo(255.3, Offset.offset(0.05));
    }
}