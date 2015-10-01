package com.aol.logprocessor.hadoop.datatypes;

import com.aol.logprocessor.aggregations.AggregationFields;
import com.aol.logprocessor.aggregations.Counters;
import com.aol.logprocessor.builders.CounterFactory;
import com.aol.logprocessor.builders.KeyFactory;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/**
 * Factory that generates {@link AggregationKey} and {@link CountAggregation} instances
 *
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public class KeyValueFactory implements KeyFactory, CounterFactory {
    @Nonnull
    @Override
    public Counters getAggregationCounters(@Nonnegative int numberOfCounters) {
        return new CountAggregation(numberOfCounters);
    }

    @Nonnull
    @Override
    public AggregationFields getAggregationFields(String[] aggregationFields) {
        return new AggregationKey(aggregationFields);
    }
}
