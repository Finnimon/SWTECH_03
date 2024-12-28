package org.sorting;

public class ArrayGenerator
{
    private int size;
    public ArrayGenerator(int size) {
        this.size = size;
    }

    public int[] generateRandomArray() {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * Integer.MAX_VALUE);
        }
        return array;
    }
}
