// USACO 2018 January Contest, Bronze Problem 3. Out of Place
import java.util.*;
import java.io.*;

public class OutOfPlace {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("outofplace.in"));
        PrintWriter pw = new PrintWriter(new File("outofplace.out"));
        int n = sc.nextInt();
        int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
        }

        int badIdx = -1; 
        for (int idx = 1; idx < n; idx++) {
            if (arr[idx] < arr[idx - 1]) {
                badIdx = idx;
                break;
            }

        }
        HashSet<Integer> s = new HashSet<Integer>();
        if (badIdx != -1) {
            if ((badIdx == n - 1) || (badIdx < n - 1 && arr[badIdx - 1] <= arr[badIdx + 1])) {
                int idx = badIdx - 1;
                while (idx >= 0 && arr[idx] > arr[badIdx]) {
                    s.add(arr[idx]);
                    idx--;
                }
            } else {
                int idx = badIdx;
                while (idx < n && arr[idx] < arr[badIdx - 1]) {
                    s.add(arr[idx]);
                    idx++;
                }
            }
        }
        pw.print(s.size());
        sc.close();
        pw.close();
    }
}
