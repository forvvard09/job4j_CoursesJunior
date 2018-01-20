package ru.spoddubnyak;

public class ArrayRemovingDuplicates {
    public String[] sourceArray;

    public ArrayRemovingDuplicates(String[] sourceArray) {
        this.sourceArray = sourceArray;
    }

    public int removeDuplicates() {
        int countDuplication = 0;
        for (int i = 0; i < this.sourceArray.length - 1; i++) {
            if (null != this.sourceArray[i]) {
                for (int j = 1 + i; j < this.sourceArray.length; j++) {
                    if (this.sourceArray[i].equals(this.sourceArray[j])) {
                        this.sourceArray[j] = null;
                        countDuplication++;
                    }
                }
            }
        }
        return countDuplication;
    }

    public String[] createResultArray(int inCountDuplication) {
        String[] resultArray = new String[this.sourceArray.length - inCountDuplication];
        int j = 0;
        for (int i = 0; i < this.sourceArray.length; i++) {
            if (null != sourceArray[i]) {
                resultArray[j] = this.sourceArray[i];
                j++;
            }
        }
        return resultArray;
    }
}
  