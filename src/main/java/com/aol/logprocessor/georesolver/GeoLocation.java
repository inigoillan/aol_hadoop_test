package com.aol.logprocessor.georesolver;

import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public class GeoLocation {
    private final String countryCode;

    public GeoLocation(@Nonnull String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof GeoLocation))
            return false;

        if (this == o)
            return true;

        GeoLocation other = (GeoLocation) o;

        return this.getCountryCode().equals(other.getCountryCode());
    }
}
