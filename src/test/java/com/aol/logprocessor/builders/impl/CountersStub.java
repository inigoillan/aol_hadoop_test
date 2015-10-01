package com.aol.logprocessor.builders.impl;

import com.aol.logprocessor.aggregations.Counters;

import javax.annotation.Nonnull;
import java.util.Arrays;

/**
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public class CountersStub implements Counters {
    private long[] counters = new long[1024];


    @Override
    public void increase(int bucket) {
        counters[bucket]++;
    }

    @Override
    public void add(int bucket, long count) {
        counters[bucket] += count;
    }

    @Nonnull
    @Override
    public long[] getCounters() {
        return counters;
    }

    @Override
    public boolean equals(Object o) {
        CountersStub other = (CountersStub) o;

        return Arrays.equals(this.counters, other.counters);
    }
}
