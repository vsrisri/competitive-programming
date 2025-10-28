import java.util.*;
import java.io.*;

public class GSS3 {
    public Node[] tree;
    public int n;

    public GSS3(int[] arr) {
        n = arr.length;
        tree = new Node[n * 4];
        build(arr, 1, 0, n - 1);
    }

    public void build(int[] arr, int node, int start, int end) {
        int mid = (start + end) / 2;
        if (start == end) {
            tree[node] = new Node(arr[start]);
        } else {
            build(arr, node * 2, start, mid);
            build(arr, node * 2 + 1, mid + 1, end);
            tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
        }
    }

    public Node merge(Node l, Node r) {
        Node node = new Node(0);
        node.sum = l.sum + r.sum;
        node.maxSum = Math.max(Math.max(l.maxSum, r.maxSum), l.sSum + r.pSum);
        node.sSum = Math.max(r.sSum, r.sum + l.sSum);
        node.pSum = Math.max(l.pSum, l.sum + r.pSum);
        return node;
    }


    public void upd(int idx, int val) {
        upd(1, 0, n - 1, idx - 1, val);
    }

    public void upd(int node, int start, int end, int idx, int val) {
        int mid = (start + end) / 2;
        if (start == end) {
            tree[node] = new Node(val);
        } else {
            if (idx <= mid) {
                upd(node * 2, start, mid, idx, val);
            } else {
                upd(node * 2 + 1, mid + 1, end, idx, val);
            }
            tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
        }
    }

    public Node query(int x, int y) {
        return query(1, 0, n - 1, x - 1, y - 1);
    }

    public Node query(int node, int start, int end, int x, int y) {
        int mid = (start + end) / 2;
        if (x > end || y < start) {
            return new Node(Integer.MIN_VALUE);
        } else if (start >= x && end <= y) {
            return tree[node];
        }

        Node l = query(node * 2, start, mid, x, y);
        Node r = query(node * 2 + 1, mid + 1, end, x, y);
        if (l.sum == Integer.MIN_VALUE) {
            return r;
        }
        if (r.sum == Integer.MIN_VALUE) {
            return l;
        }
        return merge(l, r);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        GSS3 sTree = new GSS3(arr);
        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if (a == 1) {
                System.out.println(sTree.query(x, y).maxSum);
            } else {
                sTree.upd(x, y);
            }
        }

        br.close();
    }

    public static class Node {
        public int sum, maxSum;
        public int pSum, sSum;

        public Node(int num) {
            this.sum = num;
            this.maxSum = num;
            this.pSum = num;
            this.sSum = num;
        }
    }
}
