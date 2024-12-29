package org.sorting;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.stream.Stream;

import static org.sorting.SorterTester.*;


public class AbcTest
{
    private static final int SIZE =6;
    @TestFactory
    public Stream<DynamicTest> AbcRandom()
    {
        return TestRandom(Bubble::abc, SIZE);
    }
    
    
    @TestFactory
    public Stream<DynamicTest> AbcAllOrders()
    {
        return TestSorterAllOrders(Bubble::abc, SIZE);
    }
}
