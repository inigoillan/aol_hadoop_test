package com.aol.logprocessor.hadoop.datatypes;

import com.google.common.base.Preconditions;
import org.apache.hadoop.io.VIntWritable;
import org.apache.hadoop.io.VLongWritable;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public class CountAggregation implements Writable {
    public long[] counts;

    public CountAggregation() {}

    public CountAggregation(int numberAggregations) {
        counts = new long[numberAggregations];
    }

    public void increase(int bucket) {
        Preconditions.checkArgument(bucket < counts.length,
                "The bucket is greater than the specificied number of aggregations");

        System.out.println(counts[bucket]);
        counts[bucket]++;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        VIntWritable sizeWritable = new VIntWritable(counts.length);

        sizeWritable.write(dataOutput);

        VLongWritable longWritable = new VLongWritable();

        for (int i = 0; i < counts.length; i++) {
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

        for (int i = 0; i < counts.length; i++) {
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
}
