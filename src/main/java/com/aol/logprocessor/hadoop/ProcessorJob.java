package com.aol.logprocessor.hadoop;

import com.aol.logprocessor.hadoop.datatypes.AggregationKey;
import com.aol.logprocessor.hadoop.datatypes.CountAggregation;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;

public class ProcessorJob extends Configured implements Tool {

    public int run(String[] args) throws Exception {

        Configuration conf = new Configuration();
        conf.set("com.aol.logprocessor.hadoop.config.file", "config.yml");

        Job job = Job.getInstance(conf, "Log Processor");
        job.setJarByClass(Application.class);

        // set input path
        FileInputFormat.addInputPath(job, new Path("input-data.log"));

        // set output path
        Path outputPath = new Path("output");
        FileSystem fs = FileSystem.get(conf);
        fs.delete(outputPath, false);
        FileOutputFormat.setOutputPath(job, outputPath);

        // setup mappers and reducers
        job.setMapperClass(ProcessorMapper.class);

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        // set up mapper and reducer outputs
        job.setMapOutputKeyClass(AggregationKey.class);
        job.setMapOutputValueClass(CountAggregation.class);

        job.setReducerClass(ProcessorReducer.class);
        job.setOutputValueClass(Text.class);

        boolean status = job.waitForCompletion(true);
        return (status) ? 0 : 1;
    }
}