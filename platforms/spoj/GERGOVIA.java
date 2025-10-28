import java.util.*;
import java.io.*;

public class GERGOVIA {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        while (n > 0) {
            int[] arr = lineToArr(reader.readLine(), n);
            long ans = 0;

            for (int idx = 1; idx < n; idx++) {
                arr[idx] += arr[idx - 1];
                ans+= Math.abs(arr[idx - 1]);
            }

            System.out.println(ans);
            n = Integer.parseInt(reader.readLine());
        }
        reader.close();
    }

    public static int[] lineToArr(String line, int n) {
        int[] arr = new int[n];
        int last = 0;
        int next = 0;
        for (int idx = 0; idx < line.length(); idx++) {
            if (line.charAt(idx) == ' ') {
                arr[next] = Integer.parseInt(line.substring(last, idx));
                last = idx + 1;
                next++;
            }
        }
        return arr;
    }
}
