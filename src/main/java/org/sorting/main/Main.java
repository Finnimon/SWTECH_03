package org.sorting.main;

import org.sorting.Bubble;

import java.util.Arrays;
import java.util.Scanner;


public class Main
{
    public static void main(String[] args)
    {
        var scanner = new Scanner(System.in);
        System.out.println(
                "Please enter a six digit array to be sorted in the following format: 1 2 3 4 5 6.\nAlternatively " +
                "enter an empty line to exit.");
        while (true)
        {
            if (!scanner.hasNextLine())
            {
                continue;
            }
            var line = scanner.nextLine();
            if (line.isEmpty())
            {
                return;
            }
            var array = parseIntArray(line);
            Bubble.abc(array);
            System.out.println("Sorted:\t" +
                               Arrays.toString(array) +
                               "\n\nPlease enter a six digit array to be sorted in the following format: 1 2 3 4 5 6" +
                               ".\nAlternatively enter an empty line to exit.");
        }
    }
    
    
    public static int[] parseIntArray(String line)
    {
        if (line.isEmpty())
        {
            return new int[0];
        }
        var split = line.split(" ");
        
        return Arrays.stream(split).limit(6).mapToInt(Integer::parseInt).toArray();
    }
    
    public static String parseStringArray(int[] array)
    {
        return Arrays.stream(array).mapToObj(String::valueOf).reduce("",(result,element)->{
            return result + " " + element;
        }).trim();
    }
}
