package org.jfree.data.test;

import static org.junit.Assert.*; import org.jfree.data.Range; import org.junit.*;

public class RangeExpandToinclude {
    private Range exampleRange;
  
   /* @Test
    public void centralValueShouldBeZero() {
        assertEquals("The central value of -1 and 1 should be 0",
        0, exampleRange.getCentralValue(), .000000001d);
    }*/
    // new test cases ---------------------------------------------------------
    @Test
    public void expandToIncludeWithNullRange() {
    	
        assertEquals("Testing expanding range to include Null Range", new Range(10, 10),
                Range.expandToInclude(null, 10));
    	
    }
    
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
    public void expandToIncludeWithNullRangeAndNonNullValue() {
        assertEquals("Testing expanding null range with a non-null value", 
                new Range(5, 5), Range.expandToInclude(null, 5));
    }

    
    @Test
    public void expandToIncludeWithValueEqualToUpperBound() {
        assertEquals("Testing expanding range to include a value equal to the upper bound",
                new Range(-10, 15), Range.expandToInclude(new Range(-10, 10), 10));
    }
    

    
    @Test
    public void expandToIncludeWithInputWithinRange() {
        assertEquals("Testing expanding range to include a value within the range",
                new Range(-1, 1),
                Range.expandToInclude(new Range(-1, 1), 0.5));
    }
    
}
