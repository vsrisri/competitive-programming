import java.io.*;
import java.util.*;

public class StrongFriendGroup {
    public static class DSU {
        int[] parent;

        public DSU(int n) {
            parent = new int[n];
            Arrays.fill(parent, -1);
        }

        int find(int x) {
            if (parent[x] < 0) {
                return x;
            }
            return parent[x] = find(parent[x]);
        }

        boolean unite(int x, int y) {
            x = find(x);
            y = find(y);
            if (x == y) {
                return false;
            }
            if (parent[x] > parent[y]) {
                int temp = x;
                x = y;
                y = temp;
            }
            parent[x] += parent[y];
            parent[y] = x;
            return true;
        }

        int size(int x) {
            return -parent[find(x)];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int cowCount = Integer.parseInt(tokenizer.nextToken());
        int friendCount = Integer.parseInt(tokenizer.nextToken());
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= cowCount; i++) {
            graph.add(new ArrayList<>());
        }

        int[] degree = new int[cowCount + 1];
        for (int i = 0; i < friendCount; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int u = Integer.parseInt(tokenizer.nextToken());
            int v = Integer.parseInt(tokenizer.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
            degree[u]++;
            degree[v]++;
        }

        TreeSet<int[]> active = new TreeSet<>((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        for (int i = 1; i <= cowCount; i++) {
            active.add(new int[]{graph.get(i).size(), i});
        }

        int[] minDeg = new int[cowCount];
        int[] rem = new int[cowCount];
        boolean[] inComp = new boolean[cowCount + 1];
        Arrays.fill(inComp, true);

        for (int i = 0; i < cowCount; i++) {
            int[] cow = active.pollFirst();
            minDeg[i] = cow[0];
            rem[i] = cow[1];
            inComp[cow[1]] = false;

            for (int neigh : graph.get(cow[1])) {
                if (inComp[neigh]) {
                    active.remove(new int[]{degree[neigh], neigh});
                    degree[neigh]--;
                    active.add(new int[]{degree[neigh], neigh});
                }
            }
        }

        revArray(minDeg);
        revArray(rem);
        long ans = 0;
        int maxSize = 1;
        DSU dsu = new DSU(cowCount + 1);
        Arrays.fill(inComp, false);
        for (int i = 0; i < cowCount; i++) {
            int u = rem[i];
            for (int v : graph.get(u)) {
                if (inComp[v]) {
                    dsu.unite(u, v);
                }
            }
            inComp[u] = true;
            maxSize = Math.max(maxSize, dsu.size(u));
            ans = Math.max(ans, 1L * minDeg[i] * maxSize);
        }

        System.out.println(ans);
    }

    public static void revArray(int[] array) {
        for (int i = 0, j = array.length - 1; i < j; i++, j--) {
            int tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }
}

