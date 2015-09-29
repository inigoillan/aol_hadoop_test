package com.aol.logprocessor.hadoop.datatypes;

import com.google.common.collect.Lists;
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
        CountAggregation countAggregation = buildCountAggregation(2, 0);

        // Act
        CountAggregation outputCountAggregation = callWriteAndRead(countAggregation);

        // Assert
        assertEquals(countAggregation, outputCountAggregation);
    }

    @Test
    public void Increase_IncreaseOneCount_CountIsIncreasedEffectively() {
        // Arrange
        CountAggregation countAggregation = new CountAggregation(1);

        // Act
        countAggregation.increase(0);
        long outputCount = countAggregation.getCount(0);

        // Assert
        assertEquals(1, outputCount);
    }

    @Test
    public void Merge_GivenTwo_GetMergedCountAggregation() throws MergeException {
        // Arrange
        CountAggregation countAggregation1 = buildCountAggregation(3, 2);
        CountAggregation countAggregation2 = buildCountAggregation(4, 1);

        // Act
        CountAggregation mergedAggregation = countAggregation1.merge(Lists.newArrayList(countAggregation2));

        // Assert
        CountAggregation expectedOutput = buildCountAggregation(7, 3);
        assertEquals(expectedOutput, mergedAggregation);
    }


    private CountAggregation buildCountAggregation(long... counts) {
        CountAggregation countAggregation = new CountAggregation(counts.length);

        for (int i = 0; i < counts.length; i++) {
            countAggregation.add(i, counts[i]);
        }

        return countAggregation;
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