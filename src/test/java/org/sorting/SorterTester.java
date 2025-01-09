package org.sorting;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;


/**
 * Provides multiple test cases for a {@code Sorter}.
 *
 * @author Finn Lindig
 * @see Sorter
 * @see ArrayGenerator
 * @see org.junit.jupiter.api.TestFactory
 */
public class SorterTester
{
    //region TestFactories
    
    /**
     * Tests the {@code sorter} against 50k random arrays.
     *
     * @param sorter The Sorter to test.
     * @param size The size of the arrays to test against.
     * @return A {@code TestFactory} for the {@code sorter}.
     * @see org.junit.jupiter.api.TestFactory
     */
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
    
    
    /**
     * Tests the {@code sorter} against all orders of an array. Assumes equivalency for similar arrays.
     * For example [0, 1, 2] and [10, 11, 12] are equivalent.
     *
     * @param sorter The Sorter to test.
     * @param size The size of the arrays to test against. Choosing values larger than 6 may result in redundancy, take a long time or even cause exceptions.
     * @return A {@code TestFactory} for the {@code sorter}.
     * @see org.junit.jupiter.api.TestFactory
     */
    public static Stream<DynamicTest> TestAllOrders(Sorter sorter, int size)
    {
        var arrayGenerator = new ArrayGenerator(size);
        
        return SorterTestStream(arrayGenerator.generateAllOrders(0, size - 1), sorter);
    }
    
    
    /**
     * Tests the {@code sorter} against a sorted and a reversed sorted array.
     *
     * @param sorter The Sorter to test.
     * @param size The size of the arrays to test.
     * @return A {@code TestFactory} for the {@code sorter}.
     * @see org.junit.jupiter.api.TestFactory
     */
    public static Stream<DynamicTest> TestSpecialOrders(Sorter sorter, int size)
    {
        var generator = new ArrayGenerator(size);
        var arrays = new ArrayList<int[]>();
        arrays.add(generator.sorted());
        arrays.add(generator.reverseSorted());
        
        return SorterTestStream(arrays, sorter);
    }
    
    
    /**
     * Tests a {@code Sorter} against all orders possible for an array of size 3 where the value any value either
     * {@code Integer.MIN_VALUE}, {@code 0} or {@code Integer.MAX_VALUE}. Does not check duplicate value arrays.
     *
     * @param sorter The Sorter to test
     * @return A {@code TestFactory} for the {@code sorter}.
     * @see org.junit.jupiter.api.TestFactory
     */
    public static Stream<DynamicTest> TestExtremeNumbers(Sorter sorter)
    {
        return SorterTestStream(ArrayGenerator.generateExtremeNumbers(), sorter);
    }
    //endregion
    //region Simple Tests
    
    
    /**
     * Test {@code sorter} against an empty array.
     *
     * @param sorter The Sorter to test.
     */
    public static void TestEmptyArray(Sorter sorter)
    {
        var feld = new int[0];
        CheckSorter(sorter, feld);
    }
    
    
    /**
     * Test {@code sorter} against a single element array.
     *
     * @param sorter The Sorter to test.
     */
    public static void TestSingleElementArray(Sorter sorter)
    {
        var feld = new int[]{10};
        CheckSorter(sorter, feld);
    }
    
    //endregion
    //region Base Test Function
    
    /**
     * Checks whether a {@code Sorter} sorts an array correctly.
     *
     * @param sorter The Sorter to test.
     * @param feld The array to test against.
     * @see Sorter
     * @throws org.opentest4j.AssertionFailedError If {@code sorter} does not sort {@code feld} correctly.
     */
    public static void CheckSorter(Sorter sorter, int[] feld)
    {
        var actual = feld.clone();
        sorter.sort(actual);
        if (isSorted(actual))
        {
            return;
        }
        var expected = feld.clone();
        Arrays.sort(expected);
        var message = "Attempted to sort:\t" + ArrayParser.parse(feld) +
                      "\nExpected:\t" +
                      ArrayParser.parse(expected) +
                      "\nActual:\t\t" +
                      ArrayParser.parse(feld) +
                      "\n";
        Assertions.assertArrayEquals(expected, actual, message);
    }
    //endregion
    //region private Helper Functions
    
    
    /**
     * Checks whether an array is sorted.
     *
     * @param feld The array to check.
     * @return The result of the check.
     */
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
    
    
    /**
     * Provides a stream of {@code DynamicTest} for a collection of {@code arrays} and a {@code sorter}.
     *
     * @param arrays The arrays to test against.
     * @param sorter The {@code Sorter} to use.
     * @return A stream of {@code DynamicTest} testing the {@code sorter} against the {@code arrays}.
     */
    private static Stream<DynamicTest> SorterTestStream(Collection<int[]> arrays, Sorter sorter)
    {
        return arrays.stream()
                             .map(x -> DynamicTest.dynamicTest(ArrayParser.parse(x),
                                                               () -> CheckSorter(sorter, x)));
    }
    //endregion
}
