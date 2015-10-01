package com.aol.logprocessor.configuration;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public class ConfigTest {
    @Test
    public void GetAddressField_CorrectConfig_LoadedCorrectly() {
        // Arrange
        Config config = new Config(getConfigStream());

        // Act
        int field = config.getAddressField();

        // Assert
        assertEquals(1, field);
    }

    @Test
    public void GetInputSeparator_CorrectConfig_LoadedCorrectly() {
        // Arrange
        Config config = new Config(getConfigStream());

        // Act
        String separator = config.getInputSeparator();

        // Assert
        assertEquals(",", separator);
    }

    @Test
    public void GetOutputSeparator_CorrectConfig_LoadedCorrectly() {
        // Arrange
        Config config = new Config(getConfigStream());

        // Act
        String separator = config.getOutputSeparator();

        // Assert
        assertEquals(",", separator);
    }

    @Test
    public void GetCounterField_CorrectConfig_LoadedCorrectly() {
        // Arrange
        Config config = new Config(getConfigStream());

        // Act
        int numberOfCounters = config.getCounterField();

        // Assert
        assertEquals(3, numberOfCounters);
    }

    @Test
    public void GetCountersValues_CorrectConfig_LoadedCorrectly() {
        // Arrange
        Config config = new Config(getConfigStream());

        // Act
        List<String> counters = config.getCountersValues();

        // Assert
        ArrayList<String> expected = Lists.newArrayList("views", "impressions", "clicks");

        assertTrue(counters.containsAll(expected));
    }

    @Test
    public void GetKeyFieldIndexes_CorrectConfig_LoadedCorrectly() {
        // Arrange
        Config config = new Config(getConfigStream());

        // Act
        int[] counters = config.getKeyFieldIndexes();

        // Assert
        int[] expected = new int[] {0, 1};
        assertArrayEquals(expected, counters);
    }


    private InputStream getConfigStream() {
        String yaml =
                "input:\n" +
                "  separator: \",\"\n" +
                "\n" +
                "address:\n" +
                "  field: 1\n" +
                "\n" +
                "output:\n" +
                "  separator: \",\"\n" +
                "  print: [views, impressions, clicks]\n" +
                "\n" +
                "aggregate:\n" +
                "  fields: [0, 1]\n";

        InputStream inputStream = new ByteArrayInputStream(yaml.getBytes());

        return inputStream;
    }

}