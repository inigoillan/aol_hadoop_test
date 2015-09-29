package com.aol.logprocessor.georesolver;

import com.aol.logprocessor.parser.address.IPAddress;
import com.google.common.base.Throwables;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

/**
 * {@link GeoResolver} class that uses the GeoIP database
 *
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public class GeoIPResolver implements GeoResolver<IPAddress> {

    private final DatabaseReader dbReader;

    public GeoIPResolver() {
        this(new File("./GeoLite2-City.mmdb"));
    }

    public GeoIPResolver(File database) {
        DatabaseReader.Builder builder = new DatabaseReader.Builder(database);
        DatabaseReader reader = null;

        try {
            reader = builder.build();
        } catch (IOException e) {
            Throwables.propagate(e);
        }

        this.dbReader = reader;
    }

    @Nonnull
    public GeoLocation resolveLocation(@Nonnull IPAddress address) throws ResolvingException {
        CityResponse city = getCity(address.get());

        return buildGeoLocation(city);
    }

    @Nonnull
    private CityResponse getCity(@Nonnull InetAddress address) throws ResolvingException {
        CityResponse city;
        try {
            city = dbReader.city(address);
        } catch (Exception e) {
            throw new ResolvingException();
        }

        return city;
    }

    private GeoLocation buildGeoLocation(CityResponse city) {
        String countryCode = city.getCountry().getIsoCode();

        return new GeoLocation(countryCode);
    }

}
