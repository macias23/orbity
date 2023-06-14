import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Circuits {
    public static void main(String[] args) {
        // Deklaracja tablicy permutacji bitów
        int[][] bitPermutations = {
                {2, 0, 1}, {1, 2, 0}, {0, 1, 2}, {1, 0, 2}, {0, 2, 1}
        };
        // Inicjalizacja tablicy permutacji wejść
        int[][] inputPermutations = new int[5][8];

        // Obliczenie indeksów nowych pozycji bitów wejściowych
        for (int i = 0; i < bitPermutations.length; i++) {
            for (int n = 0; n < 8; n++) {
                int[] bitPermutation = bitPermutations[i];

                // Obliczenie nowego indeksu dla danego bitu
                int newIndex = 4 * (n >> bitPermutation[0] & 1) + 2 * (n >> bitPermutation[1] & 1) + (n >> bitPermutation[2] & 1);
                inputPermutations[i][n] = newIndex;
            }
        }
        // Inicjalizacja zbioru i listy dla funkcji
        HashSet<Integer> allFunctions = new HashSet<>();
        ArrayList<Integer> necessaryFunctions = new ArrayList<>();

        // Generowanie obwodów nierównoważnych względem permutacji wejść
        for (int function = 0; function < 256; function++) {
            if (allFunctions.contains(function))
                continue;
            allFunctions.add(function);
            necessaryFunctions.add(function);

            // Generowanie nowych funkcji na podstawie permutacji wejść
            for (int p = 0; p < inputPermutations.length; p++) {
                int newFunction = 0;

                // Przypisanie bitów z oryginalnej funkcji do nowej funkcji na nowych pozycjach
                for (int i = 0; i < 8; i++) {
                    newFunction |= (function >> (7 - inputPermutations[p][i]) & 1) << (7 - i);
                }
                allFunctions.add(newFunction);
            }
        }
        // Wyświetlanie obwodów nierównoważnych
        for (int i:necessaryFunctions) {
            String circuit = Integer.toBinaryString(i);
            StringBuilder sb = new StringBuilder(circuit);
            while (sb.length()<8) sb.insert(0,0);
            System.out.println(sb.toString());
        }
        // Wyświetlanie liczby obwodów nierównoważnych
        System.out.println("Liczba obwodów: " + necessaryFunctions.size());
    }
}
