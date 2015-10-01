package com.aol.logprocessor.aggregations;

import javax.annotation.Nonnull;

/**
 * This class represents a list of counters
 *
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public interface Counters {
    /**
     * Increases by one the count in the given bucket
     *
     * @param bucket
     */
    void increase(int bucket);

    /**
     * Adds the specified count into the given bucket
     *
     * @param bucket
     * @param count
     */
    void add(int bucket, long count);

    @Nonnull
    long[] getCounters();
}
