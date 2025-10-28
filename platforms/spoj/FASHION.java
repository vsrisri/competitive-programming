import java.util.*;
import java.io.*;

public class FASHION {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        int tc = stdin.nextInt();
        for (int t = 0; t < tc; t++) {
            int n = stdin.nextInt();
            int[] arr = new int[n];
            int[] arr2 = new int[n];
            for (int idx = 0; idx < n; idx++) {
                arr[idx] = stdin.nextInt();
            }

            for (int idx = 0; idx < n; idx++) {
                arr2[idx] = stdin.nextInt();
            }
            Arrays.sort(arr);
            Arrays.sort(arr2);
            int ans = 0;
            for (int idx = 0; idx < n; idx++) {
                ans+= (arr[idx] * arr2[idx]);
            }
            System.out.println(ans);
        }
        stdin.close();

    }
}

