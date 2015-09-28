package com.aol.logprocessor.parser.input;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;

/**
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public class CSVParser implements RecordParser {
    Splitter splitter = Splitter.on(",").trimResults();

    @Override
    public String[] parse(String row) {
        Iterable<String> splitted = splitter.split(row);

        return Iterables.toArray(splitted, String.class);
    }
}
