package com.aol.logprocessor.transformations;

/**
 * Configuration meant to be used along with {@link AddressToCountryCode} class
 *
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public interface AddressToCountryCodeConfig {
    /**
     * Get's the field to use for transforming an {@link com.aol.logprocessor.parser.address.Address}
     *
     * @return
     */
    int getAddressField();
}
