package org.jfree.data.test;

import static org.junit.Assert.*;
import org.jfree.data.Range;
import org.junit.*;

public class RangeTest {
	private Range exampleRange;
	@BeforeClass public static void setUpBeforeClass () throws Exception {
		
	}
	private static final double DELTA = 0.0000000001;
    private static final double EPSILON = 0.0001;

	
	@Before
	public void setUp() throws Exception { 
		exampleRange = new Range (-10.0,10.0);
	}
	
	//X------------------------TEST CASES FOR THE METHOD COMBINE()-------------------------X
	
	@Test
    public void testCombineWithOverlappingRanges() {
		// create two ranges with some overlap 
		Range r1 = new Range(Double.MAX_VALUE - 20.0, Double.MAX_VALUE);
        Range r2 = new Range(Double.MAX_VALUE -40, Double.MAX_VALUE -10.0);
        // combine the ranges
        Range combinedRange = Range.combine(r1,r2);
        assertEquals(Double.MAX_VALUE -40, combinedRange.getLowerBound(), DELTA);
	    assertEquals(Double.MAX_VALUE , combinedRange.getUpperBound(), DELTA);
    }

	 @Test
	 public void testCombineWithNoOverlapInRanges() {
	     // Create two ranges with no overlap
	     Range r1 = new Range(Double.MAX_VALUE - 10.0, Double.MAX_VALUE);
	     Range r2 = new Range(Double.MAX_VALUE -40, Double.MAX_VALUE -30.0);
	     Range combinedRange = Range.combine(r1, r2);
	     assertEquals(Double.MAX_VALUE -40, combinedRange.getLowerBound(), DELTA);
	     assertEquals(Double.MAX_VALUE , combinedRange.getUpperBound(), DELTA);
	 }

	 @Test
	 public void testCombineWithIdenticalRanges() {
	     Range r1 = new Range(1.0, 2.0);
	     Range r2 = new Range(1.0, 2.0);
	     Range combinedRange = Range.combine(r1, r2);
	     assertEquals("Combining identical ranges should return the same range", r1, combinedRange);
	 }
	 
	 @Test
	 public void testCombineWithTouchInRanges() {
	     Range r1 = new Range(2.0, 6.0);
	     Range r2 = new Range(6.0, 10.0);
	     Range combinedRange = Range.combine(r1, r2);
	     assertEquals(2.0, combinedRange.getLowerBound(), DELTA);
	     assertEquals(10.0 , combinedRange.getUpperBound(), DELTA);
	 }
	 
	 @Test
	 public void testCombineWithOneRangeInAnother() {
	     Range r1 = new Range(1.0, 10.0);
	     Range r2 = new Range(3.0, 7.0);
	     Range combinedRange = Range.combine(r1, r2);
	     assertEquals(1.0, combinedRange.getLowerBound(), DELTA);
	     assertEquals(10.0 , combinedRange.getUpperBound(), DELTA);

	 }

    @Test
    public void testCombineWithRangeBoundsDoubleLimits() {
        Range r1 = new Range(Double.MAX_VALUE - 10.0, Double.MAX_VALUE);
        Range r2 = new Range(-Double.MAX_VALUE, -Double.MAX_VALUE + 10.0);
        Range combinedRange = Range.combine(r1, r2);
        assertEquals(-Double.MAX_VALUE, combinedRange.getLowerBound(), DELTA);
    }


    @Test(timeout = 1000) // Timeout to make sure test completes within a reasonable time since we are considering very large ranges
    public void testCombineWithLargeRanges() {
        Range r1 = new Range(Double.MIN_VALUE, Double.MAX_VALUE);
        Range r2 = new Range(-Double.MAX_VALUE, Double.MAX_VALUE);
        Range combinedRange = Range.combine(r1, r2);
        assertEquals(-Double.MAX_VALUE, combinedRange.getLowerBound(), DELTA);
        // Ensuring the method handles large ranges efficiently
        assertNotNull("Combining large ranges should not return null", combinedRange);
    }

    @Test
    public void testCombineWithNaNValues() {
        Range r1 = new Range(Double.NaN, 2.0);
        Range r2 = new Range(1.0, Double.NaN);
        Range combinedRange = Range.combine(r1, r2);
        // Ensuring the method handles NaN values correctly
       assertNotNull("Combining ranges with NaN values should not return null", combinedRange);
    }
    
    @Test
    public void testCombineWithNullRange() {
        Range r1 = new Range(1.0, 2.0);
        Range combinedRange = Range.combine(r1, null);
        assertEquals("Combining with null range should return the non-null range", r1, combinedRange);
    }
    
