package com.aol.logprocessor.parser.input;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public class CSVParserTest {

    //region Parse methods

    @Test
    public void Parse_GivenCSV_ReturnParsed() {
        // Arrange
        CSVParser parser = new CSVParser(buildConfig(","));

        // Act
        String[] parsedLine = parser.parse("a,b,c,d");

        // Assert
        String[] expected = new String[] {"a", "b", "c", "d"};
        assertArrayEquals(expected, parsedLine);
    }

    @Test
    public void Parse_GivenCSVWithSpaces_ReturnParsedWithoutSpaces() {
        // Arrange
        CSVParser parser = new CSVParser(buildConfig(","));

        // Act
        String[] parsedLine = parser.parse(" a, b");

        // Assert
        String[] expected = new String[] {"a", "b"};
        assertArrayEquals(expected, parsedLine);
    }

    //endregion


    //region Helper methods

    private CSVParserConfig buildConfig(String separator) {
        CSVParserConfig config = mock(CSVParserConfig.class);

        when(config.getInputSeparator()).thenReturn(separator);

        return config;
    }

    //endregion


}