package com.aol.logprocessor.builders.impl;

import com.aol.logprocessor.aggregations.AggregationFields;

import javax.annotation.Nonnull;
import java.util.Arrays;

/**
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public class AggregationFieldsStub implements AggregationFields {

    private final String[] keyFields;

    public AggregationFieldsStub(String[] keyFields) {
        this.keyFields = keyFields;
    }

    @Nonnull
    @Override
    public String[] getFields() {
        return keyFields;
    }

    @Override
    public boolean equals(Object o) {
        return Arrays.equals(keyFields, ((AggregationFieldsStub) o).keyFields);
    }
}
