package com.intercom.customer.model;

import lombok.NonNull;
import lombok.Value;

@Value
public class GeoCoordinates {
    @NonNull
    private Double latitude;
    @NonNull
    private Double longitude;
}
