package org.sorting.main;

import org.sorting.ArrayParser;
import org.sorting.Bubble;

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
            var array = ArrayParser.parse(line);
            if (array.length != 6) throw new IllegalArgumentException("Invalid array length: " + array.length);
            var message = "Unsorted:\t" + ArrayParser.parse(array);
            System.out.println(message);
            Bubble.abc(array);
            System.out.println("Sorted:\t\t" +
                               ArrayParser.parse(array) +
                               "\n\nPlease enter a six digit array to be sorted in the following format: 1 2 3 4 5 6" +
                               ".\nAlternatively enter an empty line to exit.");
        }
    }
}
