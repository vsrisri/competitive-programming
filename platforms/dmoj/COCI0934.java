import java.util.*;
import java.io.*;

public class COCI0934 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = reader.readLine().trim().split("\\s+");
        int n = Integer.parseInt(arr[0]);
        int m = Integer.parseInt(arr[1]);
        int[][] arr2 = new int[n][2];

        for (int i = 0; i < n; i++) {
            String str = reader.readLine();
            int idx = 0;
            for (idx = 0; idx < str.length(); idx++) {
                if (str.charAt(idx) == ' ') {
                    break;
                }
            }

            arr2[i][0] = Integer.parseInt(str.substring(0, idx));
            arr2[i][1] = Integer.parseInt(str.substring(idx + 1, str.length()));
        }

        Arrays.sort(arr2, (a, b) -> Integer.compare(a[0], b[0]));
        int[][] arr3 = new int[n + 1][2];

        for (int i = 0; i < n; i++) {
            arr3[i][0] = arr2[i][0];
            arr3[i][1] = arr2[i][1];
        }
        arr3[n][0] = 0;
        arr3[n][1] = 0;

        long ans = 0;
        for (int idx = 1; idx <= n; idx++) {
            if (arr3[idx - 1][1] > arr3[idx][1]) {
                ans+= arr3[idx - 1][1] - arr3[idx][1];
            }
        }
        System.out.println(ans);
        reader.close();
    }

}
