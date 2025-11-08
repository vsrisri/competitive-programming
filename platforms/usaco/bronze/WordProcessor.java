//USACO 2020 January Contest, Bronze Problem 1. Word Processor
import java.util.*;
import java.io.*;

public class WordProcessor {
    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(new File("word.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("word.out"));
        int numWords = s.nextInt();
        int maxNumChars = s.nextInt();
        int numCharRem = maxNumChars;

        String[] words = new String[numWords];
        int idx = 0;
        while (s.hasNext()) {
            words[idx] = s.next();
            idx++;
        }
        idx = 0;

        while (idx < numWords) {
            String subStr = words[idx];
            if (subStr.length() <= numCharRem) {
                if (numCharRem == maxNumChars) {
                    pw.print(subStr);
                } else {
                    pw.print(" " + subStr);
                }
                numCharRem-= subStr.length();
            } else {
                pw.print("\n"+ subStr);
                numCharRem = maxNumChars - subStr.length();
            }
            System.out.println(words[idx] + " " + numCharRem);
            idx++;
        }
        pw.close();
        s.close();
    }
}
