import java.util.*;
import java.io.*;

public class PickingFlowers {
    public static int[] level, state;
    public static boolean[] isFlower, isDest;
    public static List<List<Integer>> adj;
    public static boolean helper(int node, int lim) {
        if (level[node] == lim) {
            if (!isFlower[node]) {
                state[node] = 1;
                return false;
            } else {
                state[node] = 0;
                return true;
            }
        }
        if (state[node] == 0) {
            return true;
        }
        if (state[node] == 1) {
            return false;
        }
        boolean found = false;
        for (int nb : adj.get(node)) {
            if (level[nb] > level[node]) {
                if (helper(nb, lim)) {
                    found = true;
                    state[node] = 0;
                }
            }
        }
        if (!found) {
            state[node] = 1;
        }
        return found;
    }

    public static boolean reach(int node) {
        if (state[node] == 1) {
            return false;
        }
        if (state[node] == 0) {
            return true;
        }
        boolean childReach = false;
        for (int nb : adj.get(node)) {
            if (level[nb] > level[node]) {
                if (reach(nb)) {
                    childReach = true;
                }
            }
        }
        boolean good = isDest[node] || childReach;
        state[node] = good ? 0 : 1;
        return good;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            isFlower = new boolean[n];
            isDest = new boolean[n];

            if (k > 0) {
                st = new StringTokenizer(br.readLine());
                for (int i = 0; i < k; i++) {
                    isFlower[Integer.parseInt(st.nextToken()) - 1] = true;
                }
            } else {
                br.readLine();
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < l; i++) {
                isDest[Integer.parseInt(st.nextToken()) - 1] = true;
            }

            adj = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                adj.add(new ArrayList<>());
            }
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;
                adj.get(a).add(b);
                adj.get(b).add(a);
            }

            level = new int[n];
            Arrays.fill(level, -1);
            level[0] = 0;
            Queue<Integer> bfsQ = new ArrayDeque<>();
            bfsQ.add(0);
            while (!bfsQ.isEmpty()) {
                int node = bfsQ.poll();
                for (int nb : adj.get(node)) {
                    if (level[nb] == -1) {
                        level[nb] = level[node] + 1;
                        bfsQ.add(nb);
                    }
                }
            }

            state = new int[n];
            Arrays.fill(state, 2);

            List<int[]> flowLvl = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (isFlower[i]) {
                    flowLvl.add(new int[]{level[i], i});
                }
            }
            flowLvl.add(new int[]{0, 0});
            flowLvl.sort(Comparator.comparingInt(a -> a[0]));

            boolean bad = false;
            for (int i = 0; i < flowLvl.size() - 1; i++) {
                if (flowLvl.get(i)[0] == flowLvl.get(i + 1)[0]) {
                    bad = true;
                    break;
                }
            }

            if (bad) {
                for (int i = 0; i < n - 1; i++) {
                    sb.append('0');
                }
                sb.append('\n');
                continue;
            }

            boolean pathBad = false;
            for (int i = 0; i < flowLvl.size() - 1; i++) {
                int from = flowLvl.get(i)[1];
                int toLvl = flowLvl.get(i + 1)[0];
                int to = flowLvl.get(i + 1)[1];
                if (!helper(from, toLvl)) {
                    pathBad = true;
                    break;
                }
                state[to] = 2;
            }

            if (pathBad) {
                for (int i = 0; i < n - 1; i++) {
                    sb.append('0');
                }
                sb.append('\n');
                continue;
            }

            int last = flowLvl.get(flowLvl.size() - 1)[1];
            if (!reach(last)) {
                for (int i = 0; i < n - 1; i++) {
                    sb.append('0');
                }
                sb.append('\n');
                continue;
            }

            for (int i = 1; i < n; i++) {
                sb.append(state[i] == 0 ? '1' : '0');
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
}
