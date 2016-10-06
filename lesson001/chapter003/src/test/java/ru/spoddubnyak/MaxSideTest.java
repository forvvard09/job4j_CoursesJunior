package ru.spoddubnyak;

import org.junit.Test;
import org.junit.Rule;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.*;


public class MaxSideTest {
  
  // find max side 1
     @Test
    public void whenSideTriangleThenFindMaxSideOne() {
       Triangle triangle = new Triangle (new Point(1,1), new Point(2,2), new Point(3,4));
	   MaxSide  maxSide = new MaxSide();
	   assertThat(maxSide.getMaxSide(triangle), is(closeTo(3.6D,0.01)));
	}
	
	
	// find max side 2
     @Test
    public void whenSideTriangleThenFindMaxSideTwo() {
       Triangle triangle = new Triangle (new Point(3,5), new Point(1,6), new Point(0,3));
	    MaxSide  maxSide = new MaxSide();
	   assertThat(maxSide.getMaxSide(triangle), is(closeTo(3.6D,0.01)));
	}
	
}