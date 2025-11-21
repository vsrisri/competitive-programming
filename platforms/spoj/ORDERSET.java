import java.io.*;
import java.util.*;

public class ORDERSET {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        int Q = Integer.parseInt(br.readLine());
        int[] type = new int[Q];
        int[] val = new int[Q];
        ArrayList<Integer> coords = new ArrayList<>();
        for (int i = 0; i < Q; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char c = st.nextToken().charAt(0);
            type[i] = c;
            int x = Integer.parseInt(st.nextToken());
            val[i] = x;
            if (c == 'I' || c == 'D' || c == 'C') {
                coords.add(x);
            }
        }

        Collections.sort(coords);
        coords = new ArrayList<>(new LinkedHashSet<>(coords));
        int n = coords.size();
        Fenwick fw = new Fenwick(n);
        int[] pArr = new int[n];
        for (int i = 0; i < Q; i++) {
            char c = (char) type[i];
            int x = val[i];
            if (c == 'I') {
                int idx = Collections.binarySearch(coords, x);
                if (pArr[idx] == 0) {
                    pArr[idx] = 1;
                    fw.update(idx + 1, 1);
                }
            } else if (c == 'D') {
                int idx = Collections.binarySearch(coords, x);
                if (idx >= 0 && pArr[idx] == 1) {
                    pArr[idx] = 0;
                    fw.update(idx + 1, -1);
                }
            } else if (c == 'C') {
                int idx = Collections.binarySearch(coords, x);
                if (idx < 0) {
                    idx = -idx - 1;
                }
                out.append(fw.sum(idx)).append('\n');
            } else { 
                int k = x;
                if (fw.sum(n) < k) {
                    out.append("invalid\n");
                } else {
                    int pos = fw.findKth(k);
                    out.append(coords.get(pos - 1)).append('\n');
                }
            }
        }

        System.out.print(out);
        br.close();
    }

    public static class Fenwick {
        public int n;
        public int[] f;
        public Fenwick(int n) { 
            this.n = n; 
            f = new int[n + 1]; 
        }

        public void update(int i, int v) {
            for (; i <= n; i += i & -i) {
                f[i] += v;
            }
        }

        public int sum(int i) {
            int s = 0;
            for (; i > 0; i -= i & -i) {
                s += f[i];
            }
            return s;
        }

        public int findKth(int k) {
            int pos = 0;
            int bit = Integer.highestOneBit(n);
            while (bit > 0) {
                int next = pos + bit;
                if (next <= n && f[next] < k) {
                    k -= f[next];
                    pos = next;
                }
                bit >>= 1;
            }
            return pos + 1;
        }
    }
}

