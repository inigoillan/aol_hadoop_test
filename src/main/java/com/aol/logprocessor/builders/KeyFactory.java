package com.aol.logprocessor.builders;

import com.aol.logprocessor.aggregations.AggregationFields;

import javax.annotation.Nonnull;

/**
 * Factory that creates a new instance of a {link AggregationFields} type
 *
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public interface KeyFactory<T extends AggregationFields> {
    /**
     * Creates a new {@link AggregationFields} instance
     *
     * @param aggregationFields
     * @return
     */
    @Nonnull
    T getAggregationFields(String[] aggregationFields);
}
