package ru.spoddubnyak;

public class CheckString {
    public String sub;
    public String origin;

    public  CheckString(String origin, String sub) {
        this.sub = sub;
        this.origin = origin;
    }

  public boolean checkLineEntyAnotherLine() {
    char[] subArray = this.sub.toCharArray();
    char[] originArray = this.origin.toCharArray();
    int count = 0;
    for  (int i = 0; i < originArray.length - subArray.length + 1; i++ ) {
        if ((originArray[i] == subArray[0]) && (subArray.length + i <= originArray.length)) {
            count = 1;
            for (int j = 1; j < subArray.length; j++) {
                if (originArray[i + j] != subArray[j]) {
                    break;
                }
                count++;
            }
         if (count == subArray.length) {
           return true;
         }  
		}
    }
   return false;
  }
}