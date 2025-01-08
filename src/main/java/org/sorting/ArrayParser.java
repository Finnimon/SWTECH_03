package org.sorting;

import java.util.Arrays;


public final class ArrayParser
{
    private ArrayParser() {}
    
    
    public static int[] parseIntArray(String line)
    {
        if (line.isBlank())
        {
            return new int[0];
        }
        var split = line.trim().split(" ");
        
        return Arrays.stream(split).limit(6).mapToInt(Integer::parseInt).toArray();
    }
    
    
    public static String parseStringArray(int[] array)
    {
        return Arrays.stream(array).mapToObj(String::valueOf).reduce("",(result,element)-> result + " " + element).trim();
    }
}
