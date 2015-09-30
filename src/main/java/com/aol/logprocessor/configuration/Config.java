package com.aol.logprocessor.configuration;

import com.aol.logprocessor.parser.input.CSVParserConfig;
import com.aol.logprocessor.printer.CSVPrinterConfig;
import com.aol.logprocessor.transformations.AddressToCountryCodeConfig;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

/**
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public class Config implements AddressToCountryCodeConfig, CSVParserConfig, CSVPrinterConfig {

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
        return Integer.getInteger((String) ((Map) config.get("address")).get("field"));
    }


    @Override
    public String getOutputSeparator() {
        return (String) ((Map) config.get("output")).get("separator");
    }
}
