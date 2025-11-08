// USACO 2016 December Contest, Bronze Problem 2. Block Game
import java.util.*;
import java.io.*;

public class BlockGame {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("blocks.in"));
        PrintWriter p = new PrintWriter(new File("blocks.out"));
        int[] ans = new int[26];
        int n = sc.nextInt();
        sc.nextLine();

        for (int idx = 0; idx < n; idx++) {
            String line = sc.nextLine();
            int spaceIdx = line.indexOf(' ');
            String firstWord = line.substring(0, spaceIdx);
            String lastWord = line.substring(spaceIdx + 1, line.length());
            int[] charCount1 = BlockGame.getNumBlocks(firstWord);
            int[] charCount2 = BlockGame.getNumBlocks(lastWord);
            for (int c = 0; c < 26; c++) {
                int max = Math.max(charCount1[c], charCount2[c]);
                ans[c] += max;
            }
        }

        for (int i = 0; i < 26; i++) {
            p.println(ans[i]);
        }
        sc.close();
        p.close();
    }

    public static int[] getNumBlocks(String s) {
        int[] charCount = new int[26];
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (int idx = 0; idx < s.length(); idx++) {
            int i = alphabet.indexOf(s.charAt(idx));
            charCount[i]++;
        }
        return charCount;
    }

}
