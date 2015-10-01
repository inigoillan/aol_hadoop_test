package com.aol.logprocessor.configuration;

import com.aol.logprocessor.builders.impl.CountersBuilderConfig;
import com.aol.logprocessor.builders.impl.KeyBuilderConfig;
import com.aol.logprocessor.georesolver.GeoIPResolverConfig;
import com.aol.logprocessor.parser.input.CSVParserConfig;
import com.aol.logprocessor.printer.CSVPrinterConfig;
import com.aol.logprocessor.transformations.AddressToCountryCodeConfig;
import org.apache.log4j.Logger;
import org.yaml.snakeyaml.Yaml;

import javax.annotation.Nonnull;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public class Config implements AddressToCountryCodeConfig, CSVParserConfig, CSVPrinterConfig, KeyBuilderConfig, CountersBuilderConfig, GeoIPResolverConfig {
    private static final Logger LOG = Logger.getLogger(Config.class);

    private final Map<String, Object> config;

    public Config(InputStream config) {
        Yaml yaml = new Yaml();

        this.config = (Map) yaml.load(config);
    }

    @Override
    public String getInputSeparator() {
        String value = (String) ((Map) config.get("input")).get("separator");

        LOG.info(String.format("Read input separator with value: %s", value));

        return value;
    }

    @Override
    public int getAddressField() {
        int value = (int) ((Map) config.get("address")).get("field");

        LOG.info(String.format("Read address field with value: %d", value));

        return value;
    }


    @Override
    public String getOutputSeparator() {
        String value = (String) ((Map) config.get("output")).get("separator");

        LOG.info(String.format("Read output separator with value: %s", value));

        return value;
    }

    @Nonnull
    @Override
    public List<String> getCountersValues() {
        List<String> value = (List<String>) ((Map) config.get("output")).get("print");

        LOG.info(String.format("Read counters with values: %s", value));

        return value;
    }

    @Override
    public int getCounterField() {
        int value = (int) ((Map) config.get("aggregate")).get("on_field");

        LOG.info(String.format("Read number of counters with value: %d", value));

        return value;
    }

    @Override
    public int[] getKeyFieldIndexes() {
        List<Integer> keyIndexesInConfig = (List<Integer>) ((Map) config.get("aggregate")).get("fields");

        int[] keyIndexes = new int[keyIndexesInConfig.size()];

        for(int i = 0; i < keyIndexes.length; i++) {
            keyIndexes[i] = keyIndexesInConfig.get(i);
        }

        LOG.info(String.format("Read key field indexes with values: %s", Arrays.toString(keyIndexes)));

        return keyIndexes;
    }

    @Nonnull
    @Override
    public String getGeoIPDatabaseFilename() {
        String database = (String) ((Map) config.get("geoip")).get("database");

        LOG.info(String.format("Reading Geo IP database from: %s", database));

        return database;
    }
}
