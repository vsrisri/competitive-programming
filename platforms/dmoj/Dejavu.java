import java.util.*;
import java.io.*;

public class Dejavu {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int[] x = new int[n];
        int[] y = new int[n];
        int[] xCount = new int[100001];
        int[] yCount = new int[100001];
        long ans = 0;

        for (int idx = 0; idx < n; idx++) {
            st = new StringTokenizer(reader.readLine(), " ");
            x[idx] = Integer.parseInt(st.nextToken());
            xCount[x[idx]]++;
            y[idx] = Integer.parseInt(st.nextToken());
            yCount[y[idx]]++;
        }

        for (int idx = 0; idx < n; idx++) {
            ans += ((long) (xCount[x[idx]] - 1)) * ((long) (yCount[y[idx]] - 1));
        }

        System.out.println(ans);
        reader.close();
    }
}
