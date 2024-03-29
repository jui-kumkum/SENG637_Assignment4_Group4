package org.jfree.data.test;
import static org.junit.Assert.*;

import java.security.InvalidParameterException;
import java.util.Arrays;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.*;
import org.jfree.data.DataUtilities;
import org.jfree.data.Values2D;
import org.jfree.data.KeyedValues;
import org.jfree.data.DefaultKeyedValues;



public class DataUtilitiesTest {
	  private Mockery mockingContext;
	    private KeyedValues kv;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		 this.mockingContext = new Mockery();
	        this.kv = mockingContext.mock(KeyedValues.class);
		
	}
	
	private static final double DELTA = 0.0000000001;

	//---------------------------------CALCULATEROWTOTAL()-----------------------------
	@Test
	public void calculateRowTotalMaxValueBoundary() {
	    Mockery mockingContext = new Mockery();
	    final Values2D values = mockingContext.mock(Values2D.class);

	    mockingContext.checking(new Expectations() {
	        {
	            one(values).getColumnCount();
	            will(returnValue(3)); // Suppose we have 3 columns
	            one(values).getValue(0, 0);
	            will(returnValue(Double.MAX_VALUE));
	            one(values).getValue(0, 1);
	            will(returnValue(Double.MAX_VALUE));
	            one(values).getValue(0, 2);
	            will(returnValue(Double.MAX_VALUE));
	        }
	    });

	    double resultm = DataUtilities.calculateRowTotal(values, 0);
	   // System.out.println("Actual Result: " + resultm);
	    assertEquals("The row total is adding up to 2e10", 3 * Double.MAX_VALUE, resultm, DELTA);
	}

	@Test
	public void calculateRowTotalMinValBoundary() {
	    Mockery mockingContext = new Mockery();
	    final Values2D values = mockingContext.mock(Values2D.class);

	    mockingContext.checking(new Expectations() {
	        {
	            one(values).getColumnCount();
	            will(returnValue(3)); // Suppose we have 1 row
	            one(values).getValue(0, 0);
	            will(returnValue(-Double.MAX_VALUE));
	            one(values).getValue(0, 1);
	            will(returnValue(-Double.MAX_VALUE));
	            one(values).getValue(0, 2);
	            will(returnValue(Double.MAX_VALUE));
	        }
	    });

	    double resultm = DataUtilities.calculateRowTotal(values, 0);
	    double expectedres = -2 * Double.MAX_VALUE + Double.MAX_VALUE;
	    assertEquals("The row total is adding up to Double.MIN_VALUE", expectedres, resultm, DELTA);
	}

	
	@Test(expected = IllegalArgumentException.class)
	public void testCalculateRowTotalWithNullData() {
	    // Attempt to calculate row total with null data object
	    double rowTotal = DataUtilities.calculateRowTotal(null, 0);
	    
	    // The test should fail if no exception is thrown
	    // No need to assert anything here
	}

	
	@Test(expected = IndexOutOfBoundsException.class)
	public void calculateRowTotalInvalidIndex() {
	    Mockery mockingContext = new Mockery();
	    final Values2D values = mockingContext.mock(Values2D.class);

	    mockingContext.checking(new Expectations() {
	        {
	            one(values).getColumnCount();
	            will(returnValue(2)); 

	            // Expecting an InvalidParameterException when accessing negative row index
	            one(values).getValue(with(any(int.class)), with(equal(0)));
	            will(throwException(new IndexOutOfBoundsException()));
	        }
	    });

	    // Attempt to calculate row total with negative index
	    double result = DataUtilities.calculateRowTotal(values, -10);

	    // If an InvalidParameterException is thrown, the test passes
	}
   
	    @Test
	    public void calculateRowTotalValidRow() {
	    	Mockery mockingContext = new Mockery();
		    final Values2D values = mockingContext.mock(Values2D.class);
	        mockingContext.checking(new Expectations() {
	            {
	                one(values).getColumnCount();
	                will(returnValue(3));
	                one(values).getValue(0, 0);
	                will(returnValue(7.5));
	                one(values).getValue(0, 1);
	                will(returnValue(2.0));
	                one(values).getValue(0, 2);
	                will(returnValue(9.0));
	            }
	        });
	        double result = DataUtilities.calculateRowTotal(values, 0);
	 	  // System.out.println("Actual Result: " + result);
	        assertEquals(18.5, result, DELTA);
	    }
	    
	    @Test
	    public void testCalculateRowWithSomeMissingValues() {
	    	Mockery mockingContext = new Mockery();
		    final Values2D values = mockingContext.mock(Values2D.class);
	        mockingContext.checking(new Expectations() {
	            {
	            	one(values).getColumnCount();
	                will(returnValue(3));
	                one(values).getValue(0, 0);
	                will(returnValue(1.0));
	                one(values).getValue(0, 1);
	                will(returnValue(null));
	                one(values).getValue(0, 2);
	                will(returnValue(2.0));
	            }
	        });
	        double result = DataUtilities.calculateRowTotal(values, 0);
	        assertEquals("Total of the row with some missing values should be the rest values", 3.0, result, 0);
	    }
	    
	    @Test
	    public void testCalculateRowWithSingleColumnValue() {
	    	Mockery mockingContext = new Mockery();
		    final Values2D values = mockingContext.mock(Values2D.class);
	        mockingContext.checking(new Expectations() {
	            {
	            	one(values).getColumnCount();
	                will(returnValue(1));
	                one(values).getValue(0, 0);
	                will(returnValue(10.0));
	            }
	        });
	        double result = DataUtilities.calculateRowTotal(values, 0);
	        assertEquals("Total of the row with single value should be the value", 10.0, result, 0);
	    }
	    
	    @Test
	    public void testCalculateRowTotalWithNegativeValues() {
	        Mockery mockingContext = new Mockery();
	        final Values2D values = mockingContext.mock(Values2D.class);

	        mockingContext.checking(new Expectations() {
	            {
	                one(values).getColumnCount();
	                will(returnValue(3));
	                one(values).getValue(0, 0);
	                will(returnValue(-5.0));
	                one(values).getValue(0, 1);
	                will(returnValue(-3.0));
	                one(values).getValue(0, 2);
	                will(returnValue(-2.0));
	            }
	        });
	        double result = DataUtilities.calculateRowTotal(values, 0);
	        assertEquals("Total of row with negative values", -10.0, result, DELTA);
	    }

	    @Test
	    public void testCalculateRowTotalWithEmptyValues2D() {
	        Mockery mockery = new Mockery();
	        final Values2D values = mockery.mock(Values2D.class);

	        mockery.checking(new Expectations() {
	            {
	                one(values).getColumnCount();
	                will(returnValue(0)); // Suppose we have no columns
	            }
	        });

	        double result = DataUtilities.calculateRowTotal(values, 0);
	        assertEquals("Row total should be 0.0 for empty Values2D", 0.0, result, DELTA);
	    }
	    
	    @Test
	    public void testCalculateRowTotalWithZeroColumns() {
	        Mockery mockery = new Mockery();
	        final Values2D values = mockery.mock(Values2D.class);

	        mockery.checking(new Expectations() {
	            {
	                one(values).getColumnCount();
	                will(returnValue(0)); // Suppose we have no columns
	            }
	        });

	        double result = DataUtilities.calculateRowTotal(values, 0);
	        assertEquals("Row total should be 0.0 for Values2D with zero columns", 0.0, result, DELTA);
	    }
	    
	    @Test
	    public void testCalculateRowTotalWhenSumOfARowIsZero() {
	        Mockery mockery = new Mockery();
	        final Values2D values = mockery.mock(Values2D.class);

	        mockery.checking(new Expectations() {
	            {
	                one(values).getColumnCount();
	                will(returnValue(3)); // Suppose we have no columns
	                one(values).getValue(0, 0);
	                will(returnValue(7.5));
	                one(values).getValue(0, 1);
	                will(returnValue(2.5));
	                one(values).getValue(0, 2);
	                will(returnValue(-10));
	            }
	        });

	        double result = DataUtilities.calculateRowTotal(values, 0);
	        assertEquals("Should return 0.0", 0, result, .000000001d);
	    }
	    
	    @Test
	    public void testCalculateRowTotalSecondRow() {
	        Mockery mockery = new Mockery();
	        final Values2D values = mockery.mock(Values2D.class);

	        mockery.checking(new Expectations() {
	            {
	                one(values).getColumnCount();
	                will(returnValue(3)); // Suppose we have no columns
	                one(values).getValue(0, 0);
	                will(returnValue(7.5));
	                one(values).getValue(0, 1);
	                will(returnValue(2.5));
	                one(values).getValue(0, 2);
	                will(returnValue(3.0));
	            }
	        });

	        double result = DataUtilities.calculateRowTotal(values, 0);
	        assertEquals("Should return 0.0", 13.0, result, .000000001d);
	    }
	    
	    @Test
	    public void testCalculateRowTotalThirdRow() {
	        Mockery mockery = new Mockery();
	        final Values2D values = mockery.mock(Values2D.class);

	        mockery.checking(new Expectations() {
	            {
	                one(values).getColumnCount();
	                will(returnValue(3)); // Suppose we have no columns
	                one(values).getValue(0, 0);
	                will(returnValue(7.5));
	                one(values).getValue(0, 1);
	                will(returnValue(2.5));
	                one(values).getValue(0, 2);
	                will(returnValue(5.0));
	            }
	        });

	        double result = DataUtilities.calculateRowTotal(values, 0);
	        assertEquals("Should return 0.0", 15, result, .000000001d);
	    }   
	    
