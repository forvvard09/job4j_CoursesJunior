package ru.spoddubnyak;

public class Factorial {
   public int number;
   
   // check the correctness of the data for decision
   public int checkPossibleSolution (int inputNumber){
	   if (inputNumber < 0) {
		   throw new IllegalArgumentException ("Factorial can be computed for a negative number.");
	   } else {
		  return calculationFactorial(inputNumber); 
	   }
   }
   
   // factorial calculation
   public int calculationFactorial(int inputData ) {
	  int result = 0;
	  if (0 == inputData || 1 == inputData) {
		 result = 1; 
	  } else {
		  result++;
		  for (int i = 1; i <= inputData; i++) {
			  result *= i;
		  }
	  } 
	  return result;   
   }
}  