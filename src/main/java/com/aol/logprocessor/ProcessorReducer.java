package com.aol.logprocessor;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

class ProcessorReducer extends Reducer<LongWritable, Text, NullWritable, Text> {

    @Override
    protected void setup(Context context) throws InterruptedException, IOException {
        // in case you need this
    }

    @Override
    protected void reduce(LongWritable key, Iterable<Text> values, Context context) throws InterruptedException, IOException {

        // hint: do not put all your logic into the reduce method
        // think of the single responsibility principle

        for (Text value : values) {
            context.write(NullWritable.get(), value);
        }
    }
}
