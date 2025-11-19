import java.io.*;
import java.util.*;

public class WORDS1 {
    public static int[] parent = new int[26];
    public static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        parent[x] = find(parent[x]);
        return parent[x];
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[a] = b;
        }
    }

    public public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder out = new StringBuilder();
        for (int tc = 0; tc < t; tc++) {
            int n = Integer.parseInt(br.readLine());
            int[] in = new int[26];
            int[] outd = new int[26];
            boolean[] used = new boolean[26];
            boolean ok = true;
            int comp = -1;
            for (int i = 0; i < 26; i++) {
                parent[i] = i;
            }
            for (int i = 0; i < n; i++) {
                String s = br.readLine();
                int a = s.charAt(0) - 'a';
                int b = s.charAt(s.length() - 1) - 'a';
                outd[a]++;
                in[b]++;
                used[a] = true;
                used[b] = true;
                union(a, b);
            }

            for (int i = 0; i < 26; i++) {
                if (used[i]) {
                    int f = find(i);
                    if (comp == -1) {
                        comp = f;
                    } else if (f != comp) {
                        ok = false;
                        break;
                    }
                }
            }

            if (ok) {
                int c1 = 0;
                int c2 = 0;
                for (int i = 0; i < 26; i++) {
                    int d = outd[i] - in[i];
                    if (d == 1) {
                        c1++;
                    } else if (d == -1) {
                        c2++;
                    } else if (d != 0) {
                        ok = false;
                        break;
                    }
                }
                if (!((c1 == 1 && c2 == 1) || (c1 == 0 && c2 == 0))) {
                    ok = false;
                }
            }
            if (ok) {
                out.append("Ordering is possible.\n");
            } else {
                out.append("The door cannot be opened.\n");
            }
        }

        System.out.print(out.toString());
        br.close();
    }
}

