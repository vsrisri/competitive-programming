import java.util.*;
import java.io.*;

public class AlpacaDistancing {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        List<int[]> arr = new ArrayList<>();
        Set<Integer> posSet = new TreeSet<>();
        SegmentTree tree = new SegmentTree(100001);
        int[] dp = new int[n];
        TreeMap<Integer, Integer> idxMap = new TreeMap<>();
        int ans = 0;

        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
            posSet.add(a[i]);
            b[i] = sc.nextInt();
            int[] temp = new int[4];
            temp[0] = 0;
            temp[1] = i;
            temp[2] = a[i];
            temp[3] = a[i] + b[i];
            arr.add(temp);
            int[] temp2 = new int[4];
            temp2[0] = 1;
            temp2[1] = i;
            temp2[2] = b[i];
            temp2[3] = a[i];
            arr.add(temp2);
        }

        sortArr(arr);
        int idx = 1;
        for (int pos : posSet) {
            idxMap.put(pos, idx);
            idx++;
        }

        System.out.println(helper(arr, idxMap, tree, dp));
        sc.close();
    }

    public static void sortArr(List<int[]> arr) {
        arr.sort((x, y) -> {
            if (x[3] == y[3]) {
                return Integer.compare(x[0], y[0]);
            }
            return Integer.compare(x[3], y[3]);
        });
    }

    public static int helper(List<int[]> arr, TreeMap<Integer, Integer> idxMap, SegmentTree tree, int[] dp) {
        int ans = 0;
        for (int[] arrI : arr) {
            int pos = arrI[3];
            if (arrI[0] == 0) {
                tree.upd(1, idxMap.get(arrI[2]), dp[arrI[1]]);
            } else {
                Integer key = idxMap.floorKey(pos - arrI[2]);
                int num = key != null ? tree.query(1, 1, idxMap.get(key)) : 0;
                dp[arrI[1]] = num + 1;
                ans = Math.max(ans, dp[arrI[1]]);
            }
        }
        return ans;
    }

    public static class SegmentTree {
        public static class Node {
            int l;
            int r;
            int maxVal;
            Node(int l, int r, int maxVal) {
                this.l = l;
                this.r = r;
                this.maxVal = maxVal;
            }
        }

        public Node[] tree;
        public int size;

        public SegmentTree(int size) {
            this.size = size;
            tree = new Node[4 * size];
            build(1, 1, size);
        }

        public void build(int pos, int l, int r) {
            int mid = (l + r) / 2;
            if (l == r) {
                tree[pos] = new Node(l, r, 0);
            } else {
                build(pos * 2, l, mid);
                build(pos * 2 + 1, mid + 1, r);
                tree[pos] = new Node(l, r, 0);
            }
        }

        public void upd(int pos, int idx, int val) {
            int mid = (tree[pos].l + tree[pos].r) / 2;
            if (tree[pos].l == idx && tree[pos].r == idx) {
                tree[pos].maxVal = val;
            } else {
                if (idx <= mid) {
                    upd(pos * 2, idx, val);
                } else {
                    upd(pos * 2 + 1, idx, val);
                }
                tree[pos].maxVal = Math.max(tree[pos * 2].maxVal, tree[pos * 2 + 1].maxVal);
            }
        }

        public int query(int pos, int l, int r) {
            if (l > tree[pos].r || r < tree[pos].l) {
                return 0;
            }

            if (l <= tree[pos].l && tree[pos].r <= r) {
                return tree[pos].maxVal;
            }

            return Math.max(query(pos * 2, l, r), query(pos * 2 + 1, l, r));
        }
    }
}


