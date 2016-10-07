package ru.spoddubnyak;

   import org.junit.Test;
   import static org.hamcrest.core.Is.is;
   import static org.junit.Assert.*;
   import org.junit.Rule;
   import org.junit.rules.ExpectedException;

public class FactorialTest {
  
     @Test
    public void whenInputPositiveNumberThenCalculationFactorial() {
       Factorial fact = new Factorial();
	   assertThat(fact.checkPossibleSolution(7), is(5040));
	}
	
	@Test
    public void whenInputNumberZeroThenCalculationFactorial() {
       Factorial fact = new Factorial();
	   assertThat(fact.checkPossibleSolution(0), is(1));
	}

	@Rule
    public ExpectedException expectedException = ExpectedException.none(); 
	 
	 @Test
    public void whenInputNegativeNumberThenCalculationFactorialError() {
		expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Factorial can be computed for a negative number.");
		Factorial fact = new Factorial();
		assertThat(fact.checkPossibleSolution(-5), is("Factorial can be computed for a negative number."));
	} 
 }  