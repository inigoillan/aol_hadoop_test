package com.aol.logprocessor.georesolver;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public class GeoLocationTest {

    //region Equals tests

    @Test
    public void Equals_SameObjects_ReturnsTrue() {
        // Arrange
        GeoLocation location = new GeoLocation("GB");

        // Act
        boolean equals = location.equals(location);

        // Assert
        assertEquals(true, equals);
    }

    @Test
    public void Equals_SameCountryCode_ReturnsTrue() {
        // Arrange
        GeoLocation location1 = new GeoLocation("GB");
        GeoLocation location2 = new GeoLocation("GB");

        // Act
        boolean equals = location1.equals(location2);

        // Assert
        assertEquals(true, equals);
    }

    @Test
    public void Equals_NullLocation_ReturnsFalse() {
        // Arrange
        GeoLocation location1 = new GeoLocation("GB");

        // Act
        boolean equals = location1.equals(null);

        // Assert
        assertEquals(false, equals);
    }

    @Test
    public void Equals_AgainstObject_ReturnsFalse() {
        // Arrange
        GeoLocation location1 = new GeoLocation("GB");
        Object obj = new Object();

        // Act
        boolean equals = location1.equals(obj);

        // Assert
        assertEquals(false, equals);
    }

    //endregion

}