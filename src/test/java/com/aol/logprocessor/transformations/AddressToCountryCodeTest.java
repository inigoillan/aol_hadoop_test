package com.aol.logprocessor.transformations;

import com.aol.logprocessor.georesolver.GeoLocation;
import com.aol.logprocessor.georesolver.GeoResolver;
import com.aol.logprocessor.georesolver.ResolvingException;
import com.aol.logprocessor.parser.ParsingException;
import com.aol.logprocessor.parser.address.Address;
import com.aol.logprocessor.parser.address.AddressParser;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public class AddressToCountryCodeTest {
    @Test
    public void Transform_GivenValue_ReturnGB() throws ResolvingException, TransformationException {
        // Arrange
        String countryCode = "GB";

        AddressParser<Address> parser = buildAddressParser();
        GeoResolver<Address> resolver = buildAddressGeoResolver(countryCode);
        AddressToCountryCodeConfig config = getAddressToCountryCodeConfig(0);

        AddressToCountryCode addressToCountryCode =  new AddressToCountryCode(resolver, parser, config);

        // Act
        String[] input = new String[] {"0"};
        String[] output = addressToCountryCode.transform(input);

        // Assert
        String[] expected = new String[] {"GB"};
        assertArrayEquals(expected, output);
    }

    @Test(expected = TransformationException.class)
    public void Transform_GivenUnparseableEntry_ThrowException() throws ResolvingException, TransformationException {
        // Arrange
        String countryCode = "GB";

        AddressParser<Address> parser = buildAddressParser();
        GeoResolver<Address> resolver = buildAddressGeoResolver(countryCode);
        AddressToCountryCodeConfig config = getAddressToCountryCodeConfig(0);

        when(parser.parse(anyString())).thenThrow(ParsingException.class);

        AddressToCountryCode addressToCountryCode =  new AddressToCountryCode(resolver, parser, config);

        // Act
        String[] input = new String[] {"0"};
        addressToCountryCode.transform(input);
    }


    private AddressParser buildAddressParser() {
        return mock(AddressParser.class, RETURNS_DEEP_STUBS);
    }

    private GeoResolver<Address> buildAddressGeoResolver(String countryCode) throws ResolvingException {
        GeoResolver<Address> resolver = mock(GeoResolver.class);
        when(resolver.resolveLocation(any(Address.class))).thenReturn(new GeoLocation(countryCode));

        return resolver;
    }

    private AddressToCountryCodeConfig getAddressToCountryCodeConfig(int addressField) {
        AddressToCountryCodeConfig config = mock(AddressToCountryCodeConfig.class);
        when(config.getAddressField()).thenReturn(addressField);
        return config;
    }

}