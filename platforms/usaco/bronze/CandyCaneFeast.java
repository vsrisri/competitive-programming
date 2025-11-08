import java.util.*;
import java.io.*;

public class CandyCaneFeast {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        long[] cowHeights = new long[n];
        long[] candyHeights = new long[m];
        st = new StringTokenizer(reader.readLine(), " ");
        for (int idx = 0; idx < n; idx++) {
            cowHeights[idx] = Long.parseLong(st.nextToken());
        }

        st = new StringTokenizer(reader.readLine(), " ");
        for (int idx = 0; idx < m; idx++) {
            long a = Long.parseLong(st.nextToken());
            candyHeights[idx] = a;
        }

        for (int idx = 0; idx < m; idx++) {
            long eaten = 0;
            for (int idx2 = 0; idx2 < n; idx2++) {
                long eat = Math.min(candyHeights[idx], cowHeights[idx2]) - eaten;
                if (eat <= 0) {
                    continue;
                }
                cowHeights[idx2] += eat;
                eaten += eat;

                if (eaten == candyHeights[idx]) {
                    break;
                }
            }
        }

        for (int idx = 0; idx < n; idx++) {
            System.out.println(cowHeights[idx]);
        }

        reader.close();
    }
}