//X--------------------------CALCULATECOLUMNTOTAL()------------------------
	    
	    @Test
		public void calculateColumnTotalForTwoValues() {
	// setup
			Mockery mockingContext = new Mockery();
			final Values2D values = mockingContext.mock(Values2D.class); mockingContext.checking(new Expectations() {
			{
			one(values).getRowCount(); 
			will(returnValue(2));
			one(values).getColumnCount();
			will(returnValue(2));
			one(values).getValue(0, 0); 
			will(returnValue(7.5)); 
			one(values).getValue(1, 0);
			will(returnValue(2.5));
			}
			});
			double result = DataUtilities.calculateColumnTotal(values, 0);
			// verify
			assertEquals(result, 10.0, .000000001d);
	// tear-down: NONE in this test method
		}



	@Test
		public void testCalculateColumnTotal_columnIsLastColumnInTable() {
			Mockery mockingContext = new Mockery();
			final Values2D values = mockingContext.mock(Values2D.class);
			mockingContext.checking(new Expectations() {
				{
					one(values).getRowCount();
					will(returnValue(3));
					one(values).getValue(0, 2); 
				    will(returnValue(2.4)); 
				    one(values).getValue(1, 2); 
				    will(returnValue(1.6));
				    one(values).getValue(2, 2); 
				    will(returnValue(3.4));
				}
			});
			double result = DataUtilities.calculateColumnTotal(values, 2);
			assertEquals(7.4, result, 0.000000001d);
		}
		
	@Test
		public void testCalculateColumnTotal_columnIsFirstInTable() {
			Mockery mockingContext = new Mockery();
			final Values2D values = mockingContext.mock(Values2D.class);
			mockingContext.checking(new Expectations() {
				{
					one(values).getRowCount();
					will(returnValue(3));
					one(values).getValue(0, 0); 
				    will(returnValue(1.4)); 
				    one(values).getValue(1, 0); 
				    will(returnValue(1.6));
				    one(values).getValue(2, 0); 
				    will(returnValue(3.4));
				}
			});
			double result = DataUtilities.calculateColumnTotal(values, 0);
			assertEquals(6.4, result, 0.000000001d);
		}

		
	@Test
	public void testCalculateColumnTotal_columnIsCentralInTable() {
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class);
		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(3));
				one(values).getValue(0, 1); 
			    will(returnValue(1.4)); 
			    one(values).getValue(1, 1); 
			    will(returnValue(1.6));
			    one(values).getValue(2, 1); 
			    will(returnValue(3.4));
			}
		});
		double result = DataUtilities.calculateColumnTotal(values, 1);
		assertEquals(6.4, result, 0.000000001d);
	}
	@Test
	public void calculateColumnTotalWithNullValues() {
		Mockery mockingContext = new Mockery();
	    final Values2D values = mockingContext.mock(Values2D.class);
	    mockingContext.checking(new Expectations() {{
	        oneOf(values).getRowCount(); will(returnValue(3));
	        oneOf(values).getValue(0, 0); will(returnValue(5));
	        oneOf(values).getValue(1, 0); will(returnValue(null));
	        oneOf(values).getValue(2, 0); will(returnValue(10));
	    }});

	    double result = DataUtilities.calculateColumnTotal(values, 0);
	    assertEquals(15.0, result, 0.0000001d);
	}

	@Test
	public void calculateColumnTotalForEmptyDataSet() {
		Mockery mockingContext = new Mockery();
	    final Values2D values = mockingContext.mock(Values2D.class);
	    mockingContext.checking(new Expectations() {{
	        oneOf(values).getRowCount(); will(returnValue(0));
	    }});

	    double result = DataUtilities.calculateColumnTotal(values, 0);
	    assertEquals(0.0, result, 0.0000001d);
	}
	
	//------------------------GETCUMULATIVEPERCENTAGES()----------------------------
	
	@Test
    public void getCumulativePercentagesWithNoKeyedValues() {
        KeyedValues kv = new DefaultKeyedValues();
        KeyedValues cumPercentage = DataUtilities.getCumulativePercentages(kv);
        KeyedValues expected = new DefaultKeyedValues();
        assertEquals("Calling getCumulativePercentage on an empty KeyedValues should return an empty KeyedValues object",
                     expected, cumPercentage);
    }

    @Test
    public void getCumulativePercentagesWithOneRowKeyedValue() {
        mockingContext.checking(new Expectations() {
            {
                allowing(kv).getItemCount();
                will(returnValue(1));
                allowing(kv).getKey(0);
                will(returnValue(0));
                allowing(kv).getValue(0);
                will(returnValue(1));
            }
        });

        KeyedValues result = DataUtilities.getCumulativePercentages(kv);
        assertEquals("The Cumulative Percentage of 2 in data should be 1.0", 1.0, result.getValue(0).doubleValue(), 0.000000001d);
    }

	
	//----------------------------CREATENUMBERARRAY()-----------------------
	
	private final Number[] expected = {1.0, 2.5, 3.7, 4.2}; // Expected output
	
    @Test
    public void testCreateNumberArrayValidInput() {
        double[] testData = {1.0, 2.5, 3.7, 4.2}; // similar input data
        Number[] result = DataUtilities.createNumberArray(testData);
        
        assertArrayEquals(expected, result);
    }
    
     @Test
    public void testCreateNumberArrayEmptyInput() { //passed
        double[] testData = {}; // Empty array
        
        Number[] result = DataUtilities.createNumberArray(testData);
        
        assertEquals(0, result.length);
    }

    //my test cases has 100 % coverage before, No misses !!!!
    //some more test cases 
    @Test
    public void testCreateNumberArrayWithNonNumericValues() {
        double[] testData = {1.0, Double.POSITIVE_INFINITY, 3.7, Double.NEGATIVE_INFINITY};

        Number[] result = DataUtilities.createNumberArray(testData);

        assertEquals(1.0, result[0].doubleValue(), 0.001);
        assertEquals(Double.POSITIVE_INFINITY, result[1].doubleValue(), 0.001);
        assertEquals(3.7, result[2].doubleValue(), 0.001);
        assertEquals(Double.NEGATIVE_INFINITY, result[3].doubleValue(), 0.001);
    }

    @Test
    public void testCreateNumberArrayWithSingleNaN() {
        double[] testData = {Double.NaN};

        Number[] result = DataUtilities.createNumberArray(testData);

        assertTrue(Double.isNaN(result[0].doubleValue()));
    }
    
    //-------------------------CREATENUMBERARRAY2D--------------------------
    
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

    @Test
    public void testCreateNumberArray2D_EdgeCases() {
        double[][] testData = {{1.5}, {2.7}, {3.8}};
        Number[][] expected = {{1.5}, {2.7}, {3.8}};
        
        Number[][] result = DataUtilities.createNumberArray2D(testData);
        
        assertArrayEquals(expected, result);
    }

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
    

