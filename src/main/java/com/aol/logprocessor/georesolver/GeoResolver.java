package com.aol.logprocessor.georesolver;

import javax.annotation.Nonnull;
import java.net.InetAddress;

/**
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public interface GeoResolver {
    @Nonnull
    GeoLocation resolveLocation(@Nonnull InetAddress address) throws ResolvingException;
}
