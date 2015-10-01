package com.aol.logprocessor.builders;

/**
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */

import com.aol.logprocessor.aggregations.Counters;

import javax.annotation.Nonnull;

public interface CounterFactory<T extends Counters> {
    @Nonnull
    T getAggregationCounters();
}
