package com.aol.logprocessor.builders;

import com.aol.logprocessor.aggregations.Counters;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/**
 * Factory that produces a {@link Counters} instance
 *
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public interface CounterFactory<T extends Counters> {

    /**
     * Creates a new {@link Counters} instance
     *
     * @param numberOfCounters
     * @return
     */
    @Nonnull
    T getAggregationCounters(@Nonnegative int numberOfCounters);
}
