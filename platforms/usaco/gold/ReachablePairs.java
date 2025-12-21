import java.util.*;
import java.io.*;

public class ReachablePairs {
    public static final int NMAX = 200002;
    public static int n, m;
    public static int[] rad = new int[NMAX];
    public static int[] card = new int[NMAX];
    public static int[] count = new int[NMAX];
    public static int[] remArr = new int[NMAX];
    public static int[] acRemArr = new int[NMAX];
    public static long total = 0;
    public static int Find(int x) {
        if (rad[x] == x) {
            return x;
        }
        rad[x] = Find(rad[x]);
        return rad[x];
    }

    public static void Union(int a, int b) {
        if (a == b) {
            return;
        }
        if (card[a] < card[b]) {
            int temp = a;
            a = b;
            b = temp;
        }
        rad[b] = a;
        card[a] += card[b];
        total -= 1L * count[a] * (count[a] - 1) / 2;
        total -= 1L * count[b] * (count[b] - 1) / 2;
        count[a] += count[b];
        total += 1L * count[a] * (count[a] - 1) / 2;
    }

    public static void addnode(int a) {
        total += count[a];
        count[a]++;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        String s = sc.next();
        s = " " + s;
        for (int i = 1; i <= n; i++) {
            acRemArr[i] = (s.charAt(i) == '0') ? 1 : 0;
        }

        for (int i = 1; i <= n; i++) {
            rad[i] = i;
            card[i] = 1;
        }

        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            edges.add(new ArrayList<>());
        }
        
        for (int i = 1; i <= m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            edges.get(a).add(b);
            edges.get(b).add(a);
        }

        for (int i = 1; i <= n; i++) {
            for (int x : edges.get(i)) {
                if (acRemArr[i] == 0 && acRemArr[x] == 0) {
                    Union(Find(i), Find(x));
                }
            }
        }

        long[] ans = new long[n + 1];
        for (int i = n; i >= 1; i--) {
            if (acRemArr[i] == 1) {
                acRemArr[i] = 0;
                for (int x : edges.get(i)) {
                    if (acRemArr[i] == 0 && acRemArr[x] == 0) {
                        Union(Find(i), Find(x));
                    }
                }
            }
            addnode(Find(i));
            ans[i] = total;
        }

        for (int i = 1; i <= n; i++) {
            System.out.println(ans[i]);
        }
        
        sc.close();
    }
}

