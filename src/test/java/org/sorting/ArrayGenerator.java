package org.sorting;

import java.util.ArrayList;
import java.util.Arrays;


public class ArrayGenerator
{
    private int size;
    
    
    public ArrayGenerator(int size)
    {
        this.size = size;
    }
    
    
    public int[] generateRandomArray()
    {
        int[] array = new int[size];
        for (int i = 0; i < size; i++)
        {
            array[i] = (int) (Math.random() * Integer.MAX_VALUE);
        }
        return array;
    }
    
    
    public ArrayList<int[]> generateAllOrders(int lowerBound, int upperBound)
    {
        if (lowerBound > upperBound)
        {
            return new ArrayList<>(0);
        }
        
        var orders = new ArrayList<int[]>((int)Math.pow(upperBound - lowerBound+1, size));
        orders.add(Arrays.stream(new int[size]).map(i -> lowerBound).toArray());
        var last = orders.getLast();
        for (int j = 1; j < Math.pow(upperBound - lowerBound+1, size); j++)
        {
            var next = last.clone();
            var overFlow = false;
            
            for (int i = 0; i < size; i++)
            {
                if (last[i] != upperBound)
                {
                    next[i] = last[i] + 1;
                    break;
                }
                next[i] = lowerBound;
                overFlow = true;
            }
            last = next;
            orders.add(next);
        }
        return orders;
    }
}
