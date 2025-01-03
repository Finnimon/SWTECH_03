package org.sorting;

import java.util.ArrayList;
import java.util.Arrays;


public class ArrayGenerator
{
    private final int size;
    
    
    public ArrayGenerator(int size)
    {
        this.size = size;
    }
    
    
    public int[] generateRandomArray()
    {
        int[] array = new int[size];
        for (int i = 0; i < size; i++)
        {
            var rand = Math.random();
            array[i] = (int) (rand * Integer.MAX_VALUE);
            array[i] = rand < 0.5 ? array[i] : -array[i];
        }
        return array;
    }
    
    
    public ArrayList<int[]> generateAllOrders(int lowerBound, int upperBound)
    {
        if (lowerBound > upperBound)
        {
            return new ArrayList<>(0);
        }
        int count = (int) Math.pow(upperBound - lowerBound + 1, size);
        var orders = new ArrayList<int[]>(count);
        orders.add(new int[size]);
        var last = orders.getLast();
        Arrays.fill(last, lowerBound);
        for (long j = 1; j < count; j++)
        {
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
            last = next;
            orders.add(next);
        }
        return orders;
    }
}
