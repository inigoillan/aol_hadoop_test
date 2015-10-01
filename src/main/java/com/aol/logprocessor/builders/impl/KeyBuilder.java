package com.aol.logprocessor.builders.impl;

import com.aol.logprocessor.aggregations.AggregationFields;
import com.aol.logprocessor.builders.KeyFactory;
import com.google.api.client.util.Lists;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public class KeyBuilder implements com.aol.logprocessor.builders.KeyBuilder {

    private final int[] keyFieldIndexes;
    private final KeyFactory factory;

    public KeyBuilder(@Nonnull KeyBuilderConfig config, @Nonnull KeyFactory factory) {
        keyFieldIndexes = config.getKeyFieldIndexes();
        this.factory = factory;
    }

    @Nonnull
    @Override
    public AggregationFields buildKey(String[] fields) {
        List<String> keyFields = Lists.newArrayList();

        for (int i = 0; i < keyFieldIndexes.length; i++) {
            keyFields.add(fields[keyFieldIndexes[i]]);
        }

        AggregationFields aggregationFields = factory.getAggregationFields(keyFields.toArray(new String[keyFields.size()]));

        return aggregationFields;
    }
}
