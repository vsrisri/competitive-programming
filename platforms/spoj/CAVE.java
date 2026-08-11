import java.util.*;
import java.io.*;

public class CAVE {
    public static final int N = 0, E = 1, S = 2, W = 3;
    public static class Node {
        int x, y;
        Node[] adj = new Node[4];
        int corrH = -1, corrV = -1;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line).append('\n');
        }

        String input = sb.toString();
        StringTokenizer st = new StringTokenizer(input);
        int T = Integer.parseInt(st.nextToken());
        StringBuilder out = new StringBuilder();
        for (int tc = 0; tc < T; tc++) {
            int n = Integer.parseInt(st.nextToken());
            char[] type = new char[n];
            int[] c1 = new int[n];
            int[] c2a = new int[n];
            int[] c2b = new int[n];
            TreeSet<Integer>[] ranges = new TreeSet[n];
            for (int i = 0; i < n; i++) {
                String tstr = st.nextToken();
                char tp = tstr.charAt(0);
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                type[i] = tp;
                c1[i] = a;
                int lo = Math.min(b, c);
                int hi = Math.max(b, c);
                c2a[i] = lo;
                c2b[i] = hi;
                ranges[i] = new TreeSet<>();
                ranges[i].add(lo);
                ranges[i].add(hi);
            }

            for (int i = 0; i < n; i++) {
                if (type[i] != 'H') {
                    continue;
                }

                int Y = c1[i], hx1 = c2a[i], hx2 = c2b[i];
                for (int j = 0; j < n; j++) {
                    if (type[j] != 'V') {
                        continue;
                    }

                    int X = c1[j], vy1 = c2a[j], vy2 = c2b[j];
                    if (X >= hx1 && X <= hx2 && Y >= vy1 && Y <= vy2) {
                        ranges[i].add(X);
                        ranges[j].add(Y);
                    }
                }
            }

            int ex = Integer.parseInt(st.nextToken());
            int ey = Integer.parseInt(st.nextToken());
            String dstr = st.nextToken();
            char dch = dstr.charAt(0);
            for (int i = 0; i < n; i++) {
                if (type[i] == 'H') {
                    if (c1[i] == ey && ex >= c2a[i] && ex <= c2b[i]) {
                        ranges[i].add(ex);
                        break;
                    }
                }
            }

            boolean foundH = false;
            for (int i = 0; i < n; i++) {
                if (type[i] == 'H' && c1[i] == ey && ex >= c2a[i] && ex <= c2b[i]) {
                    foundH = true;
                }
            }

            if (!foundH) {
                for (int i = 0; i < n; i++) {
                    if (type[i] == 'V') {
                        if (c1[i] == ex && ey >= c2a[i] && ey <= c2b[i]) {
                            ranges[i].add(ey);
                            break;
                        }
                    }
                }
            }

            HashMap<Long, Node> nodeMap = new HashMap<>();
            for (int i = 0; i < n; i++) {
                List<Integer> pts = new ArrayList<>(ranges[i]);
                if (type[i] == 'H') {
                    int y = c1[i];
                    for (int k = 0; k + 1 < pts.size(); k++) {
                        int xa = pts.get(k), xb = pts.get(k + 1);
                        Node A = getH(nodeMap, xa, y);
                        Node B = getH(nodeMap, xb, y);
                        A.adj[E] = B;
                        A.corrH = i;
                        B.adj[W] = A;
                        B.corrH = i;
                    }
                } else {
                    int x = c1[i];
                    for (int k = 0; k + 1 < pts.size(); k++) {
                        int ya = pts.get(k), yb = pts.get(k + 1);
                        Node A = getH(nodeMap, x, ya);
                        Node B = getH(nodeMap, x, yb);
                        A.adj[N] = B;
                        A.corrV = i;
                        B.adj[S] = A;
                        B.corrV = i;
                    }
                }
            }

            boolean[] visited = new boolean[n];
            Node entryNode = getH(nodeMap, ex, ey);
            mHelper(entryNode, visited);
            int d;
            switch (dch) {
                case 'N':
                    d = N;
                    break;
                case 'E':
                    d = E;
                    break;
                case 'S':
                    d = S;
                    break;
                default:
                    d = W;
                    break;
            }

            Node curr = entryNode;
            while (true) {
                Node next = curr.adj[d];
                curr = next;
                mHelper(curr, visited);
                if (curr == entryNode) {
                    break;
                }

                int nd = -1;
                int[] order = { (d + 3) % 4, d, (d + 1) % 4, (d + 2) % 4 };
                for (int c : order) {
                    if (curr.adj[c] != null) {
                        nd = c;
                        break;
                    }
                }

                d = nd;
            }

            int count = 0;
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    count++;
                }
            }

            out.append(count).append('\n');
        }

        System.out.print(out);
    }

    public static Node getH(HashMap<Long, Node> map, int x, int y) {
        long key = (((long) (x + 40000)) << 20) | (long) (y + 40000);
        Node nd = map.get(key);

        if (nd == null) {
            nd = new Node(x, y);
            map.put(key, nd);
        }

        return nd;
    }

    public static void mHelper(Node nd, boolean[] visited) {
        if (nd.corrH >= 0) {
            visited[nd.corrH] = true;
        }

        if (nd.corrV >= 0) {
            visited[nd.corrV] = true;
        }
    }
}
