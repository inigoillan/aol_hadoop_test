package com.aol.logprocessor.printer;

import com.aol.logprocessor.aggregations.AggregationFields;
import com.aol.logprocessor.aggregations.Counters;

import javax.annotation.Nonnull;

/**
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public interface Printer {
    @Nonnull
    String print(@Nonnull AggregationFields aggregationFields, @Nonnull Counters counterFields);
}
