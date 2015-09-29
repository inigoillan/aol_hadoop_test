package com.aol.logprocessor.hadoop;

import com.aol.logprocessor.hadoop.datatypes.AggregationKey;
import com.aol.logprocessor.hadoop.datatypes.CountAggregation;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

class ProcessorReducer extends Reducer<AggregationKey, CountAggregation, NullWritable, Text> {

    @Override
    protected void setup(Context context) throws InterruptedException, IOException {
        // in case you need this
    }

    @Override
    protected void reduce(AggregationKey key, Iterable<CountAggregation> values, Context context) throws InterruptedException, IOException {

        // hint: do not put all your logic into the reduce method
        // think of the single responsibility principle

        for (CountAggregation value : values) {
            context.write(NullWritable.get(), new Text(value.toString()));
        }
    }
}
