import java.io.*;
import java.util.*;

public class NOR {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(reader.readLine());
        for (int idx = 0; idx < N; idx++) {
            A[idx] = Integer.parseInt(st.nextToken());
        }

        int q = Integer.parseInt(reader.readLine());
        int[] lastOneidx = new int[N];
        int l = -1;
        for (int idx = 0; idx < N; idx++) {
            if (A[idx] == 1) {
                l = idx;
            }
            lastOneidx[idx] = l;
        }

        for (int idx = 0; idx < q; idx++) {
            st = new StringTokenizer(reader.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int last = lastOneidx[y];
            if (last == y) {
                System.out.println(0);
            } else if (last == x) {
                if ((y - x) % 2 == 0) {
                    System.out.println(1);
                } else {
                    System.out.println(0);
                }
            } else if (last < x) {
                if ((1 + y - x) % 2 == 0) {
                    System.out.println(1);
                } else {
                    System.out.println(0);
                }
            } else if (last > x && last < y) {
                if ((y - last) % 2 == 0) {
                    System.out.println(0);
                } else {
                    System.out.println(1);
                }
            }
        }
        reader.close();
    }
}
