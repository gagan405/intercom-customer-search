package com.intercom.customer.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import static com.intercom.customer.utils.CoordinateValidator.validateLatitude;
import static com.intercom.customer.utils.CoordinateValidator.validateLongitude;

@Getter
@Setter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {
    private Integer userId;
    private String name;
    private Double longitude;
    private Double latitude;

    @JsonCreator
    public Customer(
        @JsonProperty(value = "user_id", required = true) @NonNull Integer userId,
        @JsonProperty(value = "name", required = true) @NonNull String name,
        @JsonProperty(value = "longitude", required = true) @NonNull String longitude,
        @JsonProperty(value = "latitude", required = true) @NonNull String latitude
    ) {
        this.userId = userId;
        this.name = name;
        setLatitude(latitude);
        setLongitude(longitude);
    }

    public void setLongitude(@NonNull String longitude) {
        Double lon = Double.valueOf(longitude);
        validateLongitude(lon);
        this.longitude = lon;
    }


    public void setLatitude(@NonNull String latitude) {
        Double lat = Double.valueOf(latitude);
        validateLatitude(lat);
        this.latitude = lat;
    }

    @JsonIgnore
    public GeoCoordinates getCoordinates() {
        return new GeoCoordinates(latitude, longitude);
    }
}
