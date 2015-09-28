package com.aol.logprocessor.parser;

import com.google.api.client.repackaged.com.google.common.base.Throwables;
import org.junit.Test;

import java.net.Inet4Address;
import java.net.InetAddress;

import static org.junit.Assert.*;

/**
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public class IP4AddressParserTest {

    @Test
    public void Parse_GivenValidIP_ReturnAddress() {
        // Arrange
        IP4AddressParser parser = new IP4AddressParser();
        String ipAddress = "192.168.0.1";

        // Act
        IPAddress address = parser.parse(ipAddress);

        // Assert
        IPAddress expectedAddress = new IPAddress(buildInetAddress(ipAddress));
        assertEquals(expectedAddress, address);
    }

    @Test(expected = ParsingException.class)
    public void Parse_GivenInvalidIP_ThrowParsingException() {
        // Arrange
        IP4AddressParser parser = new IP4AddressParser();
        String ipAddress = "INVALID_IP";

        // Act
        IPAddress address = parser.parse(ipAddress);
    }

    private InetAddress buildInetAddress(String ipAddress) {
        try {
            return Inet4Address.getByName(ipAddress);
        } catch (Exception e) {
            Throwables.propagate(e);
        }

        return null;
    }

}