import java.io.*;
import java.util.*;

public class TheGangs {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int[] parent = new int[n + 1];
        int[] enemy = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String type = st.nextToken();
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            if (type.equals("F")) {
                union(p, q, parent);
            } else {
                if (enemy[p] == 0) {
                    enemy[p] = q;
                } else {
                    union(enemy[p], q, parent);
                }
                if (enemy[q] == 0) {
                    enemy[q] = p;
                } else {
                    union(enemy[q], p, parent);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            if (enemy[i] != 0 && find(i, parent) == find(enemy[i], parent)) {
                System.out.println(0);
                return;
            }
        }

        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (parent[i] == i) {
                count++;
            }
        }
        System.out.println(count);
    }

    public static int find(int x, int[] parent) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x], parent);
    }

    public static void union(int x, int y, int[] parent) {
        int rootX = find(x, parent);
        int rootY = find(y, parent);
        if (rootX != rootY) {
            parent[rootX] = rootY;
        }
    }

}

