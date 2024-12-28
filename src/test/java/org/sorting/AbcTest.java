package org.sorting;

import org.junit.jupiter.api.Test;

import java.util.Arrays;


public class AbcTest
{
    @Test
    public void Abc()
    {
        for (int i = 1; i < 200; i++)
        {
            int[] feld = randomArray(i);
            var sorted= Arrays.stream(feld).sorted().toArray();
            Bubble.abc(feld);
            assert Arrays.equals(feld, sorted);
        }
    }
    
    private int[] randomArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * Integer.MAX_VALUE);
        }
        return array;
    }
}
