package com.aol.logprocessor.georesolver;

import javax.annotation.Nonnull;

/**
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public interface GeoIPResolverConfig {
    @Nonnull
    String getGeoIPDatabaseFilename();
}
