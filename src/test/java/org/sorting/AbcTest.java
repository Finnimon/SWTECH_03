package org.sorting;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.stream.Stream;

import static org.sorting.SorterTester.*;


/**
 * Tests for the {@code abc} Sorter.
 *
 * @see Bubble#abc(int[])
 */
public class AbcTest
{
    /**
     * The size of the arrays to test against.
     */
    private static final int SIZE = 6;
    
    
    /**
     * @see SorterTester#TestRandom(Sorter, int)
     */
    @TestFactory
    public Stream<DynamicTest> AbcRandom()
    {
        return TestRandom(Bubble::abc, SIZE);
    }
    
    
    /**
     * @see SorterTester#TestSpecialOrders(Sorter, int)
     */
    @TestFactory
    public Stream<DynamicTest> AbcSpecialOrders()
    {
        return TestSpecialOrders(Bubble::abc, SIZE);
    }
    
    
    /**
     * @see SorterTester#TestExtremeNumbers(Sorter)
     */
    @TestFactory
    public Stream<DynamicTest> AbcExtremeNumbers()
    {
        return TestExtremeNumbers(Bubble::abc);
    }
    
    /**
     * @see SorterTester#TestAllOrders(Sorter, int)
     */
    @TestFactory
    public Stream<DynamicTest> AbcAllOrders()
    {
        return TestAllOrders(Bubble::abc, SIZE);
    }
    
    
    /**
     * @see SorterTester#TestEmptyArray(Sorter)
     */
    @Test
    public void AbcEmptyArray()
    {
        SorterTester.TestEmptyArray(Bubble::abc);
    }
    
    
    /**
     * @see SorterTester#TestSingleElementArray(Sorter)
     */
    @Test
    public void AbcSingleElementArray()
    {
        SorterTester.TestSingleElementArray(Bubble::abc);
    }
    
    /**
     * Tests the {@code abc} Sorter against an array with duplicate numbers at the beginning,
     * that should both be moved to the end of the array.
     * Will fail.
     */
    @Test
    public void AbcDuplicateNumberBeginning()
    {
        var feld = new int[]{ 1, 1, 0, 0, 0, 0 };
        var sortedFeld = feld.clone();
        SorterTester.CheckSorter(Bubble::abc, sortedFeld);
    }
}
