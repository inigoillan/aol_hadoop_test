package com.aol.logprocessor.printer;

import com.aol.logprocessor.aggregations.AggregationFields;
import com.aol.logprocessor.aggregations.Counters;
import com.google.common.base.Joiner;
import org.apache.commons.lang.ArrayUtils;

import javax.annotation.Nonnull;

/**
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public class CSVPrinter implements Printer {
    private Joiner joiner;

    public CSVPrinter(@Nonnull CSVPrinterConfig config) {
        String separator = config.getOutputSeparator();

        this.joiner = Joiner.on(separator);
    }


    @Nonnull
    @Override
    public String print(@Nonnull AggregationFields aggregationFields, @Nonnull Counters counterFields) {
        String keys = joiner.join(aggregationFields.getFields());

        String counters = joiner.join(ArrayUtils.toObject(counterFields.getCounters()));

        return joiner.join(keys, counters);
    }
}
