package com.aol.logprocessor.printer;

import com.aol.logprocessor.aggregations.AggregationFields;
import com.aol.logprocessor.aggregations.Counters;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public class CSVPrinterTest {
    @Test
    public void Print_GivenAggregationFieldsAndCounters_OutputCommaSeparatedValues() {
        // Arrange
        CSVPrinter printer = new CSVPrinter(buildCSVPrinterConfig(","));

        AggregationFields aggregationFields = getAggregationFields("a", "b");
        Counters counters = getCounters(5, 10);

        // Act
        String printedFields = printer.print(aggregationFields, counters);

        // Assert
        assertEquals("a,b,5,10", printedFields);
    }

    private CSVPrinterConfig buildCSVPrinterConfig(String separator) {
        CSVPrinterConfig printerConfig = mock(CSVPrinterConfig.class);
        when(printerConfig.getOutputSeparator()).thenReturn(separator);

        return printerConfig;
    }

    private AggregationFields getAggregationFields(String... keys) {
        AggregationFields aggregationFields = mock(AggregationFields.class);
        when(aggregationFields.getFields()).thenReturn(keys);
        return aggregationFields;
    }

    private Counters getCounters(long... counter) {
        Counters counters = mock(Counters.class);
        when(counters.getCounters()).thenReturn(counter);
        return counters;
    }

}