    @Test
    public void testCombineWithNullAsFirstRange() {
        Range r2 = new Range(1.0, 2.0);
        Range combinedRange = Range.combine(null, r2);
        assertEquals("Combining with null range should return the non-null range", r2, combinedRange);
    }

    @Test
    public void testCombineWithBothRangesNull() {
        Range combinedRange = Range.combine(null, null);
        assertNull("Combining with both ranges null should return null", combinedRange);
    }
    
    //x-----------------------INTERSECTS()-------------------------X
    
    @Test
    public void intersectsWithInputBLBAndALB() {
        assertTrue(this.exampleRange.intersects(-10.00001, -9.99999));
    }

    @Test
    public void intersectsWithInputBLBAndAUB() {
        assertTrue(this.exampleRange.intersects(-10.00001, 10.00001));
    }

    @Test
    public void intersectsWithInputLBAndALB() {
        assertTrue(this.exampleRange.intersects(-10, -9.99999));
    }

    @Test
    public void intersectsWithInputLBAndUB() {
        assertTrue(this.exampleRange.intersects(-10, 10));
    }

    @Test
    public void intersectsWithInputNOMAndNOM() {
        assertTrue(this.exampleRange.intersects(-1, 1)); //passed, intersection
    }

    @Test
    public void intersectsWithInputBLBAndMAX() {
        assertTrue(this.exampleRange.intersects(-10.00001, Double.MAX_VALUE));
    }

    @Test
    public void intersectsWithInput0And0() {
        assertTrue(this.exampleRange.intersects(0, 0));
    }

    ////new


    @Test
    public void intersectsWithInputJustInsideRange() {
        assertTrue(this.exampleRange.intersects(-9.999999, -9.999998));
    }



    @Test
    public void intersectsWithInputLargePositiveValues() {
        assertFalse(this.exampleRange.intersects(1e10, Double.MAX_VALUE));
    }

    @Test
    public void intersectsWithInputNaNValues() {
        assertFalse(this.exampleRange.intersects(Double.NaN, Double.NaN));
    }

    @Test
    public void intersectsWithInputOutsideRange() {
        assertFalse(this.exampleRange.intersects(11, 20));
    }
    ////new
    @Test
    public void intersectsWithZeroWidthExampleRange() {
        Range zeroWidthRange = new Range(5, 5);
        assertTrue(zeroWidthRange.intersects(4, 6));
    }

    @Test
    public void intersectsWithNonIntersectingRanges() {
        assertFalse(this.exampleRange.intersects(15, 20));
    }
//X------------------------GETCENTRALVALUE()---------------------X
    
    @Test
    public void testGetCentralValueEvenLength() {
        Range range = new Range(1.0, 10.0);
        double expectedCentralValue = (1.0 + 10.0) / 2.0;
        assertEquals(expectedCentralValue, range.getCentralValue(), EPSILON);
    }

    @Test
    public void testGetCentralValueOddLength() {
        Range range = new Range(1.0, 9.0);
        double expectedCentralValue = 5.0;
        assertEquals(expectedCentralValue, range.getCentralValue(), EPSILON);
    }

    @Test
    public void testGetCentralValueSingleValue() {
        Range range = new Range(5.0, 5.0);
        double expectedCentralValue = 5.0;
        assertEquals(expectedCentralValue, range.getCentralValue(), EPSILON);
    }

    @Test
    public void testGetCentralValueNegativeRange() {
        Range range = new Range(-5.0, 5.0);
        double expectedCentralValue = 0.0;
        assertEquals(expectedCentralValue, range.getCentralValue(), EPSILON);
    }

    @Test
    public void testGetCentralValueNegativeOddLength() {
        Range range = new Range(-9.0, -1.0);
        double expectedCentralValue = -5.0;
        assertEquals(expectedCentralValue, range.getCentralValue(), EPSILON);
    }

    @Test
    public void testGetCentralValueZeroRange() {
        Range range = new Range(0.0, 0.0);
        double expectedCentralValue = 0.0;
        assertEquals(expectedCentralValue, range.getCentralValue(), EPSILON);
    }

    @Test
    public void testGetCentralValueLargeRange() {
        Range range = new Range(Double.MIN_VALUE, Double.MAX_VALUE);
        double expectedCentralValue = Double.MAX_VALUE / 2.0;
        assertEquals(expectedCentralValue, range.getCentralValue(), EPSILON);
    }

