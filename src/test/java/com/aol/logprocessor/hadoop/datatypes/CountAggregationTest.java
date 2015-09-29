package com.aol.logprocessor.hadoop.datatypes;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

/**
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public class CountAggregationTest {
    @Test
    public void WriteReadFields_GivenAValidOne_ReturnsEqualsTrueAfterDeserialization() throws IOException {
        // Arrange
        CountAggregation countAggregation = new CountAggregation(2);
        countAggregation.increase(0);
        countAggregation.increase(0);

        // Act
        CountAggregation outputCountAggregation = callWriteAndRead(countAggregation);

        // Assert
        assertEquals(countAggregation, outputCountAggregation);
    }

    private CountAggregation callWriteAndRead(CountAggregation countAggregation) throws IOException {
        CountAggregation outputCountAggregation = new CountAggregation();

        // Prepare output stream classes for serializing
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        DataOutput dataOutput = new DataOutputStream(outputStream);

        // Serialize
        countAggregation.write(dataOutput);

        // Prepare input stream classes for deserializing
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        DataInputStream dataInput = new DataInputStream(inputStream);

        // Deserialize
        outputCountAggregation.readFields(dataInput);

        return outputCountAggregation;
    }
}