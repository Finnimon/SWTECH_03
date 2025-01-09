package org.sorting;

/**
 * Functional interface for testing sorting algorithms.
 */
@FunctionalInterface
public interface Sorter
{
    /**
     * Sort an array ascending.
     *
     * @param feld The array to sort.
     */
    void sort(int[] feld);
}
