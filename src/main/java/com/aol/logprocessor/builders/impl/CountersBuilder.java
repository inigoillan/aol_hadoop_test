package com.aol.logprocessor.builders.impl;

import com.aol.logprocessor.aggregations.Counters;
import com.aol.logprocessor.builders.CounterFactory;
import com.google.api.client.repackaged.com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import org.apache.log4j.Logger;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;

/**
 * Generates a new {@link Counters} instance for a given {@CountersBuilderConfig}
 *
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public class CountersBuilder<T extends Counters> implements com.aol.logprocessor.builders.CountersBuilder<T> {
    private final static Logger LOG = Logger.getLogger(CountersBuilder.class);

    private final Map<String, Integer> countersMapping;
    private final int fieldIndex;
    private final CounterFactory<T> factory;

    public CountersBuilder(@Nonnull CountersBuilderConfig config, @Nonnull CounterFactory<T> factory) {
        this.factory = factory;
        List<String> counterValues = config.getCountersValues();

        countersMapping = Maps.newHashMap();

        for (int i = 0; i < counterValues.size(); i++) {
            String field = counterValues.get(i);

            countersMapping.put(field, i);
        }

        fieldIndex = config.getCounterField();
    }

    @Override
    @Nonnull
    public T buildCounters(@Nonnull String[] fields) {
        Preconditions.checkArgument(this.fieldIndex < fields.length);

        String field = fields[this.fieldIndex];
        T counters = factory.getAggregationCounters(countersMapping.size());

        if (countersMapping.containsKey(field)) {
            int index = countersMapping.get(field);
            counters.increase(index);
        } else {
            LOG.debug(String.format("Not found $%s$", field));
        }

        return counters;
    }

    @Nonnull
    @Override
    public T buildZeroCounters() {
        return factory.getAggregationCounters(countersMapping.size());
    }
}
