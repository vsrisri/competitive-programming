import java.io.*;
import java.util.*;

public class ALIEN {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine().trim());
        for (int tc = 0; tc < t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            long B = Long.parseLong(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int[] arr = new int[A];
            int left = 0, ansLen = 0;
            long sum = 0, ansSum = Long.MAX_VALUE;
            for (int i = 0; i < A; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for (int right = 0; right < A; right++) {
                sum += arr[right];
                while (sum > B) {
                    sum -= arr[left++];
                }

                int len = right - left + 1;
                if (len > ansLen || (len == ansLen && sum < ansSum)) {
                    ansLen = len;
                    ansSum = sum;
                }
            }
            if (ansSum == Long.MAX_VALUE) {
                ansSum = 0;
            }
            sb.append(ansSum).append(" ").append(ansLen).append("\n");
        }
        System.out.print(sb.toString());
        br.close();
    }
}

