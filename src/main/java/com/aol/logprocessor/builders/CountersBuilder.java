package com.aol.logprocessor.builders;

import com.aol.logprocessor.aggregations.Counters;

import javax.annotation.Nonnull;

/**
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public interface CountersBuilder<T extends Counters> {
    @Nonnull
    T buildCounters(@Nonnull String[] fields);
}
