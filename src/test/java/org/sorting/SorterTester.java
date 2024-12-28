package org.sorting;

import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.io.IOException;


public class SorterTester
{
    public static void TestRandom(Sorter sorter, int size, String loggingPath)
    {
        var logger = new Logger();
        var failed = false;
        var arrayGenerator = new ArrayGenerator(size);
        
        for (int i = 0; i < 50000; i++)
        {
            int[] feld = arrayGenerator.generateRandomArray();
            var currentFailed = CheckAndLog(sorter, feld, logger);
            failed = failed || currentFailed;
        }
        
        try
        {
            logger.Write(loggingPath);
        }
        catch (IOException _)
        {
        }
        Assertions.assertFalse(failed, "To See failures check " + new File(loggingPath).getAbsolutePath());
    }
    
    
    private static boolean CheckAndLog(Sorter sorter, int[] feld, Logger logger)
    {
        var sorted = feld.clone();
        var currentFailed = !CheckSorter(sorter, sorted);
        logger.log(feld, sorted, currentFailed);
        return currentFailed;
    }
    
    
    private static boolean CheckSorter(Sorter sorter, int[] feld)
    {
        Bubble.abc(feld);
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
    
    
    public static void TestSorterAllOrders(Sorter sorter, int size, String loggingPath)
    {
        var logger = new Logger();
        var failed = false;
        var arrayGenerator = new ArrayGenerator(size);
        
        for (int[] feld : arrayGenerator.generateAllOrders(0, size - 1))
        {
            var currentFailed = CheckAndLog(sorter, feld, logger);
            failed = failed || currentFailed;
        }
        
        try
        {
            logger.Write(loggingPath);
        }
        catch (IOException _)
        {
        }
        Assertions.assertFalse(failed, "To See failures check " + new File(loggingPath).getAbsolutePath());
    }
}