    @Test
    public void testGetCentralValueSymmetricRange() {
        Range range = new Range(-2.5, 2.5);
        double expectedCentralValue = 0.0;
        assertEquals(expectedCentralValue, range.getCentralValue(), EPSILON);
    }

    @Test
    public void testGetCentralValueWithNaN() {
        Range range = new Range(Double.NaN, 10.0);
        assertTrue(Double.isNaN(range.getCentralValue()));
    }
    
    //X-----------------------EXPANDTOINCLUDE()-------------------------X//
    
    @Test
    public void expandToIncludeWithInputBLB() {
        assertEquals("Testing expanding range to include value BLB", new Range(-10.00001, 10),
                Range.expandToInclude(this.exampleRange, -10.00001));
    }

    @Test
    public void expandToIncludeWithInputLB() {
        assertEquals("Testing expanding range to include value at LB (range shouldn't change)", new Range(-10, 10),
                Range.expandToInclude(this.exampleRange, -10));
    }


    @Test
    public void expandToIncludeWithInputUpperBoundSlightlyBelowUB() {
        assertEquals("Testing expanding range to include value just below UB", new Range(-10, 10.00001),
                Range.expandToInclude(new Range(-10, 10), 10.00001));
    }
  
    @Test
    public void expandToIncludeWithInputUpperBound() {
        assertEquals("Testing expanding range to include value at UB (range shouldn't change)", new Range(-10, 10),
                Range.expandToInclude(new Range(-10, 10), 10));
    }
 
    @Test
    public void expandToIncludeWithInputUB() {
        assertEquals("Testing expanding range to include value at UB (range shouldn't change)", new Range(-10, 10),
                Range.expandToInclude(new Range(-10, 10), 10));
    }
    @Test
    public void expandToIncludeWithInputAUB() {
        assertEquals("Testing expanding range to include value just above UB", new Range(-10, 10.00001),
                Range.expandToInclude(new Range(-10, 10), 10.00001));
    }
   
        

    @Test
    public void expandToIncludeWithInputNegative() {
        assertEquals("Testing epanding range to include value at LB (range shouldn't change)", new Range(-25, 10),
                Range.expandToInclude(this.exampleRange, -25));
        }

    @Test
    public void expandToIncludeWithInputGreaterThanUpperBound() {
        assertEquals("Testing expanding range to include value greater than UB", new Range(-10, Double.MAX_VALUE),
                Range.expandToInclude(new Range(-10, 10), Double.MAX_VALUE));
    }

  
    @Test
    public void expandToIncludeWithInputNegativeDoubleMax() {
        assertEquals("Testing epanding range to include negative max double", new Range(-Double.MAX_VALUE, 10),
                Range.expandToInclude(this.exampleRange, -Double.MAX_VALUE));
        }

    @Test
    public void expandToIncludeWithNullRange() {
        assertEquals("Testing expanding range to include Null Range", new Range(10, 10),
                Range.expandToInclude(null, 10));
        }
    //x------------------CONTAINS()------------------------X
    @Test
    public void testContains_PositiveRange_ReturnsCorrectValue() {
        Range range = new Range(1.0, 10.0);
        boolean result = range.contains(5.0);
        assertTrue(result);
    }

    @Test
    public void testContains_PositiveRange_ReturnsFalseForValueLessThanMin() {
        Range range = new Range(1.0, 10.0);
        boolean result = range.contains(0.0);
        assertFalse(result);
    }

    @Test
    public void testContains_PositiveRange_ReturnsFalseForValueGreaterThanMax() {
        Range range = new Range(1.0, 10.0);
        boolean result = range.contains(11.0);
        assertFalse(result);
    }

    @Test
    public void testContains_NegativeRange_ReturnsCorrectValue() {
        Range range = new Range(-10.0, -1.0);
        boolean result = range.contains(-5.0);
        assertTrue(result);
    }

    @Test
    public void testContains_NegativeRange_ReturnsFalseForValueLessThanMin() {
        Range range = new Range(-10.0, -1.0);
        boolean result = range.contains(-11.0);
        assertFalse(result);
    }

    @Test
    public void testContains_NegativeRange_ReturnsFalseForValueGreaterThanMax() {
        Range range = new Range(-10.0, -1.0);
        boolean result = range.contains(0.0);
        assertFalse(result);
    }

    @Test
    public void testContains_ZeroRange_ReturnsCorrectValue() {
        Range range = new Range(0.0, 0.0);
        boolean result = range.contains(0.0);
        assertTrue(result);
    }

