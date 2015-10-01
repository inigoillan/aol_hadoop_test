package com.aol.logprocessor.hadoop;

import com.aol.logprocessor.builders.impl.CountersBuilder;
import com.aol.logprocessor.configuration.Config;
import com.aol.logprocessor.hadoop.datatypes.AggregationKey;
import com.aol.logprocessor.hadoop.datatypes.CountAggregation;
import com.aol.logprocessor.hadoop.datatypes.KeyValueFactory;
import com.aol.logprocessor.hadoop.datatypes.MergeException;
import com.google.common.collect.Iterables;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public class ProcessorCombiner extends Reducer<AggregationKey, CountAggregation, AggregationKey, CountAggregation> {
    private static final Logger LOG = Logger.getLogger(ProcessorCombiner.class);

    private CountersBuilder<CountAggregation> countersBuilder;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        String configFilename = context.getConfiguration().get("com.aol.logprocessor.hadoop.config.file");

        try (InputStream configFile = new FileInputStream(configFilename)) {
            Config config = new Config(configFile);

            KeyValueFactory factory = new KeyValueFactory();
            countersBuilder = new CountersBuilder(config, factory);
        }
    }

    @Override
    protected void reduce(AggregationKey key, Iterable<CountAggregation> values, Context context) throws IOException, InterruptedException {
        CountAggregation countAggregation = countersBuilder.buildZeroCounters();

        try {
            CountAggregation mergedCounters = countAggregation.merge(values);

            context.write(key, mergedCounters);
        } catch (MergeException e) {
            LOG.error(String.format("Merge exception for key %s", key));
            LOG.error(String.format("With values %s", Iterables.toString(values)));
        }
    }
}
