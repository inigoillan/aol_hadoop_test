package com.aol.logprocessor.parser;

/**
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public interface AddressParser<T extends Address> {
    /**
     * Parses an address. Note that if {@link #canParse(String)} returns true, then this method should never fail
     *
     * @param address
     * @return
     */
    T parse(String address);

    /**
     * Checks whether this parser can parse the given address
     *
     * @param address
     * @return
     */
    boolean canParse(String address);
}
