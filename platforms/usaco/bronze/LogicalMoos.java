import java.util.*;
import java.io.*;

public class LogicalMoos {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        int[] doneL = new int[200002];
        int[] doneR = new int[200002];
        int[] tArr = new int[200002];
        int[] LLOr = new int[200002];
        int[] RLOr = new int[200002];
        int[] llf = new int[200002];
        int[] rlf = new int[200002];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            String s = st.nextToken();
            if (s.charAt(0) == 'f') {
                tArr[i] = 0;
            }
            if (s.charAt(0) == 't') {
                tArr[i] = 1;
            }
            if (s.charAt(0) == 'a') {
                tArr[i] = 2;
            }
            if (s.charAt(0) == 'o') {
                tArr[i] = 3;
            }
        }

        for (int i = 1; i <= n; i++) {
            if (tArr[i] == 3) {
                LLOr[i] = i;
            } else {
                LLOr[i] = LLOr[i - 1];
            }
        }

        RLOr[n + 1] = n + 1;
        for (int i = n; i >= 1; i--) {
            if (tArr[i] == 3) {
                RLOr[i] = i;
            } else {
                RLOr[i] = RLOr[i + 1];
            }
        }

        llf[0] = -1;
        for (int i = 1; i <= n; i++) {
            if (tArr[i] == 0) {
                llf[i] = i;
            } else {
                llf[i] = llf[i - 1];

            }
        }

        rlf[n + 1] = n + 2;
        for (int i = n; i >= 1; i--) {
            if (tArr[i] == 0) {
                rlf[i] = i;
            } else {
                rlf[i] = rlf[i + 1];
            }
        }
        int ans = 0;
        for (int i = 1; i <= n; ) {
            int j = i + 1;
            int val = tArr[i];
            while (j <= n && tArr[j] != 3) {
                val &= (tArr[j + 1]);
                j += 2;
            }
            if (val == 1)
                ans = 1;
            doneL[j] = ans;
            i = j + 1;
        }

        int ans2 = 0;
        for (int i = n; i >= 1;) {
            int j = i - 1;
            int val = tArr[i];
            while (j >= 1 && tArr[j] != 3) {
                val &= (tArr[j - 1]);
                j -= 2;
            }
            if (val == 1) {
                ans2 = 1;
            }
            doneR[j] = ans2;
            i = j - 1;
        }

        for (int i = 1; i <= q; i++) {
            int L, R;
            String x;
            st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            x = st.nextToken();

            int pL = LLOr[L];
            int pR = RLOr[R];

            if (doneL[pL] != 0 || doneR[pR] != 0) {
                if (x.equals("false")) {
                    System.out.print("N");
                } else {
                    System.out.print("Y");
                }
            } else {
                if (x.equals("false")) {
                    System.out.print("Y");
                } else if (llf[L - 1] >= pL || rlf[R + 1] <= pR) {
                    System.out.print("N");
                } else {
                    System.out.print("Y");
                }
            }
        }
    }
}
~                    

