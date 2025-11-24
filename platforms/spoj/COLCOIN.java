// Incomplete
import java.util.*;
import java.io.*;

public class COLCOIN {
    public static void main(String[] args) throws Exception {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int tc = Integer.parseInt(reader.readLine());
            for (int t = 1; t <= tc; t++) {

                int n = Integer.parseInt(reader.readLine());
                StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
                int[] arr = new int[n];
                for (int idx = 0; idx < n; idx++) {
                    arr[idx] = Integer.parseInt(st.nextToken());
                }

                Arrays.sort(arr);
                StringBuilder str = new StringBuilder();
                int ans = helper(arr);
                str.append("Case #");
                str.append(t);
                str.append(": ");
                str.append(ans);
                str.append("\n");
                System.out.print(str);
            }   
            reader.close();
        } catch (Exception e) {
            return; 
        }       
    }           

    public static int helper(int[] arr) {
        if (arr.length <= 2) {
            return arr.length;
        }

        int toWithdraw = arr[0], ans = 1;
        for (int idx = 1; idx < arr.length; idx++) {
            int next = (idx + 1 < arr.length ? arr[idx + 1] : Integer.MAX_VALUE);
            if (toWithdraw + arr[idx] < next) {
                toWithdraw += arr[idx];
                ans++;
            }
        }

        return ans;
    }
}

