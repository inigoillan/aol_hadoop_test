package com.aol.logprocessor.georesolver;

import com.aol.logprocessor.parser.address.IPAddress;
import com.google.common.base.Throwables;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.Inet4Address;
import java.net.UnknownHostException;

import static org.junit.Assert.assertEquals;


public class GeoIPResolverTest {


    //region ResolveLocation tests

    // IP Addresses are taken from:
    // http://www.nirsoft.net/countryip/gb.html
    @Test
    public void ResolveLocation_GivenGBIP_ReturnsUK() throws ResolvingException {
        // Arrange
        IPAddress address = getInetAddress("2.24.1.1");
        GeoIPResolver resolver = new GeoIPResolver(getDatabaseInputStream());

        // Act
        GeoLocation country = resolver.resolveLocation(address);

        // Assert
        GeoLocation expectedLocation = buildLocation("GB");
        assertEquals(expectedLocation, country);
    }

    @Test(expected = ResolvingException.class)
    public void ResolveLocation_GivenInvalidIP_ThrowsResolvingException() throws ResolvingException {
        // Arrange
        IPAddress address = getInetAddress("192.168.0.1");
        GeoIPResolver resolver = new GeoIPResolver(getDatabaseInputStream());

        // Act
        resolver.resolveLocation(address);
    }

    //endregion


    //region Helper methods

    private GeoLocation buildLocation(String countryCode) {
        return new GeoLocation(countryCode);
    }

    private IPAddress getInetAddress(String ip) {
        try {
            return new IPAddress(Inet4Address.getByName(ip));
        } catch (UnknownHostException e) {
            Throwables.propagate(e);
        }

        // Just to make the compiler happy
        return null;
    }

    private InputStream getDatabaseInputStream() {
        try {
            return new FileInputStream("./GeoLite2-City.mmdb");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //endregion
}