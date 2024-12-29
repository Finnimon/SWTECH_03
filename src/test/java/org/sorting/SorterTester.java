package org.sorting;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.sorting.main.Main;

import java.util.ArrayList;
import java.util.stream.Stream;


public class SorterTester
{
    public static Stream<DynamicTest> TestRandom(Sorter sorter, int size)
    {
        var arrayGenerator = new ArrayGenerator(size);
        var list = new ArrayList<int[]>();
        for (int i = 0; i < 50000; i++)
        {
            int[] feld = arrayGenerator.generateRandomArray();
            list.add(feld);
        }
        
        return list.stream()
                   .map(x -> DynamicTest.dynamicTest(Main.parseStringArray(x),
                                                     () -> Assertions.assertTrue(CheckSorter(sorter, x))));
    }
    
    
    private static boolean CheckSorter(Sorter sorter, int[] feld)
    {
        sorter.sort(feld);
        return isSorted(feld);
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
    
    
    public static Stream<DynamicTest> TestSorterAllOrders(Sorter sorter, int size)
    {
        var arrayGenerator = new ArrayGenerator(size);
        
        return arrayGenerator.generateAllOrders(0, size - 1)
                             .stream()
                             .map(x -> DynamicTest.dynamicTest(Main.parseStringArray(x),
                                                               () -> Assertions.assertTrue(CheckSorter(sorter, x))));
    }
    
    
    private static Runnable TestRunnable(int position, int coreCount, Sorter sorter, ArrayList<int[]> arrangements,
                                         ArrayList<DynamicTest> container)
    {
        return () ->
        {
            var chunkSize = arrangements.size() / coreCount;
            var start = position * chunkSize;
            var to = start + chunkSize;
            for (int i = start; i < to; i++)
            {
                var x = arrangements.get(i);
                var test = DynamicTest.dynamicTest(Main.parseStringArray(x),
                                                   () -> Assertions.assertTrue(CheckSorter(sorter, x)));
                container.add(test);
            }
        };
        
    }
}
