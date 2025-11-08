import java.io.*;
import java.util.*;

public class Blocks {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        int numCases = stdin.nextInt();
        String[] words = new String[numCases];
        String[] die1 = makeInArr(stdin.next());
        String[] die2 = makeInArr(stdin.next());
        String[] die3 = makeInArr(stdin.next());
        String[] die4 = makeInArr(stdin.next());
        // System.out.println(die1.length + " " + die2.length + " " + die3.length + " " + die4.length);

        for (int idx = 0; idx < numCases; idx++) {
            words[idx] = stdin.next();
        }

        HashSet<String> possWords = new HashSet<String>();
        for (int d1 = 0; d1 < 7; d1++) {
            for (int d2 = 0; d2 < 7; d2++) {
                for (int d3 = 0; d3 < 7; d3++) {
                    for (int d4 = 0; d4 < 7; d4++) {
                        String curr = die1[d1] + die2[d2] + die3[d3] + die4[d4];
                        possWords.add(curr);
                    }
                }
            }
        }
        possWords.remove("");

        for (int idx = 0; idx < numCases; idx++) {
            outputHelper(words[idx], possWords);
        }

        stdin.close();

    }

    public static void outputHelper(String currWord, HashSet<String> possWords) {
        if (possWords.contains(currWord)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    public static String[] makeInArr(String line) {
        String[] arr = new String[7];
        for (int i = 0; i < 6; i++) {
            arr[i] = Character.toString(line.charAt(i));
        }
        arr[6] = "";
        return arr;
    }
}
