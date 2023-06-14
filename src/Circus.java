import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Circus {
    public static void main(String[] args) {
        int[][] bitPermutations = {
                {2, 0, 1}, {1, 2, 0}, {0, 1, 2}, {1, 0, 2}, {0, 2, 1}
        };
        int[][] inputPermutations = new int[5][8];
        for (int i = 0; i < bitPermutations.length; i++) {
            for (int n = 0; n < 8; n++) {
                int[] bitPermutation = bitPermutations[i];
                int newIndex = 4 * (n >> bitPermutation[0] & 1) + 2 * (n >> bitPermutation[1] & 1) + (n >> bitPermutation[2] & 1);
                inputPermutations[i][n] = newIndex;
            }
        }
        HashSet<Integer> functions = new HashSet<>();
        ArrayList<Integer> necessary = new ArrayList<>();
        for (int function = 0; function < 256; function++) {
            if (functions.contains(function))
                continue;
            functions.add(function);
            necessary.add(function);
            for (int p = 0; p < inputPermutations.length; p++) {
                int newFunction = 0;
                for (int i = 0; i < 8; i++) {
                    newFunction |= (function >> (7 - inputPermutations[p][i]) & 1) << (7 - i);
                }
                functions.add(newFunction);
            }
        }
        System.out.println(necessary.size());
        for (int i:necessary) {
            String circuit = Integer.toBinaryString(i);
            StringBuilder sb = new StringBuilder(circuit);
            while (sb.length()<8) sb.insert(0,0);
            System.out.println(sb.toString());
        }
    }
}
