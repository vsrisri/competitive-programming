import java.io.*;

public class GSS1 {
    static class SegmentTree {
        public class Node {
            int totSum, maxPrefSum, maxSufSum, maxSubarrSum;

            Node(int totSum, int maxPrefSum, int maxSufSum, int maxSubarrSum) {
                this.totSum = totSum;
                this.maxPrefSum = maxPrefSum;
                this.maxSufSum = maxSufSum;
                this.maxSubarrSum = maxSubarrSum;
            }
        }

        public Node[] tree;
        public int[] arr;
        public int n;

        public SegmentTree(int[] arr) {
            this.arr = arr;
            this.n = arr.length;
            this.tree = new Node[2 * n];
            build();
        }

        public void build() {
            for (int i = 0; i < n; i++) {
                int value = arr[i];
                tree[n + i] = new Node(value, value, value, value);
            }
            for (int i = n - 1; i > 0; i--) {
                tree[i] = merge(tree[2 * i], tree[2 * i + 1]);
            }
        }

        public Node merge(Node left, Node right) {
            int totSum = left.totSum + right.totSum;
            int maxPrefSum = Math.max(left.maxPrefSum, left.totSum + right.maxPrefSum);
            int maxSufSum = Math.max(right.maxSufSum, right.totSum + left.maxSufSum);
            int maxSubarrSum = Math.max(Math.max(left.maxSubarrSum, right.maxSubarrSum), left.maxSufSum + right.maxPrefSum);
            return new Node(totSum, maxPrefSum, maxSufSum, maxSubarrSum);
        }

        public Node query(int l, int r) {
            Node leftRes = new Node(0, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
            Node rightRes = new Node(0, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
            for (l += n, r += n + 1; l < r; l >>= 1, r >>= 1) {
                if ((l & 1) == 1) leftRes = merge(leftRes, tree[l++]);
                if ((r & 1) == 1) rightRes = merge(tree[--r], rightRes);
            }
            return merge(leftRes, rightRes);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine().trim());
        int[] arr = new int[n];
        String[] arrStr = reader.readLine().trim().split("\\s+");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(arrStr[i]);
        }

        SegmentTree segmentTree = new SegmentTree(arr);
        int m = Integer.parseInt(reader.readLine().trim());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            String[] queryStr = reader.readLine().trim().split("\\s+");
            int x = Integer.parseInt(queryStr[0]) - 1;
            int y = Integer.parseInt(queryStr[1]) - 1;
            SegmentTree.Node result = segmentTree.query(x, y);
            sb.append(result.maxSubarrSum).append("\n");
        }
        System.out.print(sb);
        reader.close();
    }
}











import java.io.*;
import java.util.*;

public class GSS1 {
    static class SegmentTree {
        public class Node {
            int totSum, maxPrefSum, maxSufSum, maxSubarrSum;

            Node(int totSum, int maxPrefSum, int maxSufSum, int maxSubarrSum) {
                this.totSum = totSum;
                this.maxPrefSum = maxPrefSum;
                this.maxSufSum = maxSufSum;
                this.maxSubarrSum = maxSubarrSum;
            }
        }

        public Node[] tree;
        public int[] arr;
        public int n;

        public SegmentTree(int[] arr) {
            this.arr = arr;
            this.n = arr.length;
            this.tree = new Node[4 * n];
            build(0, 0, n - 1);
        }

        public void build(int node, int start, int end) {
            if (start == end) {
                int value = arr[start];
                tree[node] = new Node(value, value, value, value);
            } else {
                int mid = (start + end) / 2;
                int leftC = 2 * node + 1;
                int rightC = 2 * node + 2;
                build(leftC, start, mid);
                build(rightC, mid + 1, end);
                tree[node] = merge(tree[leftC], tree[rightC]);
            }
        }

        public Node merge(Node left, Node right) {
            int totSum = left.totSum + right.totSum;
            int maxPrefSum = Math.max(left.maxPrefSum, left.totSum + right.maxPrefSum);
            int maxSufSum = Math.max(right.maxSufSum, right.totSum + left.maxSufSum);
            int maxSubarrSum = Math.max(Math.max(left.maxSubarrSum, right.maxSubarrSum), left.maxSufSum + right.maxPrefSum);
            return new Node(totSum, maxPrefSum, maxSufSum, maxSubarrSum);
        }

        public Node query(int l, int r) {
            return query(0, 0, n - 1, l, r);
        }

        public Node query(int node, int start, int end, int l, int r) {
            if (r < start || end < l) {
                return new Node(0, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
            }

            if (l <= start && end <= r) {
                return tree[node];
            }

            int mid = (start + end) / 2;
            Node leftRes = query(2 * node + 1, start, mid, l, r);
            Node rightRes = query(2 * node + 2, mid + 1, end, l, r);
            if (leftRes.maxSubarrSum == Integer.MIN_VALUE) {
                return rightRes;
            }

            if (rightRes.maxSubarrSum == Integer.MIN_VALUE) {
                return leftRes;
            }

            return merge(leftRes, rightRes);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine().trim());
        int[] arr = new int[n];
        String[] arrStr = reader.readLine().trim().split("\\s+");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(arrStr[i]);
        }

        SegmentTree segmentTree = new SegmentTree(arr);
        int m = Integer.parseInt(reader.readLine().trim());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            String[] queryStr = reader.readLine().trim().split("\\s+");
            int x = Integer.parseInt(queryStr[0]) - 1;
            int y = Integer.parseInt(queryStr[1]) - 1;
            SegmentTree.Node result = segmentTree.query(x, y);
            sb.append(result.maxSubarrSum).append("\n");
        }
        System.out.print(sb);
        reader.close();
    }
}
