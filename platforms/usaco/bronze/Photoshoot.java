// USACO 2020 January Contest, Bronze Problem 2. Photoshoot
import java.util.*;
import java.io.*;

public class Photoshoot {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("photo.in"));
        PrintWriter p = new PrintWriter(new File("photo.out"));
        int n = sc.nextInt();
        int[] bArr = new int[n-1];

        for (int i = 0; i < n-1; i++) {
            bArr[i] = sc.nextInt();
        }

        for (int first = 1; first < bArr[0]; first++) {
            int[] currPerm = new int[n];
            int idx = 1;
            currPerm[0] = first;
            int prev = bArr[0] - currPerm[0];
            while (idx <= n - 2) {
                currPerm[idx] = prev;
                prev = bArr[idx] - currPerm[idx];
                idx++;
            }
            currPerm[currPerm.length - 1] = prev;

            if (Photoshoot.isValid(currPerm)) {
                p.print(currPerm[0]);
                for (int i = 1; i < n; i++) {
                    p.print(" " + currPerm[i]);
                }
            }
        }
        sc.close();
        p.close();
    }

    public static boolean isValid(int[] arr) {
        int n = arr.length;
        boolean[] isPresent = new boolean[n];
        for (int idx = 0; idx < n; idx++) {
            if (arr[idx] > n || arr[idx] <= 0 || isPresent[arr[idx] - 1] == true) {
                return false;
            } else {
                isPresent[arr[idx] - 1] = true;
            }
        }
        return true;
    }
}
