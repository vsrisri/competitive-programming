import java.io.*;
import java.util.*;

public class BRCKTS {
    public static class SegmentTree {
        int[] open;
        int[] close;
        int[] balance;
        int n;
        public SegmentTree(int n) {
            this.n = n;
            open = new int[4 * n];
            close = new int[4 * n];
            balance = new int[4 * n];
        }

        public void build(String s, int node, int start, int end) {
            if (start == end) {
                if (s.charAt(start) == '(') {
                    open[node] = 1;
                    close[node] = 0;
                } else {
                    open[node] = 0;
                    close[node] = 1;
                }
                balance[node] = open[node] - close[node];
            } else {
                int mid = (start + end) / 2;
                build(s, 2 * node, start, mid);
                build(s, 2 * node + 1, mid + 1, end);
                merge(node);
            }
        }

        public void merge(int node) {
            int left = 2 * node;
            int right = 2 * node + 1;
            int match = Math.min(open[left], close[right]);
            open[node] = open[left] + open[right] - match;
            close[node] = close[left] + close[right] - match;
            balance[node] = open[node] - close[node];
        }

        public void update(int node, int start, int end, int idx) {
            if (start == end) {
                open[node] = (open[node] == 0) ? 1 : 0;
                close[node] = (close[node] == 0) ? 1 : 0;
                balance[node] = open[node] - close[node];
            } else {
                int mid = (start + end) / 2;
                if (idx <= mid) {
                    update(2 * node, start, mid, idx);
                } else {
                    update(2 * node + 1, mid + 1, end, idx);
                }
                merge(node);
            }
        }

        public  boolean isBalanced() {
            return balance[1] == 0 && open[1] == 0 && close[1] == 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int test = 1; test <= 10; test++) {
            int n = Integer.parseInt(br.readLine());
            String s = br.readLine();
            int m = Integer.parseInt(br.readLine());
            SegmentTree stree = new SegmentTree(n);
            stree.build(s, 1, 0, n - 1);
            System.out.println("Test " + test + ":");
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int op = Integer.parseInt(st.nextToken());
                if (op == 0) {
                    if (stree.isBalanced()) {
                        System.out.println("YES");
                    } else {
                        System.out.println("NO");
                    }
                } else {
                    stree.update(1, 0, n - 1, op - 1);
                }
            }
        }
    }
}

