package ru.spoddubnyak;

public class BubleArray {
    public int[] values;

    public BubleArray(int[] values) {
        this.values = values;
    }

    public int[] sortBuble() {
        for (int i = 0; i < this.values.length - 1; i++) {
            for (int j = 0; j < this.values.length - 1 - i; j++) {
                if (this.values[j] > this.values[j + 1]) {
                    int temp = this.values[j];
                    this.values[j] = this.values[j + 1];
                    this.values[j + 1] = temp;
                }
            }

        }
        return this.values;
    }
}