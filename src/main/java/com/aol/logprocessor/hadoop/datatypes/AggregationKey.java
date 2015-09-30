package com.aol.logprocessor.hadoop.datatypes;

import com.aol.logprocessor.aggregations.AggregationFields;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

import javax.annotation.Nonnull;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;

/**
 * Set of keys use for the aggregation. Take into account that the order of the keys passed into the constructor, matters
 *
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public class AggregationKey implements WritableComparable<AggregationKey>, AggregationFields {
    String[] keys;

    /**
     * Don't use this constructor. This is meant for serializing and deserializing purposes
     */
    public AggregationKey() {}

    public AggregationKey(@Nonnull String... keys) {
        this.keys = keys;
    }

    @Nonnull
    @Override
    public String[] getFields() {
        return keys;
    }

    @Override
    public int compareTo(AggregationKey aggregationKey) {
        if (this.keys.length != aggregationKey.keys.length) {
            return this.keys.length < aggregationKey.keys.length ? -1 : 1;
        }

        for (int i = 0; i < keys.length; i++) {
            if (!this.keys[i].equals(aggregationKey.keys[i])) {
                return this.keys[i].compareTo(aggregationKey.keys[i]);
            }
        }

        return 0;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(keys.length);

        Text text = new Text();

        for (int i = 0; i < keys.length; i++) {
            text.set(keys[i]);
            text.write(dataOutput);
        }

    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        int size = dataInput.readInt();

        keys = new String[size];

        Text text = new Text();

        for (int i = 0; i < keys.length; i++) {
            text.readFields(dataInput);
            keys[i] = text.toString();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof AggregationKey))
            return false;

        if (this == o)
            return true;

        AggregationKey other = (AggregationKey) o;

        return Arrays.equals(this.keys, other.keys);
    }
}
