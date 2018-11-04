package com.intercom.customer.utils;

import com.intercom.customer.model.GeoCoordinates;

import lombok.NonNull;

public class DistanceCalculator {
    private static final int RADIUS_OF_EARTH_IN_KMS = 6371;

    private DistanceCalculator() {}

    public static double getHaversineDistance(final @NonNull GeoCoordinates coordinatesX,
                                              final @NonNull GeoCoordinates coordinatesY) {
        double latDiff = Math.toRadians(coordinatesY.getLatitude() - coordinatesX.getLatitude());
        double lonDiff = Math.toRadians(coordinatesY.getLongitude() - coordinatesX.getLongitude());

        double a = Math.pow(Math.sin(latDiff / 2), 2)
            + Math.cos(Math.toRadians(coordinatesX.getLatitude())) * Math.cos(Math.toRadians(coordinatesY.getLatitude()))
            * Math.pow(Math.sin(lonDiff / 2), 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return RADIUS_OF_EARTH_IN_KMS * c;
    }
}
