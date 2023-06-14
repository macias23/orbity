import java.util.ArrayList;
import java.util.Arrays;

public class Colorings {
    public static void main(String[] args) {
        // Generowanie wszystkich kombinacji cyfr 0,1 (odpowiadających kolorom) dla 8 elementów-wierzchołków sześcianu
        int[][] allCombinations = generateCombinations(8);

        // Zdefiniowanie zestawu permutacji dla sześcianu wynikających z grupy obrotów
        int[][][] cubePermutations = {{{1, 2, 3, 4}, {5, 6, 7, 8}},
                {{1, 3}, {2, 4}, {5, 7}, {6, 8}},
                {{1, 4, 3, 2}, {5, 8, 7, 6}},
                {{1, 2, 6, 5}, {3, 7, 8, 4}},
                {{1, 6}, {2, 5}, {3, 8}, {4, 7}},
                {{1, 5, 6, 2}, {3, 4, 8, 7}},
                {{1, 4, 8, 5}, {2, 3, 7, 6}},
                {{1, 8}, {2, 7}, {3, 6}, {4, 5}},
                {{1, 5, 8, 4}, {2, 6, 7, 3}},
                {{1, 5}, {2, 8}, {3, 7}, {4, 6}},
                {{1, 7}, {2, 6}, {3, 5}, {4, 8}},
                {{1, 2}, {3, 5}, {4, 6}, {7, 8}},
                {{1, 7}, {2, 8}, {3, 4}, {5, 6}},
                {{1, 4}, {2, 8}, {3, 5}, {6, 7}},
                {{1, 7}, {2, 3}, {4, 6}, {5, 8}},
                {{1}, {2, 4, 5}, {3, 8, 6}, {7}},
                {{1}, {2, 5, 4}, {3, 6, 8}, {7}},
                {{1, 3, 6}, {2}, {4, 7, 5}, {8}},
                {{1, 6, 3}, {2}, {4, 5, 7}, {8}},
                {{1, 6, 8}, {2, 7, 4}, {3}, {5}},
                {{1, 8, 6}, {2, 4, 7}, {3}, {5}},
                {{1, 3, 8}, {2, 7, 5}, {4}, {6}},
                {{1, 8, 3}, {2, 5, 7}, {4}, {6}},
                {{1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}}
        };

        ArrayList<int[]> uniqueCubeColorings = uniqueColoring(allCombinations, cubePermutations);
        printArrayList(uniqueCubeColorings);
        System.out.println("Liczba kolorowań: " + uniqueCubeColorings.size());

    }

    public static int[] permuteArray(int[] arr, int[][] cycles) {
        int n = arr.length;
        int[] permutedArray = Arrays.copyOf(arr, n);

        // Iteracja przez każdy cykl w zestawie cykli
        for (int[] cycle : cycles) {
            int[] adjustedCycle = new int[cycle.length];

            // Dostosowanie indeksów cyklu o 1 (w celu łatwiejszego przepisywania cykli)
            for (int i = 0; i < cycle.length; i++) {
                adjustedCycle[i] = cycle[i] - 1;
            }

            int temp = permutedArray[adjustedCycle[adjustedCycle.length - 1]];

            // Iteracja przez każdy element cyklu
            for (int i = adjustedCycle.length - 1; i > 0; i--) {
                permutedArray[adjustedCycle[i]] = permutedArray[adjustedCycle[i - 1]];
            }
            permutedArray[adjustedCycle[0]] = temp;
        }
        return permutedArray;
    }


    // Metoda do znalezienia unikalnych kombinacji kolorów
    public static ArrayList<int[]> uniqueColoring(int[][] allCombinations, int[][][] permutations) {
        ArrayList<int[]> uniqueColoring = new ArrayList<>();

        //Iteracja przez każdą kombinację
        for (int[] combination : allCombinations) {
            boolean unique = true;
            for (int[][] cycle : permutations) {
                int[] afterPerm = permuteArray(combination, cycle);
                if (containsSameArray(uniqueColoring, afterPerm)) unique = false;
            }

            //Jeśli lista unikalnych kolorowań nie zawiera żadnych permutacji danej kombinacji, dodajemy ją do unikalnych kolorowań.
            if (unique) uniqueColoring.add(combination);
        }
        return uniqueColoring;
    }

    // Metoda do generowania wszystkich kombinacji 0,1 o danej długości
    public static int[][] generateCombinations(int length) {
        int[][] combinations = new int[(int) Math.pow(2, length)][length];

        for (int i = 0; i < combinations.length; i++) {
            for (int j = 0; j < length; j++) {
                int value = (i >> j) & 1;
                combinations[i][j] = value;
            }
        }

        return combinations;
    }

    //Metoda do sprawdzania czy ArrayList<int[]> zawiera daną int[].
    public static boolean containsSameArray(ArrayList<int[]> arrayList, int[] targetArray) {
        for (int[] array : arrayList) {
            if (Arrays.equals(array, targetArray)) {
                return true;
            }
        }

        return false;
    }

    public static void printArrayList(ArrayList<int[]> arrayList) {
        for (int[] array : arrayList) {
            System.out.println(Arrays.toString(array));
        }
    }
}
