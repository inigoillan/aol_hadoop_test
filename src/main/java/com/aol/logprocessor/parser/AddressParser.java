package com.aol.logprocessor.parser;

/**
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public interface AddressParser<T extends Address> {
    T parse(String address);

    boolean canParse(String address);
}
