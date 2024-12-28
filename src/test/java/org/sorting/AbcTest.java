package org.sorting;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.sorting.SorterTester.TestRandom;
import static org.sorting.SorterTester.TestSorterAllOrders;


public class AbcTest
{
    
    @Test
    public void AbcRandom()
    {
        Sorter sorter = Bubble::abc;
        var size = 6;
        
        TestRandom(sorter, size,"src\\test\\resources\\AbcRandom.log");
    }
    
    @Test
    public void AbcAllOrders()
    {
        Sorter sorter = Bubble::abc;
        TestSorterAllOrders(sorter, 6,"src\\test\\resources\\AbcAllOrders.log");
    }
    
}