    @Test
    public void testContains_ZeroRange_ReturnsFalseForNonZeroValue() {
        Range range = new Range(0.0, 0.0);
        boolean result = range.contains(1.0);
        assertFalse(result);
    }
// New
    
    @Test
    public void testContains_BoundaryValueLower_ReturnsTrue() {
        Range range = new Range(1.0, 10.0);
        assertTrue("Boundary lower value should be contained", range.contains(1.0));
    }

    @Test
    public void testContains_BoundaryValueUpper_ReturnsTrue() {
        Range range = new Range(1.0, 10.0);
        assertTrue("Boundary upper value should be contained", range.contains(10.0));
    }

    // Test for contains method with NaN values
    @Test
    public void testContains_NaNValue() {
        Range range = new Range(1.0, 10.0);
        assertFalse("NaN should not be contained", range.contains(Double.NaN));
    }
    
 //X------------------------------NEW TEST CASES FOR ASSIGNMENT 4--------------------------X
    
    
    
    @Test
    public void expandToIncludeWithNullRangeAndNonNullValue() {
        assertEquals("Testing expanding null range with a non-null value", 
                new Range(5, 5), Range.expandToInclude(null, 5));
    }
 
    @Test
    public void expandToIncludeWithInputWithinRange() {
        assertEquals("Testing expanding range to include a value within the range",
                new Range(-1, 1),
                Range.expandToInclude(new Range(-1, 1), 0.5));
    }   
    
    @Test
    public void testExpandToIncludeWithNegValues() {
        Range range = new Range(2.0, 10.0);
        double newValue = -2.0;
        Range afterexp = Range.expandToInclude(range, newValue);
        assertEquals(new Range(-2.0, 10.0), afterexp);
    }
    
    @Test
    public void testLengthChangeAfterExpandToIncludeRange() {
        Range range = new Range(2.0, 17.0);
        Range afterexp = Range.expandToInclude(range, 20.0);
        assertEquals(18.0, afterexp.getLength(), 0.00001);
    }


    @Test
    public void testIfConstrainOnBound() {
        Range range = new Range(3.0, 10.0);
        assertEquals(3.0, range.constrain(3.0), 0.00001);
        assertEquals(10.0, range.constrain(10.0), 0.00001);
    }
    
    @Test
    public void testConstrainWithInfiniteMixedValues() {
        Range range = new Range(12, 1000);
        assertEquals("Constraining positive infinite value must return upper bound", 1000, range.constrain(Double.POSITIVE_INFINITY), 0.00001);
        assertEquals("Constraining negative infinite value must return lower bound", 12, range.constrain(Double.NEGATIVE_INFINITY), 0.00001);
    }       

    @Test
    public void testShiftOnLengthNoChange() {
        Range r1 = new Range(5, 15);
        Range result = Range.shift(r1, 10, true);
        assertEquals("Shifting range should not alter its length", r1.getLength(), result.getLength(), 0.00001);
    }
    
    @Test
    public void TestexpandWithPositiveBounds() {
        Range r1 = new Range(-11, 11);
        assertEquals(r1, Range.expand(this.exampleRange, 0.05, 0.05));
    }


    @Test
    public void expandWithOppMixedBounds() {
    	Range r1=new Range(-9,11);
        assertEquals(r1, Range.expand(this.exampleRange, -0.05, 0.05));
    }
    
    @Test
    public void testExpandNegBounds() {
        Range Range = new Range(10, 20);
        Range result = Range.expand(Range, -0.1, -0.1);
        assertTrue("Expanded range should shorten", result.getLength() < Range.getLength());
    }

    @Test
    public void expandWithNegBounds() {
    	Range r1=new Range(-9,9);
        assertEquals(r1, Range.expand(this.exampleRange, -0.05, -0.05));
    }

    @Test
    public void expandWithInputLargeMixedBounds() {
    	Range r1=new Range(20,20);
        assertEquals(r1, Range.expand(this.exampleRange, -2, 0));
    }  
    
    @Test
    public void expandWithNoBounds() {
    	Range r1=new Range(-10,10);
        assertEquals(r1, Range.expand(this.exampleRange, 0, 0));
    }
    
    @Test
    public void TestexpandWithMixedBounds() {
        Range r1 = new Range(-11, 9);
        assertEquals(r1, Range.expand(this.exampleRange, 0.05, -0.05));
    }
    
