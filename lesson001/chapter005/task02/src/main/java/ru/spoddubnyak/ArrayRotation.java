	package ru.spoddubnyak;

  public class ArrayRotation {
    public int[][] sourceArray;
   
   public ArrayRotation(int[][] sourceArray){
     this.sourceArray = sourceArray;   
   }
  
	public int[][] turnArrayRight () {
	   int[][] resultArray = new int[this.sourceArray.length][this.sourceArray.length]; 
	   for(int col = 0; col < this.sourceArray.length; col++) {      
		  for(int row = 0; row < this.sourceArray.length; row++) {		   
		    resultArray[col][row] = this.sourceArray[this.sourceArray.length-1-row][row];
		  }
	   }	   
	return resultArray;	
	}  
	
	public int[][] turnArrayLeft () {
	   int[][] resultArray = new int[this.sourceArray.length][this.sourceArray.length]; 
	   for(int col = 0; col < this.sourceArray.length; col++) {      
		  for(int row = 0; row < this.sourceArray.length; row++) {		   
		    resultArray[col][row] = this.sourceArray[row][this.sourceArray.length-1-col];
		  }
	   }	   
	return resultArray;	
	} 
  }
  