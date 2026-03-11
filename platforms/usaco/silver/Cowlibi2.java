import java.io.*;
import java.util.*;

public class Cowlibi2 {
    public static char[] L;
    public static char[] R;
    public static int N;
    public static int[] pArr;
    public static boolean[] curr;

    public static boolean helperCheck() {
        int leftJ = 0;
        int rightJ = 0;
        int leftN = 0;
        ArrayList<Integer>[][] types = new ArrayList[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                types[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < N; i++) {
            if (L[i] == 'J') {
                leftJ++;
            }
            if (R[i] == 'J') {
                rightJ++;
            }
            if (L[i] == 'N') {
                leftN++;
            }

            int a = (L[i] == 'N') ? 1 : 0;
            int b = (R[i] == 'N') ? 1 : 0;

            types[a][b].add(i);
        }

        if (leftJ != rightJ) {
            return false;
        }

        if (leftN % 2 != 0) {
            return false;
        }

        if (!types[0][0].isEmpty() && !types[1][1].isEmpty() && types[0][1].isEmpty() && types[1][0].isEmpty()) {
            return false;
        }

        pArr = new int[N];
        if (types[0][0].size() == N || types[1][1].size() == N) {
            for (int i = 0; i < N; i++) {
                pArr[i] = i;
            }
        }
        else {
            int idx = 0;
            for (int v : types[0][0]) {
                pArr[idx++] = v;
            }

            int t = types[0][1].remove(types[0][1].size() - 1);
            pArr[idx++] = t;

            for (int v : types[1][1]) {
                pArr[idx++] = v;
            }

            while (!types[0][1].isEmpty() && !types[1][0].isEmpty()) {
                int a = types[1][0].remove(types[1][0].size() - 1);
                pArr[idx++] = a;

                int b = types[0][1].remove(types[0][1].size() - 1);
                pArr[idx++] = b;
            }

            int last = types[1][0].remove(types[1][0].size() - 1);
            pArr[idx++] = last;
        }

        curr = new boolean[N];
        int side = 0;
        curr[pArr[0]] = true;
        for (int i = 1; i < N; i++) {
            if (L[pArr[i]] == 'N') {
                side = 1 - side;
            }

            if (side == 0) {
                curr[pArr[i]] = true;
            }
            else {
                curr[pArr[i]] = false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        StringBuilder out = new StringBuilder();
        for (int tc = 0; tc < T; tc++) {
            N = Integer.parseInt(br.readLine());
            L = br.readLine().toCharArray();
            R = br.readLine().toCharArray();
            boolean ok = helperCheck();
            if (!ok) {
                out.append("NO\n");
            }
            else {
                out.append("YES\n");
                if (C == 1) {
                    for (int i = 0; i < N; i++) {
                        out.append(pArr[i] + 1);

                        if (i + 1 < N) {
                            out.append(" ");
                        }
                    }

                    out.append("\n");
                    for (int i = 0; i < N; i++) {
                        if (curr[pArr[i]]) {
                            out.append('J');
                        }
                        else {
                            out.append('N');
                        }
                    }

                    out.append("\n");
                }
            }
        }

        System.out.print(out.toString());
    }
}

