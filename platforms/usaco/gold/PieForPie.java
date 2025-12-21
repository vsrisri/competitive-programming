import java.io.*;
import java.util.*;

public class PieForPie {
    public static class Node {
        boolean fromB;
        int id, steps;
        Node(boolean f, int i, int s) {
            fromB = f;
            id = i;
            steps = s;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("piepie.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("piepie.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int[] bSelf = new int[n];
        int[] bOther = new int[n];
        int[] eSelf = new int[n];
        int[] eOther = new int[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            bSelf[i] = Integer.parseInt(st.nextToken());
            bOther[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            eSelf[i] = Integer.parseInt(st.nextToken());
            eOther[i] = Integer.parseInt(st.nextToken());
        }

        TreeMap<Integer, List<Integer>> remB = new TreeMap<>();
        TreeMap<Integer, List<Integer>> remE = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            if (bOther[i] != 0) {
                remB.computeIfAbsent(bOther[i], k -> new ArrayList<>()).add(i);
            }
            if (eSelf[i] != 0) {
                remE.computeIfAbsent(eSelf[i], k -> new ArrayList<>()).add(i);
            }
        }

        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        Deque<Node> dq = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (bOther[i] == 0) {
                dq.add(new Node(true, i, 1));
            }
            if (eSelf[i] == 0) {
                dq.add(new Node(false, i, 1));
            }
        }

        while (!dq.isEmpty()) {
            Node cur = dq.poll();
            if (cur.fromB) {
                if (ans[cur.id] == -1) {
                    ans[cur.id] = cur.steps;
                }

                Integer key = remE.ceilingKey(bSelf[cur.id] - d);
                List<Integer> rmKeys = new ArrayList<>();
                while (key != null && key <= bSelf[cur.id]) {
                    for (int j : remE.get(key)) {
                        dq.add(new Node(false, j, cur.steps + 1));
                    }
                    rmKeys.add(key);
                    key = remE.higherKey(key);
                }
                for (int k : rmKeys) {
                    remE.remove(k);
                }
            } else {
                Integer key = remB.ceilingKey(eOther[cur.id] - d);
                List<Integer> rmKeys = new ArrayList<>();
                while (key != null && key <= eOther[cur.id]) {
                    for (int j : remB.get(key)) {
                        dq.add(new Node(true, j, cur.steps + 1));
                    }
                    rmKeys.add(key);
                    key = remB.higherKey(key);
                }
                for (int k : rmKeys) {
                    remB.remove(k);
                }
            }
        }

        for (int v : ans) {
            out.println(v);
        }
        out.close();
    }
}

