package com.aol.logprocessor.parser.input;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;

import javax.annotation.Nonnull;

/**
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public class CSVParser implements RecordParser {
    private final Splitter splitter;

    public CSVParser(@Nonnull CSVParserConfig config) {
        String separator = config.getInputSeparator();

        splitter = Splitter.on(separator).trimResults();
    }

    @Override
    @Nonnull
    public String[] parse(String row) {
        Iterable<String> splitted = splitter.split(row);

        return Iterables.toArray(splitted, String.class);
    }
}
