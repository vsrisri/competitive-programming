import java.util.*;
import java.io.*;

public class CircularBarn {
    public static void main(String[] args) throws Exception {
        boolean[] primes = new boolean[5000001];
        int[] arr = new int[4];
        int[] steps = new int[5000001];
        for (int idx = 1; idx <= 5000000; idx++) {
            primes[idx] = true;
        }

        for (int idx = 2; idx <= 5000000; idx++) {
            if (primes[idx]) {
                for (int idx2 = 2 * idx; idx2 <= 5000000; idx2 += idx) {
                    primes[idx2] = false;
                }
            }
        }

        for (int idx = 1; idx <= 5000000; idx++) {
            if (idx % 2 == 0) {
                steps[idx] = idx / 2;
            } else {
                if (primes[idx]) {
                    arr[idx % 4] = idx;
                }
                steps[idx] = 1 + steps[idx - arr[idx % 4]];
            }
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(reader.readLine());
        for (int tc = 0; tc < t; tc++) {
            int n = Integer.parseInt(reader.readLine());
            int[] arr2 = new int[n];
            StringTokenizer st = new StringTokenizer(reader.readLine());
            for (int idx = 0; idx < n; idx++) {
                arr[idx] = Integer.parseInt(st.nextToken());
            }

            boolean j = true;
            int min = 5000001;
            for (int idx = 0; idx < n; idx++) {
                int currSteps = steps[arr2[idx]] / 2;
                if (currSteps < min) {
                    min = currSteps;
                    if (steps[arr2[idx]] % 2 == 1) {
                        j = true;
                    } else {
                        j = false;
                    }
                }
            }
            if (j) {
                System.out.println("Farmer John");
            } else {
                System.out.println("Farmer Nhoj");
            }
        }
        reader.close();
    }
}
