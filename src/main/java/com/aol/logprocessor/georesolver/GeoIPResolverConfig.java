package com.aol.logprocessor.georesolver;

import javax.annotation.Nonnull;

/**
 * Configuration class meant for use with {@link GeoIPResolver} class
 *
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public interface GeoIPResolverConfig {
    /**
     * Get's the filename for the GeoIP database
     *
     * @return
     */
    @Nonnull
    String getGeoIPDatabaseFilename();
}
