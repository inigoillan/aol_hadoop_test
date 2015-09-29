package com.aol.logprocessor.hadoop;

import com.aol.logprocessor.hadoop.datatypes.AggregationKey;
import com.aol.logprocessor.hadoop.datatypes.CountAggregation;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

class ProcessorMapper extends Mapper<LongWritable, Text, AggregationKey, CountAggregation> {

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        // in case you need this
    }

    @Override
    public void map(LongWritable key, Text line, Context context) throws IOException, InterruptedException {

        // hint: do not put all your logic into the map method
        // think of the single responsibility principle

        // it's important to us you demonstrate your object oriented design skills
        // how you handle errors and exceptions is also important

        AggregationKey keyout = new AggregationKey("a");

        context.write(keyout, new CountAggregation(1));
    }
}