    @Test
	 public void testCombineWithSingleElementRanges() {
	     Range r1 = new Range(2.0, 2.0);
	     Range r2 = new Range(2.0, 2.0);
	     Range combinedRange = Range.combine(r1, r2);
	     assertEquals("Combining such ranges should return the same range", r1, combinedRange);
	 }

    @Test
    public void testCombineIgnoringNaNWithOneRangeasNaN() {
        Range r1 = new Range(2, 7);
        Range r2 = new Range(Double.NaN, Double.NaN);
        Range result = Range.combineIgnoringNaN(r1, r2);
        assertEquals("Combining with NaN range should ignore the NaN range", r1, result);
    }    
    
    @Test
    public void testCombineIgnoringNaNWithBothRangesNull() {
        assertNull("Combining two null ranges must return null", Range.combineIgnoringNaN(null, null));
    }

    @Test
    public void testCombineIgnoringNaNWithInitialRangeNull() {
        Range r2 = new Range(2.0, 10.0);
        assertEquals("Combining null with a valid range must return the valid one", r2, Range.combineIgnoringNaN(null, r2));
    }

    @Test
    public void testCombineIgnoringNaNWithOneNullAndOneNaN() {
        Range range2 = new Range(Double.NaN, Double.NaN);
        assertNull("Combining null with a NaN range should return null", Range.combineIgnoringNaN(null, range2));
    }

    @Test
    public void testCombineIgnoringNaNWithNullAsSecondRange() {
        Range r1 = new Range(3.0, 8.0);
        assertEquals("Combining a valid range with null must return the valid one", r1, Range.combineIgnoringNaN(r1, null));
    }

    @Test
    public void testCombineIgnoringNaNWithNaNAndSecondAsNull() {
        Range r1 = new Range(Double.NaN, Double.NaN);
        assertNull("Combining a NaN range with null must return null", Range.combineIgnoringNaN(r1, null));
    }

    @Test
    public void testCombineIgnoringNaNWithBothValidRanges() {
        Range r1 = new Range(1.0, 8.0);
        Range r2 = new Range(6.0, 10.0);
        Range result = Range.combineIgnoringNaN(r1, r2);
        assertEquals("Lower bound of new range should be minimum of two ranges", 1.0, result.getLowerBound(), 0.00001);
        assertEquals("Upper bound of new range should be maximum of two ranges", 10.0, result.getUpperBound(), 0.00001);
    }

    @Test
    public void testCombineIgnoringNaNWithOneRangeAsNaN() {
        Range r1 = new Range(Double.NaN, Double.NaN);
        Range r2 = new Range(2.0, 6.0);
        Range result = Range.combineIgnoringNaN(r1, r2);
        assertEquals("Combining a NaN range with a valid range should return the valid range", r2, result );
    }

    @Test
    public void testCombineIgnoringNaNWithBothNaNRanges() {
        Range r1 = new Range(Double.NaN, Double.NaN);
        Range r2 = new Range(Double.NaN, Double.NaN);
        Range result = Range.combineIgnoringNaN(r1, r2);
        assertNull("Combining two NaN ranges should return null", result);
    }


    @Test
    public void testEqualsWithSingleElementRange() {
        Range r1 = new Range(5, 5);
        Range r2 = new Range(5, 5);
        assertTrue("Two ranges of zero length are considered equal", r1.equals(r2));
    }

    @Test
    public void testIfToStringGivesCorrectOutput() {
        Range r1 = new Range(-30, 30);
        assertEquals("Range's toString method should format correctly", "Range[-30.0,30.0]", r1.toString());
    }
    
    @Test
    public void intersectsWithSmallValues() {
        assertTrue(this.exampleRange.intersects(1e-20, 1e-19)); // Small range values
    }
    @Test
    public void intersectsWithVerySmallNegativeValues() {
        assertFalse(this.exampleRange.intersects(-1e-20, -1e-19)); // Very small negative values
    }

    @Test
    public void intersectsWithSmallDifference() {
        assertTrue(this.exampleRange.intersects(0, Double.MIN_VALUE)); // Very small difference between upper and lower bounds
    }
    @Test
    public void intersectsWithUpperBoundNegativeInfinity() {
        assertFalse(this.exampleRange.intersects(-8, Double.NEGATIVE_INFINITY)); // Mutation: Replace upper bound with negative infinity
    }
    @Test
    public void testIntersectsWithNonIntersectingRanges() {
        assertFalse(exampleRange.intersects(15, 20)); // Range is entirely after exampleRange
        assertFalse(exampleRange.intersects(-20, -15)); // Range is entirely before exampleRange
    }
    
    
    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}


