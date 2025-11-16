import java.io.*;
import java.util.*;

public class M3TILE {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long[] arr = new long[31];
        arr[0] = 1;
        arr[2] = 3;
        for (int i = 4; i <= 30; i += 2) {
            arr[i] = 4 * arr[i - 2] - arr[i - 4];
        }
        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.equals("-1")) {
                break;
            }
            int n = Integer.parseInt(line);
            System.out.println(arr[n]);
        }
        br.close();

    }
}

