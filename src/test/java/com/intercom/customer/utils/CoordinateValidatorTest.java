package com.intercom.customer.utils;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class CoordinateValidatorTest {

    @Test
    public void validateLongitude() {
        Assertions.assertThatThrownBy(() -> CoordinateValidator.validateLongitude(190.0))
            .isInstanceOf(InvalidParameterException.class);

        Assertions.assertThatThrownBy(() -> CoordinateValidator.validateLongitude(-190.0))
            .isInstanceOf(InvalidParameterException.class);

        // Should throw no exception when longitude is in valid range
        CoordinateValidator.validateLongitude(-170.0);
    }

    @Test
    public void validateLatitude() {
        Assertions.assertThatThrownBy(() -> CoordinateValidator.validateLatitude(91.0))
            .isInstanceOf(InvalidParameterException.class);

        Assertions.assertThatThrownBy(() -> CoordinateValidator.validateLatitude(-91.0))
            .isInstanceOf(InvalidParameterException.class);

        // Should throw no exception when latitude is in valid range
        CoordinateValidator.validateLatitude(-70.0);
    }
}