import java.util.*;
import java.io.*;

public class HappyAlpacas {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        Arrays.fill(arr, 0);
        if ((n - x) % 2 != 0) {
            System.out.println(-1);
        } else if (((n - x) % 2 == 0) && ((n - x) > 0)) {
            int counter = 1;
            int lastIdx = 0;
            arr[0] = 1;

            while ((counter < (n - x) / 2) && lastIdx < n) {
                arr[lastIdx + 2] = 1;
                counter++; lastIdx+= 2;
            }
            helper(arr);
        } else if (((n - x) % 2 == 0) && ((n - x) == 0)) {
            helper(arr);
        }
    }
    public static void helper(int[] arr) {
        for (int idx = 0; idx < arr.length - 1; idx++) {
            System.out.print(arr[idx] + " ");
        }
        System.out.print(arr[arr.length - 1]);
        System.out.println();
    }


}