//x------------------------------ASSIGNMENT 4 NEW TEST CASES-----------------------------x
    
    @Test
    public void testCreateNumberArray_SingleElementInput() {
        double[] testData = {1.0};
        
        Number[] result = DataUtilities.createNumberArray(testData);
        
        assertNotNull(result);
        assertEquals(1, result.length);
    }
    
    @Test
    public void testCreateNumberArray_LargeInput() {
        double[] testData = generateRandomArray(100);
        
        Number[] result = DataUtilities.createNumberArray(testData);
        
        assertNotNull(result);
        assertEquals(100, result.length);
    }
    
    // Helper method to generate random array
    private double[] generateRandomArray(int size) {
        double[] array = new double[size];
        for (int i = 0; i < size; i++) {
            array[i] = Math.random();
        }
        return array;
    }
//    @Test
//    public void testCreateNumberArrayEmptyInput() { //passed
//        double[] testData = {}; // Empty array
//        
//        Number[] result = DataUtilities.createNumberArray(testData);
//        
//        assertEquals(0, result.length);
//    }
    @Test
    public void testCreateNumberArray_SinglePositiveInfinityInput() {
        double[] testData = {Double.POSITIVE_INFINITY};
        
        Number[] result = DataUtilities.createNumberArray(testData);
        
        assertNotNull(result);
        assertEquals(testData.length, result.length);
        // Check if positive infinity value is preserved in the result array
        assertEquals(Double.POSITIVE_INFINITY, result[0].doubleValue(), 0.0001);
    }
    
    @Test
    public void testGetCumulativePercentagesWithEmptyKeyedValueObject() {
    	// Create a mock when KeyedValues has no item or 0 itemCount
    	mockingContext.checking(new Expectations() {
            {
                allowing(kv).getItemCount();
                will(returnValue(0));
            }
        });

    	// Calculate cumulative percentage
        KeyedValues cumPercentage = DataUtilities.getCumulativePercentages(kv);
        KeyedValues expected = new DefaultKeyedValues();
        assertEquals(
                "Calling getCumulativePercentage on an empty KeyedValues, should return an empty KeyedValues object",
                expected, cumPercentage);
    }
    
    @Test
    public void testGetCumulativePercentagesWithOneZeroValueItem() {
        mockingContext.checking(new Expectations() {
            {
                allowing(kv).getItemCount();
                will(returnValue(1));
                allowing(kv).getKey(0);
                will(returnValue(0));
                allowing(kv).getValue(0);
                will(returnValue(0));
            }
        });

        KeyedValues result = DataUtilities.getCumulativePercentages(kv);
        assertEquals("The cumulative percentage of 0 in data should be NaN", Double.NaN,
                result.getValue(0));
    }

    /**
     * This test case verifies that the method works properly when one value in the KeyedValue object is null.
     */
    @Test
    public void testGetCumulativePercentagesWhenNullExists() {
    	// Create mock for the case when KeyedValue has null item value
        mockingContext.checking(new Expectations() {
            {
                allowing(kv).getItemCount();
                will(returnValue(1));
                allowing(kv).getKey(0);
                will(returnValue(0));
                allowing(kv).getValue(0);
                will(returnValue(null));
            }
        });

        KeyedValues result = DataUtilities.getCumulativePercentages(kv);
        assertEquals("The cumulative percentage of null should be NaN", Double.NaN, result.getValue(0));
    }

    /**
     * This test case verifies that the method works properly when the KeyedValues object has multiple
     * item/values associated to it.
     */
    @Test
    public void testGetCumulativePercentagesWhenMultipleKeyValuedItemsExist() {
        mockingContext.checking(new Expectations() {
            {
                allowing(kv).getItemCount();
                will(returnValue(3));
                allowing(kv).getKey(0);
                will(returnValue(0));
                allowing(kv).getKey(1);
                will(returnValue(1));
                allowing(kv).getKey(2);
                will(returnValue(2));
                allowing(kv).getValue(0);
                will(returnValue(3));
                allowing(kv).getValue(1);
                will(returnValue(2));
                allowing(kv).getValue(2);
                will(returnValue(1));
            }
        });

        KeyedValues result = DataUtilities.getCumulativePercentages(kv);

        assertEquals("The cumulative percentage of 3 should be 3/6", 0.5, result.getValue(0).doubleValue(), 0.01);
        assertEquals("The cumulative percentage of 2 should be (3 + 2)/6", 0.83, result.getValue(1).doubleValue(),
                0.01);
        assertEquals("The cumulative percentage of 1 should be (3 + 2 + 1)/6", 1.0, result.getValue(2).doubleValue(),
                0.01);
    }
    
    /**
     * Tests if the {@link DataUtilities#getCumulativePercentages(double[])} method
     * correctly handles null input by throwing an exception of {@link IllegalArgumentException}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetCumulativePercentagesWithNullData() {
    	// Expects the method to throw error
        DataUtilities.getCumulativePercentages(null);
    }
    

    
    @Test
    public void testGetCumulativePercentagesValidityWithGeneralInput() {
        mockingContext.checking(new Expectations() {{
            allowing(kv).getItemCount(); will(returnValue(3));
            allowing(kv).getValue(0); will(returnValue(10));
            allowing(kv).getValue(1); will(returnValue(20));
            allowing(kv).getValue(2); will(returnValue(30));
            allowing(kv).getKey(0); will(returnValue("Key1"));
            allowing(kv).getKey(1); will(returnValue("Key2"));
            allowing(kv).getKey(2); will(returnValue("Key3"));
        }});

        KeyedValues result = DataUtilities.getCumulativePercentages(kv);
        assertNotNull("Result must not be null", result);
        assertEquals("Cumulative percentage for item-1 should be 16.67%", 0.166, ((Number) result.getValue("Key1")).doubleValue(), 0.01);
        assertEquals("Cumulative percentage for item-2 should be 50%", 0.50, ((Number) result.getValue("Key2")).doubleValue(), 0.01);
        assertEquals("Cumulative percentage for item-3 should be 100%", 1.00, ((Number) result.getValue("Key3")).doubleValue(), 0.01);
        mockingContext.assertIsSatisfied();
    }

    @Test
    public void testGetCumulativePercentagesWithValuesAsNull() {
        mockingContext.checking(new Expectations() {{
            allowing(kv).getItemCount(); will(returnValue(3));
            allowing(kv).getValue(0); will(returnValue(null));
            allowing(kv).getValue(1); will(returnValue(20));
            allowing(kv).getValue(2); will(returnValue(30));
            allowing(kv).getKey(0); will(returnValue("Key1"));
            allowing(kv).getKey(1); will(returnValue("Key2"));
            allowing(kv).getKey(2); will(returnValue("Key3"));
        }});

        KeyedValues result = DataUtilities.getCumulativePercentages(kv);
        assertNotNull("Result must not be null", result);
        assertEquals("Cumulative percentage for item-1 should be 0% when value is null", 0.00, ((Number) result.getValue("Key1")).doubleValue(), 0.01);
        assertEquals("Cumulative percentage for item-2 should be 40%", 0.40, ((Number) result.getValue("Key2")).doubleValue(), 0.01);
        assertEquals("Cumulative percentage for item-3 should be 100%", 1.00, ((Number) result.getValue("Key3")).doubleValue(), 0.01);
        mockingContext.assertIsSatisfied();
    }
    
    @Test
    public void testCalculateRowTotalAllNullValues() {
    	Mockery mockingContext = new Mockery();
	    final Values2D values = mockingContext.mock(Values2D.class);
        mockingContext.checking(new Expectations() {{
            oneOf(values).getColumnCount(); will(returnValue(3));
            allowing(values).getValue(with(any(Integer.class)), with(any(Integer.class))); will(returnValue(null));
        }});

        double result = DataUtilities.calculateRowTotal(values, 0);
        assertEquals("Total should be 0 when all values are null", 0.0, result, DELTA);
        mockingContext.assertIsSatisfied();
    }
    
    @Test
    public void testCalculateColumnTotalWhenPosBoundExists() {
    	Mockery mockingContext = new Mockery();
	    final Values2D values = mockingContext.mock(Values2D.class);
        mockingContext.checking(new Expectations() {
            {
                one(values).getRowCount();
                will(returnValue(3));
                one(values).getValue(0, 0);
                will(returnValue(Double.MAX_VALUE));
                one(values).getValue(1, 0);
                will(returnValue(2.5));
                one(values).getValue(2, 0);
                will(returnValue(1.5));
            }
        });
        
        double expectedValue = Double.MAX_VALUE + 2.5 + 1.5;
        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals("Should return " + expectedValue, expectedValue, result, .000000001d);
    }
    
    @Test
    public void testCalculateColumnTotalWhenNegBoundExists() {
    	Mockery mockingContext = new Mockery();
	    final Values2D values = mockingContext.mock(Values2D.class);
        mockingContext.checking(new Expectations() {
            {
                one(values).getRowCount();
                will(returnValue(3));
                one(values).getValue(0, 0);
                will(returnValue(Math.pow(2, -51)));
                one(values).getValue(1, 0);
                will(returnValue(12.5));
                one(values).getValue(2, 0);
                will(returnValue(-12.5));
            }
        });

        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals(Math.pow(2, -51), result, .000000001d);
    }

    @Test
    public void testCalculateColumnTotalWhenTheLargeIndexAtBound() {
    	Mockery mockingContext = new Mockery();
	    final Values2D values = mockingContext.mock(Values2D.class);
        mockingContext.checking(new Expectations() {
            {
                one(values).getRowCount();
                will(returnValue(3));
                one(values).getValue(0, Integer.MAX_VALUE);
                will(returnValue(7.5));
                one(values).getValue(1, Integer.MAX_VALUE);
                will(returnValue(2.5));
                one(values).getValue(2, Integer.MAX_VALUE);
                will(returnValue(5.0));
            }
        });
        
        double expectedValue = 15.0;
        double result = DataUtilities.calculateColumnTotal(values, Integer.MAX_VALUE);
        assertEquals("Should calculate column total correctly with max index", expectedValue, result, .000000001d);
    }
    
    @Test
    public void testCalculateColumnTotalAllNegValues() {
    	Mockery mockingContext = new Mockery();
	    final Values2D values = mockingContext.mock(Values2D.class);
        mockingContext.checking(new Expectations() {{
            one(values).getRowCount(); will(returnValue(2));
            one(values).getValue(0, 0); will(returnValue(-10.0));
            one(values).getValue(1, 0); will(returnValue(-5.0));
        }});

        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals("Total of all negative values should be -15.0", -15.0, result, 0.0000001d);
    }
    
    @Test
    public void testCalculateColumnTotalWithExBoundValues() {
    	Mockery mockingContext = new Mockery();
	    final Values2D values = mockingContext.mock(Values2D.class);
        mockingContext.checking(new Expectations() {{
            one(values).getRowCount(); will(returnValue(2));
            one(values).getValue(0, 0); will(returnValue(Double.MAX_VALUE));
            one(values).getValue(1, 0); will(returnValue(Double.MIN_VALUE));
        }});

        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals("Total should handle extreme values without overflow", Double.MAX_VALUE, result, 0.0000001d);
    }
    
    @Test
    public void testEqualWithNullRanges() {
    	boolean isEqual = DataUtilities.equal(null, null);
    	boolean result = true;  	
    	assertEquals("must true", result, isEqual);
    }
    
    @Test
    public void testEqualWithInNestedArrayDifference() {
        double[][] array1 = {{1.0, 2.0}, {3.0, 4.0}};
        double[][] array2 = {{1.0, 2.0}, {3.0, 4.1}}; 
        assertFalse("Arrays with different nested values must not be equal", DataUtilities.equal(array1, array2));
    }
    
    @Test
    public void testEqualWithOneArrayAsNull() {
    	double[][] testParameters = new double[][]{{1.0, 2.0, 3.0}}; 	
    	boolean isEqual = DataUtilities.equal(null, testParameters);
    	boolean result = false;
    	assertEquals("Should be true", result, isEqual);
    }
    
    @Test
    public void testEqualFirstArrayNull() {
        double[][] firstArray = null;
        double[][] secondArray = {{1.0, 2.0}, {3.0, 4.0}};
        assertFalse("Method should return false when first array is null and second is not", DataUtilities.equal(firstArray, secondArray));
    }

    @Test
    public void testEqualSecondArrayNull() {
        double[][] firstArray = {{1.0, 2.0}, {3.0, 4.0}};
        double[][] secondArray = null;
        assertFalse("Method should return false when second array is null and first is not", DataUtilities.equal(firstArray, secondArray));
    }
    
    @Test
    public void testEqualArraysWithLengDifference() {
        double[][] a1 = {{1.0, 2.0}};
        double[][] a2 = {{1.0, 2.0}, {3.0, 4.0}};
        assertFalse("Method should return false for arrays of different lengths", DataUtilities.equal(a1, a2));
    }
    
    @Test
    public void testEqualArraysWithDiffValues() {
        double[][] a1 = {{1.0, 2.0}, {3.0, 4.0}};
        double[][] a2 = {{1.0, 2.0}, {3.0, 5.0}}; 
        assertFalse("Method should return false when arrays have different contents", DataUtilities.equal(a1, a2));
    }
    
    @Test
    public void testCreateNumberArrayValid() {
        double[] data = {-2.5, 0, 1.5}; 
        Number[] result = {-2.5, 0.0, 1.5}; 
        Number[] actual = DataUtilities.createNumberArray(data); 
        assertArrayEquals("Array should correctly convert all types of numbers", result, actual);
    }


    @Test
    public void testCreateNumberArray2DWithMixedValues() {
        double[][] data = {{-1.0, 2.0}, {3.0, -4.0}};
        Number[][] expected = {{-1.0, 2.0}, {3.0, -4.0}};
        assertArrayEquals(expected, DataUtilities.createNumberArray2D(data));
    }
    
    @Test
    public void testCreateNumberArrayWithNegativeValues() {
        double[] data = {-1.0, -2.0, -3.0};
        Number[] expected = {-1.0, -2.0, -3.0};
        assertArrayEquals(expected, DataUtilities.createNumberArray(data));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCreateNumberForIllegalArguments() {
		double testParameter[] = null;
		DataUtilities.createNumberArray(testParameter);
    }
    
    @Test
    public void testCreateNumberArrayWhenNegativeBoundaryExists() {
    	double[] testParameters = new double[]{1.0, 2.0, Double.MIN_VALUE}; 
        Number[] actualArray = DataUtilities.createNumberArray(testParameters);
        
        Number[] expected = new Number[]{1.0, 2.0, Double.MIN_VALUE};
        assertArrayEquals("Should return a valid Number[] array containing the values", expected, actualArray);
    }
	

	    
	    @After
		public void tearDown() throws Exception {
			
		}

		@AfterClass
		public static void tearDownAfterClass() throws Exception {
			
		}
	
	

}


