import java.util.*;
import java.io.*;

public class Cereal2 {
    public static Set<Integer> comp = new HashSet<>();
    public static Map<Integer, Set<Pair>> graph = new HashMap<>();
    public static Set<Integer> tree = new HashSet<>();
    public static Set<Integer> notInComp = new HashSet<>();
    public static List<Integer> assignedCows = new ArrayList<>();
    public static boolean[] v1 = new boolean[100005];
    public static boolean[] v2 = new boolean[100005];

    public static class Pair {
        int f, s;
        Pair (int f, int s) {
            this.f = f;
            this.s = s;
        }
    

    public static void dfs1(int node) {
        v1[node] = true;
        comp.add(node);

        for (Pair p : graph.getOrDefault(node, new HashSet<>())) {
            if (!v1[p.f]) {
                tree.add(p.s);
                dfs1(p.f);
            } else {
                if (!tree.contains(p.s)) {
                    notInComp.add(p.s);
                }
            }
        }
    }

    public static void dfs2(int node) {
        v2[node] = true;
        for (Pair p : graph.getOrDefault(node, new HashSet<>())) {
            if (!v2[p.f] && tree.contains(p.s)) {
                assignedCows.add(p.s);
                dfs2(p.f);
            }
        }
    }

    public static void helper(int n, int m, int[][] cows) {
        int ans = 0;
        for (int i = 1; i <= m; i++) {
            if (!v1[i]) {
                dfs1(i);
                int root = i;
                if (!notInComp.isEmpty()) {
                    int cow = notInComp.iterator().next();
                    assignedCows.add(cow);
                    notInComp.remove(cow);
                    root = cows[cow][0];
                }

                dfs2(root);
                ans += notInComp.size();
                while (!notInComp.isEmpty()) {
                    assignedCows.add(notInComp.iterator().next());
                    notInComp.remove(notInComp.iterator().next());
                }
            }
        }

        System.out.println(ans);
        for (int i = 0; i < n; i++) {
            System.out.println(assignedCows.get(i));
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] cows = new int[n + 1][2];

        for (int i = 1; i <= n; i++) {
            cows[i][0] = sc.nextInt();
            cows[i][1] = sc.nextInt();
            graph.putIfAbsent(cows[i][0], new HashSet<>());
            graph.putIfAbsent(cows[i][1], new HashSet<>());
            graph.get(cows[i][0]).add(new Pair(cows[i][1], i));
            graph.get(cows[i][1]).add(new Pair(cows[i][0], i));
        }

        helper(n, m, cows);
        sc.close();
    }
}

