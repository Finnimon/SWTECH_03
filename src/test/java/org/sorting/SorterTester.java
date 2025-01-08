package org.sorting;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Stream;


public class SorterTester
{
    public static Stream<DynamicTest> TestRandom(Sorter sorter, int size)
    {
        var arrayGenerator = new ArrayGenerator(size);
        var arrays = new ArrayList<int[]>();
        for (int i = 0; i < 50000; i++)
        {
            int[] feld = arrayGenerator.generateRandomArray();
            arrays.add(feld);
        }
        
        return SorterTestStream(arrays, sorter);
    }
    
    public static Stream<DynamicTest> TestSorterAllOrders(Sorter sorter, int size)
    {
        var arrayGenerator = new ArrayGenerator(size);
        
        return SorterTestStream(arrayGenerator.generateAllOrders(0, size - 1), sorter);
    }
    
    
    public static Stream<DynamicTest> TestSpecialOrders(Sorter sorter, int size)
    {
        var generator=new ArrayGenerator(size);
        var arrays = new ArrayList<int[]>();
        arrays.add(generator.sorted());
        arrays.add(generator.reverseSorted());
        
        return SorterTestStream(arrays,sorter);
    }
    
    public static Stream<DynamicTest> TestExtremeNumbers(Sorter sorter, int size)
    {
        var arrayGenerator = new ArrayGenerator(size);
        
        return SorterTestStream(arrayGenerator.generateExtremeNumbers(), sorter);
    }
    
    private static Stream<DynamicTest> SorterTestStream(Collection<int[]> arrayGenerator, Sorter sorter)
    {
        return arrayGenerator.stream()
                             .map(x -> DynamicTest.dynamicTest(ArrayParser.parseStringArray(x),
                                                               () -> CheckSorter(sorter, x)));
    }
    

    
    public static void CheckSorter(Sorter sorter, int[] feld)
    {
        var actual=feld.clone();
        sorter.sort(actual);
        if (isSorted(actual)) return;
        var expected=feld.clone();
        Arrays.sort(expected);
        Assertions.assertArrayEquals(expected, actual,"Attempted to sort:\t"+ArrayParser.parseStringArray(feld));
        
    }
    
    
    private static boolean isSorted(int[] feld)
    {
        for (int i = 1; i < feld.length; i++)
        {
            if (feld[i] < feld[i - 1])
            {
                return false;
            }
        }
        return true;
    }
    
    

}
