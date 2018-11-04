package com.intercom.customer.utils;

public class CoordinateValidator {

    private CoordinateValidator() {}

    public static void validateLongitude(Double lon) {
        if ((lon.compareTo(-180.0) < 0) || (lon.compareTo(180.0) > 0)) {
            throw new InvalidParameterException("Invalid longitude value. Should be value in range : (-180, 180)");
        }
    }

    public static void validateLatitude(Double lat) {
        if ((lat.compareTo(-90.0) < 0) || (lat.compareTo(90.0) > 0)) {
            throw new InvalidParameterException("Invalid latitude value. Should be value in range : (-90, 90)");
        }
    }
}
