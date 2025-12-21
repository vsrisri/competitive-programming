import java.io.*;
import java.util.*;

public class WhyDidCowCross {
    static int[] a, b, pos;
    static int n;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("mincross.in"));
        PrintWriter out = new PrintWriter("mincross.out");
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        a = new int[n];
        b = new int[n];
        pos = new int[n + 1];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 0; i < n; i++) {
            b[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 0; i < n; i++) {
            pos[b[i]] = i;
        }
        int[] mapped = new int[n];
        for (int i = 0; i < n; i++) {
            mapped[i] = pos[a[i]];
        }

        int[] original = Arrays.copyOf(mapped, n);
        BIT bit = new BIT(n);
        long inv = 0;
        for (int i = 0; i < n; i++) {
            inv += bit.query(n - 1) - bit.query(mapped[i]);
            bit.update(mapped[i], 1);
        }
        long minInv = inv;
        for (int i = 0; i < n; i++) {
            inv += n - 1 - 2 * mapped[i];
            if (inv < minInv) minInv = inv;
        }

        Arrays.fill(bit.tree, 0);
        for (int i = 0; i < n; i++) {
            pos[a[i]] = i;
        }
        for (int i = 0; i < n; i++) {
            mapped[i] = pos[b[i]];
        }
        inv = 0;
        for (int i = 0; i < n; i++) {
            inv += bit.query(n - 1) - bit.query(mapped[i]);
            bit.update(mapped[i], 1);
        }
        if (inv < minInv) minInv = inv;
        for (int i = 0; i < n; i++) {
            inv += n - 1 - 2 * mapped[i];
            if (inv < minInv) {
                minInv = inv;
            }
        }

        out.println(minInv);
        out.close();
    }

    public static class BIT {
        long[] tree;
        int size;

        public BIT(int n) {
            size = n;
            tree = new long[n + 2];
        }

        public void update(int i, long delta) {
            i++;
            while (i < tree.length) {
                tree[i] += delta;
                i += i & -i;
            }
        }

        public long query(int i) {
            i++;
            long sum = 0;
            while (i > 0) {
                sum += tree[i];
                i -= i & -i;
            }
            return sum;
        }
    }
}

