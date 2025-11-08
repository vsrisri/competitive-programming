// USACO 2015 February Contest, Bronze Problem 1. Censoring 
import java.util.*;
import java.io.*;

public class Censoring {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("censor.in"));
        PrintWriter p = new PrintWriter(new File("censor.out"));
        String s = sc.nextLine();
        int sLen = s.length();
        String t = sc.nextLine();
        int tLen = t.length();
        String newS = "";
        int newSLen = 0;

        for (int idx = 0; idx < sLen; idx++) {
            newS += s.charAt(idx);
            newSLen++;
            if ((newSLen >= tLen) && (newS.substring(newSLen - tLen, newSLen).equals(t))) {
                newS = newS.substring(0, newSLen - tLen);
                newSLen-= tLen;
            }
        }

        p.print(newS);
        sc.close();
        p.close();
    }


