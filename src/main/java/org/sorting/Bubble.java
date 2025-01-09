package org.sorting;

public final class Bubble
{
    private Bubble()
    {
    }
    
    
    /**
     * Das gegebene Programm abc()
     * @param feld das zu sortierende Array.
     */
    public static void abc(int[] feld)
    {
        int temp;
        do
        {
            temp = feld[0];
            for (int j = 1; j < feld.length; j++)
            {
                if (feld[j] < feld[j - 1])
                {
                    temp = feld[j - 1];
                    feld[j - 1] = feld[j];
                    feld[j] = temp;
                }
            }
        } while (temp != feld[0]);
    }
}

