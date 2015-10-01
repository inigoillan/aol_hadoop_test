package com.aol.logprocessor.hadoop;

import com.aol.logprocessor.hadoop.datatypes.AggregationKey;
import com.aol.logprocessor.hadoop.datatypes.CountAggregation;
import com.google.common.collect.Lists;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public class ProcessorMapperReducerIT {
    MapDriver<LongWritable, Text, AggregationKey, CountAggregation> mapDriver;
    ReduceDriver<AggregationKey, CountAggregation, NullWritable, Text> reduceDriver;
    MapReduceDriver<LongWritable, Text, AggregationKey, CountAggregation, NullWritable, Text> mapReduceDriver;

    @Before
    public void setup() {
        mapDriver = new MapDriver<>(new ProcessorMapper());
        reduceDriver = new ReduceDriver<>(new ProcessorReducer());
        mapReduceDriver = new MapReduceDriver<>(new ProcessorMapper(), new ProcessorReducer(), new ProcessorCombiner());
    }

    @Test
    public void MapperIntegrationTest() throws IOException {
        mapDriver.getConfiguration().set("com.aol.logprocessor.hadoop.config.file", "config.yml");

        mapDriver.withInput(
                new LongWritable(0),
                new Text("1,64.236.4.133,view")
        );

        AggregationKey expectedKey = new AggregationKey(new String[] {"1", "US"});
        CountAggregation expectedValue = new CountAggregation(3);
        expectedValue.add(0, 1);

        mapDriver.withOutput(expectedKey, expectedValue);

        mapDriver.runTest();
    }

    @Test
    public void ReducerIntegrationTest() throws IOException {
        reduceDriver.getConfiguration().set("com.aol.logprocessor.hadoop.config.file", "config.yml");

        CountAggregation inputValue1 = new CountAggregation(3);
        CountAggregation inputValue2 = new CountAggregation(3);

        inputValue1.add(0, 2);
        inputValue2.add(1, 1);

        reduceDriver.withInput(new AggregationKey(new String[] {"1","US"}), Lists.newArrayList(inputValue1, inputValue2));

        reduceDriver.withOutput(NullWritable.get(), new Text("1,US,2,1,0"));

        reduceDriver.runTest();
    }

    @Test
    public void MapperReducerIntegrationTest() throws IOException {
        mapReduceDriver.getConfiguration().set("com.aol.logprocessor.hadoop.config.file", "config.yml");

        mapReduceDriver.withInput(
                new LongWritable(0),
                new Text("1,64.236.4.133,impression")
        );

        mapReduceDriver.withInput(
                new LongWritable(0),
                new Text("1,64.236.4.133,view")
        );

        mapReduceDriver.withInput(
                new LongWritable(0),
                new Text("2,64.236.4.133,click")
        );

        mapReduceDriver.withOutput(NullWritable.get(), new Text("1,US,1,1,0"));
        mapReduceDriver.withOutput(NullWritable.get(), new Text("2,US,0,0,1"));

        mapReduceDriver.runTest();

    }
}
