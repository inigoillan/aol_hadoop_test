package com.aol.logprocessor.hadoop.datatypes;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public class AggregationKeyTest {

    //region CompareTo tests

    @Test
    public void CompareTo_EqualKeys_Returns0() {
        // Arrange
        AggregationKey aggregationKey1 = new AggregationKey("a", "b");
        AggregationKey aggregationKey2 = new AggregationKey("a", "b");

        // Act
        int compareResult = aggregationKey1.compareTo(aggregationKey2);

        // Assert
        assertEquals(0, compareResult);
    }

    @Test
    public void CompareTo_FirstKeysSizeLower_ReturnsMinus1() {
        // Arrange
        AggregationKey aggregationKey1 = new AggregationKey("a");
        AggregationKey aggregationKey2 = new AggregationKey("a", "b");

        // Act
        int compareResult = aggregationKey1.compareTo(aggregationKey2);

        // Assert
        assertEquals(-1, compareResult);
    }

    @Test
    public void CompareTo_FirstKeysSizeGreater_Returns1() {
        // Arrange
        AggregationKey aggregationKey1 = new AggregationKey("a", "b");
        AggregationKey aggregationKey2 = new AggregationKey("a");

        // Act
        int compareResult = aggregationKey1.compareTo(aggregationKey2);

        // Assert
        assertEquals(1, compareResult);
    }

    @Test
    public void CompareTo_FirstKeysLowerThanSecond_ReturnsMinus1() {
        // Arrange
        AggregationKey aggregationKey1 = new AggregationKey("a", "b");
        AggregationKey aggregationKey2 = new AggregationKey("a", "c");

        // Act
        int compareResult = aggregationKey1.compareTo(aggregationKey2);

        // Assert
        assertEquals(-1, compareResult);
    }

    @Test
    public void CompareTo_FirstKeysGreaterThanSecond_Returns1() {
        // Arrange
        AggregationKey aggregationKey1 = new AggregationKey("a", "c");
        AggregationKey aggregationKey2 = new AggregationKey("a", "b");

        // Act
        int compareResult = aggregationKey1.compareTo(aggregationKey2);

        // Assert
        assertEquals(1, compareResult);
    }


    //endregion


    //region Write and ReadFields tests

    @Test
    public void WriteReadFields_GivenAggregationKey_BeforeAndAfterAreEqual() throws IOException {
        // Arrange
        AggregationKey aggregationKey = new AggregationKey("a", "b");

        // Act
        AggregationKey outputAggregationKey = callWriteAndRead(aggregationKey);

        // Assert
        assertEquals(aggregationKey, outputAggregationKey);
    }

    //endregion


    //region Equals tests

    @Test
    public void Equals_NotEquals_ReturnsFalse() throws IOException {
        // Arrange
        AggregationKey aggregationKey1 = new AggregationKey("a", "b");
        AggregationKey aggregationKey2 = new AggregationKey("a", "c");

        // Act
        boolean equals = aggregationKey1.equals(aggregationKey2);

        // Assert
        assertEquals(false, equals);
    }

    @Test
    public void Equals_NullReference_ReturnsFalse() throws IOException {
        // Arrange
        AggregationKey aggregationKey = new AggregationKey("a", "b");

        // Act
        boolean equals = aggregationKey.equals(null);

        // Assert
        assertEquals(false, equals);
    }

    //endregion


    //region Helper methods

    private AggregationKey callWriteAndRead(AggregationKey aggregationKey) throws IOException {
        AggregationKey outputAggregationKey = new AggregationKey();

        // Prepare output stream classes for serializing
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        DataOutput dataOutput = new DataOutputStream(outputStream);

        // Serialize
        aggregationKey.write(dataOutput);

        // Prepare input stream classes for deserializing
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        DataInputStream dataInput = new DataInputStream(inputStream);

        // Deserialize
        outputAggregationKey.readFields(dataInput);

        return outputAggregationKey;
    }

    //endregion
}