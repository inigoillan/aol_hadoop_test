package com.aol.logprocessor.aggregations;

import javax.annotation.Nonnull;

/**
 * Fields used for the final aggregation
 *
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public interface AggregationFields {
    @Nonnull
    String[] getFields();
}
