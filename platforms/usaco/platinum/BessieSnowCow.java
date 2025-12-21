import java.io.*;
import java.util.*;

public class BessieSnowCow {
    static int N, Q, time;
    static ArrayList<Integer>[] tree;
    static int[] start, end, size;
    static TreeMap<Integer, Integer>[] colorMap;
    static BIT parentBIT, childBIT;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("snowcow.in"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("snowcow.out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        tree = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }

        start = new int[N + 1];
        end = new int[N + 1];
        size = new int[N + 1];
        time = 0;
        dfs(1, 0);
        parentBIT = new BIT(2 * N + 2);
        childBIT = new BIT(2 * N + 2);
        colorMap = new TreeMap[100_005];
        for (int i = 0; i < 100_005; i++) {
            colorMap[i] = new TreeMap<>();
        }

        for (int q = 0; q < Q; q++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            if (type == 1) {
                int x = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                TreeMap<Integer, Integer> map = colorMap[c];
                Integer lower = map.floorKey(start[x]);
                if (lower != null && end[map.get(lower)] >= end[x]) {
                    continue;
                }

                while (true) {
                    Integer upper = map.ceilingKey(start[x]);
                    if (upper == null || start[map.get(upper)] > end[x]) {
                        break;
                    }

                    int u = map.get(upper);
                    parentBIT.update(start[u], -1);
                    parentBIT.update(end[u] + 1, 1);
                    childBIT.update(start[u], -size[u]);
                    map.remove(start[u]);
                }

                map.put(start[x], x);
                parentBIT.update(start[x], 1);
                parentBIT.update(end[x] + 1, -1);
                childBIT.update(start[x], size[x]);
            } else {
                int x = Integer.parseInt(st.nextToken());
                long a = parentBIT.query(start[x]);
                long b = childBIT.query(end[x]) - childBIT.query(start[x] - 1);
                long res = a * size[x] + b;
                bw.write(res + "\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static void dfs(int u, int p) {
        start[u] = ++time;
        size[u] = 1;

        for (int v : tree[u]) {
            if (v != p) {
                dfs(v, u);
                size[u] += size[v];
            }
        }

        end[u] = time;
    }

    public static class BIT {
        long[] tree;
        int n;
        BIT(int n) {
            this.n = n;
            tree = new long[n + 2];
        }

        public void update(int i, long v) {
            while (i < tree.length) {
                tree[i] += v;
                i += i & -i;
            }
        }

        public long query(int i) {
            long res = 0;
            while (i > 0) {
                res += tree[i];
                i -= i & -i;
            }
            return res;
        }
    }
}

