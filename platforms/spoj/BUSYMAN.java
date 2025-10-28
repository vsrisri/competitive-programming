// Incomplete
import java.util.*;
import java.io.*;

public class BUSYMAN {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(reader.readLine());
        for (int t = 0; t < tc; t++) {
            int n = Integer.parseInt(reader.readLine());
            Long[][] arr = new Long[n][2];
            for (int i = 0; i < n; i++) {
                String line = reader.readLine();
                long[] arr2 = lineToArr(line, 2);
                long start = arr2[0];
                long end = arr2[1];
                arr[i][0] = start;
                arr[i][1] = end;
            }

            Arrays.sort(arr, (a, b) -> Long.compare(a[1], b[1]));
            long time = 0;
            long ans = 0;
            for (int idx = 0; idx < n; idx++) {
                if (arr[idx][0] >= time) {
                    time = arr[idx][1];
                    ans++;
                }
            }
            System.out.println(ans);
        }
        reader.close();
    }

    public static long[] lineToArr(String line, int n) {
        long[] arr = new long[n];
        int last = 0;
        int next = 0;
        for (int idx = 0; idx < line.length(); idx++) {
            if (line.charAt(idx) == ' ') {
                arr[next] = Long.parseLong(line.substring(last, idx));
                last = idx + 1;
                next++;
            }
        }
        arr[1] = Long.parseLong(line.substring(last, line.length()));
        return arr;
    }

}
 

