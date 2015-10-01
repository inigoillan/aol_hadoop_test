package com.aol.logprocessor.builders;

import com.aol.logprocessor.aggregations.AggregationFields;

import javax.annotation.Nonnull;

/**
 * Builds a new {@link AggregationFields} with the given information
 *
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public interface KeyBuilder<T extends AggregationFields> {
    /**
     * Builds a new {@link AggregationFields} with the given information
     *
     * @param fields
     * @return
     */
    @Nonnull
    T buildKey(String[] fields);
}
