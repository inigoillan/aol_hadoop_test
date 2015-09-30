package com.aol.logprocessor.configuration;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

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


    private InputStream getConfigStream() {
        String yaml =
                "input:\n" +
                "  separator: \",\"\n" +
                "\n" +
                "address:\n" +
                "  field: 1\n" +
                "\n" +
                "output:\n" +
                "  separator: \",\"";

        InputStream inputStream = new ByteArrayInputStream(yaml.getBytes());

        return inputStream;
    }

}