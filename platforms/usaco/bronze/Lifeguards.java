//USACO 2018 January Contest, Bronze Problem 2. Lifeguards
import java.util.*;
import java.io.*;

public class Lifeguards {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("lifeguards.in"));
        PrintWriter p = new PrintWriter(new File("lifeguards.out"));
        int numGuards = sc.nextInt();
        int[][] inArr = new int[numGuards][2];
        sc.nextLine();
        int[] timeGrid = new int[1000];
        int numFilled = 0;
        int max = 0;
        for (int idx = 0; idx < numGuards; idx++) {
            String line = sc.nextLine();
            int spaceIdx = line.indexOf(' ');
            int start = Integer.parseInt(line.substring(0, spaceIdx));
            int end = Integer.parseInt(line.substring(spaceIdx + 1, line.length()));
            inArr[idx][0] = start;
            inArr[idx][1] = end;
        }
        for (int skip = 0; skip < numGuards; skip++) {
            for (int idx = 0; idx < numGuards; idx++) {
                if (idx == skip) {
                    continue;
                }

                for (int idx2 = inArr[idx][0] - 1; idx2 < inArr[idx][1] - 1; idx2++) {
                    if (timeGrid[idx2] == 0) {
                    timeGrid[idx2] = 1;
                    numFilled++;
                    }
                }
                
                if (numFilled > max) {
                    max = numFilled;
                }
            }
            numFilled = 0;
            timeGrid = new int[1000];
        }
        p.print(max);
        sc.close();
        p.close();
    }
}
