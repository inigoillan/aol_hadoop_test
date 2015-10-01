package com.aol.logprocessor.hadoop;

import com.aol.logprocessor.builders.CountersBuilder;
import com.aol.logprocessor.builders.KeyBuilder;
import com.aol.logprocessor.configuration.Config;
import com.aol.logprocessor.georesolver.GeoIPResolver;
import com.aol.logprocessor.georesolver.GeoResolver;
import com.aol.logprocessor.hadoop.datatypes.AggregationKey;
import com.aol.logprocessor.hadoop.datatypes.CountAggregation;
import com.aol.logprocessor.hadoop.datatypes.KeyValueFactory;
import com.aol.logprocessor.parser.address.IP4AddressParser;
import com.aol.logprocessor.parser.input.CSVParser;
import com.aol.logprocessor.parser.input.RecordParser;
import com.aol.logprocessor.transformations.AddressToCountryCode;
import com.aol.logprocessor.transformations.Transformation;
import com.aol.logprocessor.transformations.TransformationException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

class ProcessorMapper extends Mapper<LongWritable, Text, AggregationKey, CountAggregation> {
    private static final Logger LOG = Logger.getLogger(ProcessorMapper.class);

    private KeyBuilder<AggregationKey> keyBuilder;
    private CountersBuilder<CountAggregation> countersBuilder;
    private RecordParser parser;
    private Transformation transformation;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        String configFilename = context.getConfiguration().get("com.aol.logprocessor.hadoop.config.file");

        try (InputStream configFile = new FileInputStream(configFilename)) {
            Config config = new Config(configFile);

            KeyValueFactory factory = new KeyValueFactory();

            parser = new CSVParser(config);

            GeoIPResolver geoResolver = new GeoIPResolver();
            IP4AddressParser addressParser = new IP4AddressParser();
            transformation = new AddressToCountryCode(geoResolver, addressParser, config);
            keyBuilder = new com.aol.logprocessor.builders.impl.KeyBuilder(config, factory);
            countersBuilder = new com.aol.logprocessor.builders.impl.CountersBuilder(config, factory);
        }
    }

    @Override
    public void map(LongWritable key, Text line, Context context) throws IOException, InterruptedException {
        String[] parsedRecord = parser.parse(line.toString());

        Logger.getLogger(ProcessorMapper.class).info(Arrays.toString(parsedRecord));

        String[] geoResolvedRecord = new String[0];

        try {
            geoResolvedRecord = transformation.transform(parsedRecord);
            AggregationKey aggregationKey = keyBuilder.buildKey(geoResolvedRecord);
            CountAggregation countAggregation = countersBuilder.buildCounters(geoResolvedRecord);

            context.write(aggregationKey, countAggregation);
        } catch (TransformationException e) {
            LOG.error(String.format("Couldn't georesolve the record with values: %s", Arrays.toString(parsedRecord)));
        }
    }
}
