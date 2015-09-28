package com.aol.logprocessor.georesolver;

import com.google.common.base.Objects;

import javax.annotation.Nonnull;

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

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getCountryCode());
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this.getClass())
                .add("Country code", this.getCountryCode())
                .toString();
    }
}
