package com.aol.logprocessor.parser.address;

import com.aol.logprocessor.parser.ParsingException;

/**
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public interface AddressParser<T extends Address> {
    /**
     * Parses an address. Note that if {@link #canParse(String)} returns true, then this method should never fail,
     * so do check first if this parser is able to deal with the address.
     *
     * @param address
     * @return
     * @throws ParsingException
     */
    T parse(String address) throws ParsingException;

    /**
     * Checks whether this parser can parse the given address
     *
     * @param address
     * @return
     */
    boolean canParse(String address);
}
