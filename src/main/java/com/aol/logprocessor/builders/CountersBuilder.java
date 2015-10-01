package com.aol.logprocessor.builders;

import com.aol.logprocessor.aggregations.Counters;

import javax.annotation.Nonnull;

/**
 * Convenient class for building a {@link Counters} instance for a given record
 *
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public interface CountersBuilder<T extends Counters> {
    /**
     * Builds a new {@link Counters} instance an initialises with the information given as parameter
     *
     * @param fields
     * @return
     */
    @Nonnull
    T buildCounters(@Nonnull String[] fields);

    /**
     * Builds a new {@link Counters} instance with counters initialized to 0
     *
     * @return
     */
    @Nonnull
    T buildZeroCounters();
}
