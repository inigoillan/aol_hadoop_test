package com.aol.logprocessor.configuration;

import com.aol.logprocessor.builders.impl.CountersBuilderConfig;
import com.aol.logprocessor.builders.impl.KeyBuilderConfig;
import com.aol.logprocessor.parser.input.CSVParserConfig;
import com.aol.logprocessor.printer.CSVPrinterConfig;
import com.aol.logprocessor.transformations.AddressToCountryCodeConfig;
import com.google.common.collect.Iterables;
import org.yaml.snakeyaml.Yaml;

import javax.annotation.Nonnull;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public class Config implements AddressToCountryCodeConfig, CSVParserConfig, CSVPrinterConfig, KeyBuilderConfig, CountersBuilderConfig {

    private final Map<String, Object> config;

    public Config(InputStream config) {
        Yaml yaml = new Yaml();

        this.config = (Map) yaml.load(config);
    }

    @Override
    public String getInputSeparator() {
        return (String) ((Map) config.get("input")).get("separator");
    }

    @Override
    public int getAddressField() {
        return (int) ((Map) config.get("address")).get("field");
    }


    @Override
    public String getOutputSeparator() {
        return (String) ((Map) config.get("output")).get("separator");
    }

    @Nonnull
    @Override
    public List<String> getCountersValues() {
        return (List<String>) ((Map) config.get("output")).get("print");
    }

    @Override
    public int getCounterField() {
        return getCountersValues().size();
    }

    @Override
    public int[] getKeyFieldIndexes() {
        List<Integer> keyIndexesInConfig = (List<Integer>) ((Map) config.get("aggregate")).get("fields");

        int[] keyIndexes = new int[keyIndexesInConfig.size()];

        for(int i = 0; i < keyIndexes.length; i++) {
            keyIndexes[i] = keyIndexesInConfig.get(i);
        }

        return keyIndexes;
    }
}
