package com.aol.logprocessor.hadoop.datatypes;

import com.aol.logprocessor.aggregations.Counters;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import org.apache.hadoop.io.VIntWritable;
import org.apache.hadoop.io.VLongWritable;
import org.apache.hadoop.io.Writable;

import javax.annotation.Nonnull;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;

/**
 * Convenient class for count (like in SQL) aggregations
 *
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public class CountAggregation implements Writable, Counters {
    private long[] counts;

    public CountAggregation() {}

    public CountAggregation(int numberAggregations) {
        counts = new long[numberAggregations];
    }

    @Override
    public void increase(int bucket) {
        Preconditions.checkArgument(bucket < getLength(),
                "The bucket is greater than the specificied count of aggregations");

        counts[bucket]++;
    }

    @Override
    public void add(int bucket, long count) {
        Preconditions.checkArgument(bucket < getLength(),
                "The bucket is greater than the specificied count of aggregations");

        counts[bucket] += count;
    }

    public long getCount(int bucket) {
        Preconditions.checkArgument(bucket < getLength(),
                "The bucket is greater than the specified number count of aggregations");

        return counts[bucket];
    }

    @Nonnull
    @Override
    public long[] getCounters() {
        return new long[0];
    }

    public int getLength() {
        return this.counts.length;
    }

    /**
     * Merges by adding this and all the elements in countAggregations into a new CountAggregation class
     *
     * @param countAggregations All the elements to be merged
     * @return All the elements merged
     * @throws MergeException If the elements couldn't be merged, this exception is thrown
     */
    public CountAggregation merge(Iterable<CountAggregation> countAggregations) throws MergeException {
        CountAggregation mergedCountAggregation = new CountAggregation(getLength());

        this.addAll(this, mergedCountAggregation);

        for (CountAggregation countAggregation : countAggregations) {
            if (countAggregation.getLength() != getLength()) {
                throw new MergeException();
            }

            this.addAll(countAggregation, mergedCountAggregation);
        }

        return mergedCountAggregation;
    }

    private void addAll(CountAggregation countAggregation, CountAggregation mergedCountAggregation) {
        for (int i = 0; i < countAggregation.getLength(); i++) {
            mergedCountAggregation.counts[i] += countAggregation.counts[i];
        }
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        VIntWritable sizeWritable = new VIntWritable(getLength());

        sizeWritable.write(dataOutput);

        VLongWritable longWritable = new VLongWritable();

        for (int i = 0; i < getLength(); i++) {
            longWritable.set(counts[i]);
            longWritable.write(dataOutput);
        }
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        VIntWritable sizeWritable = new VIntWritable();

        sizeWritable.readFields(dataInput);

        counts = new long[sizeWritable.get()];

        VLongWritable longWritable = new VLongWritable();

        for (int i = 0; i < getLength(); i++) {
            longWritable.readFields(dataInput);
            counts[i] = longWritable.get();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof CountAggregation))
            return false;

        if (this == o)
            return true;

        CountAggregation other = (CountAggregation) o;

        return Arrays.equals(this.counts, other.counts);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this.getClass())
                .add("Counts", Arrays.toString(counts))
                .toString();
    }
}
