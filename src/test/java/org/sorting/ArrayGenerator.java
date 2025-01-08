package org.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;


public class ArrayGenerator
{
    private final int size;
    
    
    public ArrayGenerator(int size)
    {
        this.size = size;
    }
    
    
    public int[] sorted()
    {
        var array = new int[size];
        for (int i = 0; i < size; i++)
        {
            array[i] = i;
        }
        return array;
    }
    
    
    public int[] reverseSorted()
    {
        var array = new int[size];
        for (int i = 0; i < array.length; i++)
        {
            array[i] = array.length - i - 1;
        }
        return array;
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
    
    public int[] generateRandomFromSet(int[] set)
    {
        var array = new int[size];
        for (int i = 0; i < size; i++)
        {
            var rand = Math.random();
            int index= (int) Math.floor(rand * set.length);
            array[i] = set[index];
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
    
    
    public Collection<int[]> generateExtremeNumbers()
    {
        var set=new int[]{Integer.MIN_VALUE,0, Integer.MAX_VALUE};
        var arrays=new ArrayList<int[]>();
        arrays.add(new int[]{Integer.MIN_VALUE,0,Integer.MAX_VALUE});
        arrays.add(new int[]{Integer.MAX_VALUE,0,Integer.MIN_VALUE});
        arrays.add(new int[]{Integer.MIN_VALUE,Integer.MAX_VALUE,0});
        arrays.add(new int[]{Integer.MAX_VALUE,Integer.MIN_VALUE,0});
        arrays.add(new int[]{0,Integer.MIN_VALUE,Integer.MAX_VALUE});
        arrays.add(new int[]{0,Integer.MAX_VALUE,Integer.MIN_VALUE});
        return arrays;
    }
    
    
}
