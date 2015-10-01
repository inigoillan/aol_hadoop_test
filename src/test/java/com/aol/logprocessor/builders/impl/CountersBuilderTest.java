package com.aol.logprocessor.builders.impl;


import com.aol.logprocessor.aggregations.Counters;
import com.aol.logprocessor.builders.CounterFactory;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public class CountersBuilderTest {

    //region BuildCounters tests

    @Test
    public void BuildCounters_GivenAField_ReturnsCounter() {

        // Arrange
        ArrayList<String> columnValues = Lists.newArrayList("a", "b");

        CountersBuilderConfig config = getCountersBuilderConfig(0, columnValues);
        CounterFactory factory = getCounterFactory(columnValues.size());

        CountersBuilder countersBuilder = new CountersBuilder(config, factory);

        // Act
        Counters outputCounters = countersBuilder.buildCounters(new String[] {"a"});

        // Assert
        CountersStub expectedCounters = new CountersStub(columnValues.size());
        expectedCounters.add(0, 1);

        assertEquals(outputCounters, expectedCounters);
    }

    //endregion


    //region Helper methods

    private CountersBuilderConfig getCountersBuilderConfig(int columnIndex, List<String> columnValue) {
        CountersBuilderConfig config = mock(CountersBuilderConfig.class);
        when(config.getCounterField()).thenReturn(columnIndex);

        when(config.getCountersValues()).thenReturn(columnValue);
        return config;
    }

    private CounterFactory getCounterFactory(final int numberCounters) {
        CounterFactory factory = mock(CounterFactory.class);
        when(factory.getAggregationCounters()).thenAnswer(new Answer<Counters>() {
            @Override
            public Counters answer(InvocationOnMock invocationOnMock) throws Throwable {
                return new CountersStub(numberCounters);
            }
        });
        return factory;
    }

    //endregion


}