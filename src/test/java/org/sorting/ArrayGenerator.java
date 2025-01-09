package org.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;


/**
 * Provides methods for generating arrays for testing purposes.
 */
public class ArrayGenerator
{
    /**
     * The size of arrays to create
     */
    private final int size;
    
    
    /**
     * A random number generator.
     */
    private final Random random = new Random();
    
    
    /**
     * Default constructor.
     *
     * @param size The size of arrays to create
     */
    public ArrayGenerator(int size)
    {
        if (size <= 0)
        {
            throw new IllegalArgumentException("Out of bounds, choose an integer greater than 0.");
        }
        this.size = size;
    }
    
    
    /**
     * Generates arrays of size three populated only with extreme values.
     * The resulting arrays are in the form [min, 0, max] and shuffled.
     *
     * @return The generated arrays.
     */
    public static Collection<int[]> generateExtremeNumbers()
    {
        var arrays = new ArrayList<int[]>();
        arrays.add(new int[]{ Integer.MIN_VALUE, 0, Integer.MAX_VALUE });
        arrays.add(new int[]{ Integer.MAX_VALUE, 0, Integer.MIN_VALUE });
        arrays.add(new int[]{ Integer.MIN_VALUE, Integer.MAX_VALUE, 0 });
        arrays.add(new int[]{ Integer.MAX_VALUE, Integer.MIN_VALUE, 0 });
        arrays.add(new int[]{ 0, Integer.MIN_VALUE, Integer.MAX_VALUE });
        arrays.add(new int[]{ 0, Integer.MAX_VALUE, Integer.MIN_VALUE });
        return arrays;
    }
    
    
    /**
     * Creates an already sorted array.
     *
     * @return The sorted array.
     */
    public int[] sorted()
    {
        var array = new int[size];
        for (int i = 0; i < size; i++)
        {
            array[i] = i;
        }
        return array;
    }
    
    
    /**
     * Creates reversed sorted array.
     *
     * @return The reversed sorted array
     */
    public int[] reverseSorted()
    {
        var array = new int[size];
        for (int i = 0; i < array.length; i++)
        {
            array[i] = array.length - i - 1;
        }
        return array;
    }
    
    
    /**
     * Generates an array filled with random integers.
     *
     * @return The generated array.
     */
    public int[] generateRandomArray()
    {
        int[] array = new int[size];
        for (int i = 0; i < size; i++)
        {
            array[i] = random.nextInt();
        }
        return array;
    }
    
    
    /**
     * Generates all possible orders of arrays of size {@code size},
     * populated only with numbers in the inclusive range {@code lowerBound} to inclusive {@code upperBound}.
     * @param lowerBound The inclusive lower bound of the array values.
     * @param upperBound The inclusive upper bound of the array values.
     * @return All possible
     * @see org.junit.jupiter.api.TestFactory
     */
    public ArrayList<int[]> generateAllOrders(int lowerBound, int upperBound)
    {
        if (lowerBound > upperBound)
        {
            return new ArrayList<>(0);
        }
        int count = (int) Math.pow(upperBound - lowerBound + 1, size);
        var orders = new ArrayList<int[]>(count);
        orders.add(new int[size]);
        Arrays.fill(orders.getLast(), lowerBound);
        for (long j = 1; j < count; j++)
        {
            var last = orders.getLast();
            var next = last.clone();
            
            for (int i = 0; i < size; i++)
            {
                if (last[i] != upperBound)
                {
                    next[i] += 1;
                    break;
                }
                next[i] = lowerBound;
            }
            orders.add(next);
        }
        return orders;
    }
    
    
}
