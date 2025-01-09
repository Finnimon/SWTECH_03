package org.sorting;

import java.util.Arrays;


/**
 * Provides parsing methods for an array of integers.
 * Uses a csv format with spaces as delimiters.
 */
public final class ArrayParser
{
    /**
     * Private constructor to prevent instantiation.
     */
    private ArrayParser()
    {
    }
    
    
    /**
     * Parses a string representing a csv array, delimited by spaces, to an array of integers.
     *
     * @param string The string to parse.
     * @return The parsed array.
     */
    public static int[] parse(String string)
    {
        if (string.isBlank())
        {
            return new int[0];
        }
        var split = string.trim().split(" ");
        
        return Arrays.stream(split).mapToInt(Integer::parseInt).toArray();
    }
    
    
    /**
     * Parses an array of integers to a string representing a csv array, delimited by spaces.
     *
     * @param array The array to parse.
     * @return The parsed string.
     */
    public static String parse(int[] array)
    {
        return Arrays.stream(array)
                     .mapToObj(String::valueOf)
                     .reduce("", (result, element) -> result + " " + element)
                     .trim();
    }
}
