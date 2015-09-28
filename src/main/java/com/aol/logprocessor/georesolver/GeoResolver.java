package com.aol.logprocessor.georesolver;

import com.aol.logprocessor.parser.Address;

import javax.annotation.Nonnull;
import java.net.InetAddress;

/**
 * Geo location resolver class
 *
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public interface GeoResolver<T extends Address> {
    /**
     * Resolves an {@link Address} to a {@link GeoLocation}
     *
     * @param address Valid address you want to resolve
     * @return
     * @throws ResolvingException The exception is thrown in case the class couldn't resolve the ip to a (@link GeoLocation)
     */
    @Nonnull
    GeoLocation resolveLocation(@Nonnull T address) throws ResolvingException;
}
