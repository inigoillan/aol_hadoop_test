package com.aol.logprocessor.builders.impl;

import com.aol.logprocessor.aggregations.AggregationFields;
import com.aol.logprocessor.builders.KeyFactory;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public class KeyBuilderTest {

    //region BuildKey tests

    @Test
    public void BuildKey_GivenARow_BuildCorrectKey() {
        // Arrange
        int[] keyFields = {0, 2};

        KeyBuilderConfig config = getKeyBuilderConfig(keyFields);
        KeyFactory factory = getKeyFactory();

        KeyBuilder keyBuilder = new KeyBuilder(config, factory);

        // Act
        String[] row = {"a", "b", "c", "d"};
        AggregationFields outputKey = keyBuilder.buildKey(row);

        // Assert
        AggregationFields expectedKey = new AggregationFieldsStub(new String[] {row[0], row[2]});

        assertEquals(expectedKey, outputKey);
    }

    //endregion


    //region Helper methods

    private KeyBuilderConfig getKeyBuilderConfig(int[] keyFields) {
        KeyBuilderConfig config = mock(KeyBuilderConfig.class);
        when(config.getKeyFieldIndexes()).thenReturn(keyFields);
        return config;
    }

    private KeyFactory getKeyFactory() {
        KeyFactory factory = mock(KeyFactory.class);
        when(factory.getAggregationFields(any(String[].class))).thenAnswer(new Answer<AggregationFields>() {
            @Override
            public AggregationFields answer(InvocationOnMock invocationOnMock) throws Throwable {
                String[] params = (String[]) invocationOnMock.getArguments()[0];

                return new AggregationFieldsStub(params);
            }
        });

        return factory;
    }

    //endregion

}