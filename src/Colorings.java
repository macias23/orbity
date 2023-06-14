import java.util.ArrayList;
import java.util.Arrays;

public class Colorings {
    public static void main(String[] args) {
    int [][] allCombinations = generateCombinations(8);

    int [][][] cubePermutations = {{{1, 2, 3, 4}, {5, 6, 7, 8}},
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
        ArrayList<int[]> uniqueCubeColorings = uniqueColoring(allCombinations,cubePermutations);
        printArrayList(uniqueCubeColorings);
        System.out.println("Liczba kolorowań: " + uniqueCubeColorings.size());

    }

    public static int[] permuteArray(int[] arr, int[][] cycles) {
        int n = arr.length;
        int[] permutedArray = Arrays.copyOf(arr, n); // Tworzenie kopii tablicy wejściowej

        // Iteracja przez każdy cykl w parze cykli
        for (int[] cycle : cycles) {
            int[] adjustedCycle = new int[cycle.length];

            // Dostosowanie indeksów cyklu o 1
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

        return permutedArray; // Zwracanie permutowanej kopii tablicy
    }


    public static ArrayList<int[]> uniqueColoring(int[][] allCombinations, int [][][] permutations){
        ArrayList<int []> uniqueColoring = new ArrayList<>();
        for (int [] combination:allCombinations) {
            boolean unique = true;
            for (int [][] cycle:permutations) {
                int [] afterPerm =  permuteArray(combination,cycle);
                if (containsSameArray(uniqueColoring,afterPerm)) unique = false;
            }
            if (unique) uniqueColoring.add(combination);
        }
        return uniqueColoring;
    }

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
