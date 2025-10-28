import java.util.*;
import java.io.*;

public class MCOINS {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
        int k = Integer.parseInt(st.nextToken()); int l = Integer.parseInt(st.nextToken()); int m = Integer.parseInt(st.nextToken());
        int[] arr  = new int[m];
        st = new StringTokenizer(reader.readLine(), " ");
        for (int idx = 0; idx < m; idx++) {
            arr[idx] = Integer.parseInt(st.nextToken());
        }

        boolean[] didWin = new boolean[1000001];
        didWin = helper(k, l, m, didWin);
        for (int idx = 0; idx < m; idx++) {
            if (didWin[arr[idx]]) {
                System.out.print("A");
            } else {
                System.out.print("B");
            }
        }
        reader.close();
    }

    public static boolean[] helper(int k, int l, int m, boolean[] didWin) {
        for (int idx = 1; idx < 1000001; idx++) {
            if ((idx - 1 >= 0 && !didWin[idx - 1]) || (idx - k >= 0 && !didWin[idx - k]) || (idx - l >= 0 && !didWin[idx - l])) {
                didWin[idx] = true;
            }
        }
        return didWin;
    }
}



