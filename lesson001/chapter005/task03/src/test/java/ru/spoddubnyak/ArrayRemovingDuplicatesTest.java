package ru.spoddubnyak;

  import org.junit.Test;
  import static org.hamcrest.core.Is.is;
  import static org.junit.Assert.*;

public class ArrayRemovingDuplicatesTest {
	
  @Test
    public void whenThereduplicatesThenRemoveDuplicates() {															   
     	ArrayRemovingDuplicates array = new ArrayRemovingDuplicates (new String[] {"a","a","b","b","c","d","d"});
        String[] checkedArray = {"a","b","c","d"}; 
        assertThat(array.createResultArray(array.removeDuplicates()), is(checkedArray));
   }
   
   @Test
    public void whenNotThereduplicatesThenNotRemoveDuplicates() {															   
     	ArrayRemovingDuplicates array = new ArrayRemovingDuplicates (new String[] {"1","2","3"});
        String[] checkedArray = {"1","2","3"}; 
        assertThat(array.createResultArray(array.removeDuplicates()), is(checkedArray));
   }
}	