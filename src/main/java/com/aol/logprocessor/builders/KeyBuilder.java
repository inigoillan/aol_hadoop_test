package com.aol.logprocessor.builders;

import com.aol.logprocessor.aggregations.AggregationFields;

import javax.annotation.Nonnull;

/**
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public interface KeyBuilder<T extends AggregationFields> {
    @Nonnull
    T buildKey(String[] fields);
}
