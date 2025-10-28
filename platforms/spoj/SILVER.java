import java.io.*;
import java.util.*;

public class SILVER {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) {
                break;
            }

            int ans = 0;
            int max = 1;
            while (max < n) {
                ans++;
                max = max * 2 + 1;
            }
            sb.append(ans).append('\n');
        }
        System.out.print(sb.toString());
        br.close();
    }
}

