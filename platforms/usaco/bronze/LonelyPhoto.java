// USACO 2021 December Contest, Bronze Problem 1. Lonely Photo
import java.util.*;
import java.io.*;

public class LonelyPhoto {
    public static String str;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        str = sc.nextLine();
        int lonely = 0;
        for (int i = 0; i < str.length(); i++) {
            int gCount = 0;
            int hCount = 0;
            for (int j = i; j < str.length(); j++) {
                if (str.charAt(j) == 'H') {
                    hCount++;
                } else {
                    gCount++;
                }
                if ((gCount + hCount >= 3) && ((gCount == 1) || (hCount == 1))) {
                    lonely++;
                }
            }
        }
        System.out.print(lonely);
        sc.close();
    }
}
