package org.sorting;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.stream.Stream;

import static org.sorting.SorterTester.*;


public class AbcTest
{
    private static final int SIZE = 6;
    
    
    //region BlackBox
    @TestFactory
    public Stream<DynamicTest> AbcRandom()
    {
        return TestRandom(Bubble::abc, SIZE);
    }
    @TestFactory
    public Stream<DynamicTest> SpecialOrders()
    {
        return TestSpecialOrders(Bubble::abc, SIZE);
    }
    @TestFactory
    public Stream<DynamicTest> ExtremeNumbers()
    {
        return TestExtremeNumbers(Bubble::abc, SIZE);
    }
    //endregion
    //region WhiteBox
    @TestFactory
    public Stream<DynamicTest> AbcAllOrders()
    {
        return TestSorterAllOrders(Bubble::abc, SIZE);
    }
    
    
    @Test
    public void DuplicateNumberBeginning()
    {
        var feld = new int[]{ 1, 1, 0, 0, 0, 0 };
        var sortedFeld = feld.clone();
        SorterTester.CheckSorter(Bubble::abc, sortedFeld);
    }
    
    //endregion
}
