package org.jfree.data.test;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;
import java.util.Arrays;

import org.junit.Test;
import org.jfree.data.DataUtilities;



public class DataUtilitiesCreatenumberArray {

    @Test
    public void testCreateNumberArray2D_BasicInput() {
        double[][] testData = {{1.5, 2.7}, {3.8, 4.9, 5.2}, {6.4}};
        Number[][] expected = {{1.5, 2.7}, {3.8, 4.9, 5.2}, {6.4}};
        
        Number[][] result = DataUtilities.createNumberArray2D(testData);
        
        assertArrayEquals(expected, result);
    }

    @Test
    public void testCreateNumberArray2D_BoundaryValues() {
        double[][] testData = {{Double.MIN_VALUE, Double.MAX_VALUE}};
        Number[][] expected = {{Double.MIN_VALUE, Double.MAX_VALUE}};
        
        Number[][] result = DataUtilities.createNumberArray2D(testData);
        
        assertArrayEquals(expected, result);
    }

    @Test
    public void testCreateNumberArray2D_SpecialValues() {
        double[][] testData = {{1.5, Double.NaN}, {Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY}};
        Number[][] expected = {{1.5, Double.NaN}, {Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY}};
        
        Number[][] result = DataUtilities.createNumberArray2D(testData);
        
        assertArrayEquals(expected, result);
    }

    @Test
    public void testCreateNumberArray2D_IrregularStructure() {
        double[][] testData = {{1.5, 2.7}, {3.8}, {4.9, 5.2, 6.4, 7.6}};
        Number[][] expected = {{1.5, 2.7}, {3.8}, {4.9, 5.2, 6.4, 7.6}};
        
        Number[][] result = DataUtilities.createNumberArray2D(testData);
        
        assertArrayEquals(expected, result);
    }

   /* @Test
    public void testCreateNumberArray2D_LargeInput() {
        double[][] testData = new double[1000][1000];
        for (double[] row : testData) {
            Arrays.fill(row, 1.0);
        }
        Number[][] expected = new Number[1000][1000];
        assertArrayEquals(expected, DataUtilities.createNumberArray2D(testData));
    }*/

    @Test
    public void testCreateNumberArray2D_EdgeCases() {
        double[][] testData = {{1.5}, {2.7}, {3.8}};
        Number[][] expected = {{1.5}, {2.7}, {3.8}};
        
        Number[][] result = DataUtilities.createNumberArray2D(testData);
        
        assertArrayEquals(expected, result);
    }

  /*  @Test(expected = InvalidParameterException.class)
    public void testCreateNumberArray2D_NullInput() {
        double[][] testData = null;
        
        DataUtilities.createNumberArray2D(testData);
    }*/

    @Test
    public void testCreateNumberArray2D_EmptyInput() {
        double[][] testData = {};
        
        Number[][] result = DataUtilities.createNumberArray2D(testData);
        
        assertEquals(0, result.length);
    }

    @Test
    public void testCreateNumberArray2D_RandomizedInput() {
        double[][] testData = generateRandom2DArray(5, 5);
        Number[][] result = DataUtilities.createNumberArray2D(testData);
        
        assertNotNull(result);
        assertEquals(5, result.length);
        assertEquals(5, result[0].length);
    }

    @Test
    public void testCreateNumberArray2D_Performance() {
        double[][] testData = generateRandom2DArray(1000, 1000);
        
        long startTime = System.currentTimeMillis();
        DataUtilities.createNumberArray2D(testData);
        long endTime = System.currentTimeMillis();
        
        assertTrue("Execution time should be reasonable", endTime - startTime < 1000);
    }

    private double[][] generateRandom2DArray(int rows, int cols) {
        double[][] array = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                array[i][j] = Math.random() * 100;
            }
        }
        return array;
    }
}
