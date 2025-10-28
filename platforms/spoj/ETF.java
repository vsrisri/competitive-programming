import java.io.*;
import java.util.*;

public class ETF {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] totient = new int[1000001];
        for (int i = 0; i <= 1000000; i++) {
            totient[i] = i;
        }
        for (int i = 2; i <= 1000000; i++) {
            if (totient[i] == i) {
                for (int j = i; j <= 1000000; j += i) {
                    totient[j] -= totient[j] / i;
                }
            }
        }
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            sb.append(totient[n]).append('\n');
        }
        System.out.print(sb);
        br.close();
    }
}

