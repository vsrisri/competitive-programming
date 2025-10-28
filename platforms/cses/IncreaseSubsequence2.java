import java.util.*;
import java.io.*;

public class IncreaseSubsequence2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        TreeSet<Integer> ts = new TreeSet<>();
        String[] input = br.readLine().split(" ");
        
        for (int idx = 0; idx < n; idx++){
            arr[idx] = Integer.parseInt(input[idx]);
            ts.add(arr[idx]);
        }

        HashMap<Integer,Integer> hs = new HashMap<>();
        int t = 0;
        for (int i: ts) {
            hs.put(i, t);
            t++;
        }

        for (int idx = 0; idx < n; idx++){
            arr[idx] = hs.get(arr[idx]);
        }

        long[] arr2 = new long[ts.size()];
        long ans = 0L;
        SegmentTree st = new SegmentTree(ts.size());
        for (int idx = 0; idx < n; idx++) {
            long s = st.rangeQuery(0, arr[idx] - 1);
            arr2[arr[idx]] = modAdd(arr2[arr[idx]], modAdd(s, 1));
            st.update(arr[idx], arr2[arr[idx]]);
        }

        for (long num : arr2) {
            ans = modAdd(ans, num);
        }

        System.out.println(ans);
    }

    public static long modAdd(long a , long b) {
        long MOD = (long) (1e9 + 7);
        return (a + b) % MOD;
    }

    public static class SegmentTree {
        long[] tree;
        int n;

        public SegmentTree(int size) {
            n = size;
            tree = new long[2 * n];
        }

        public void update(int idx, long val) {
            idx += n;
            tree[idx] = val;
            for (idx /= 2; idx > 0; idx /= 2) {
                tree[idx] = modAdd(tree[2 * idx], tree[2 * idx + 1]);
            }
        }

        public long rangeQuery(int l, int r) {
            long sum = 0L;
            l += n;
            r += n + 1;
            while (l < r) {
                if ((l & 1) == 1) {
                    sum = modAdd(sum, tree[l++]);
                }
                if ((r & 1) == 1) {
                    sum = modAdd(sum, tree[--r]);
                }
                l /= 2;
                r /= 2;
            }
            return sum;
        }
    }
}



/*
import java.util.*;
import java.io.*;

public class IncreaseSubsequence2 {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int[] arr = new int[n];
        TreeSet<Integer> ts = new TreeSet<>();
        for (int idx = 0; idx < n; idx++){
            arr[idx] = stdin.nextInt();
            ts.add(arr[idx]);
        }

        HashMap<Integer,Integer> hs = new HashMap<>();
        int t = 0;
        for (int i: ts) {
            hs.put(i, t);
            t++;
        }

        for (int idx = 0; idx < n; idx++){
            arr[idx] = hs.get(arr[idx]);
        }

        long[] arr2 = new long[ts.size()];
        long ans = 0L;
        STree st = new STree(0, ts.size() - 1, arr2);
        for (int idx = 0; idx < n; idx++) {
            long s = st.rangeQuery(0, arr[idx] - 1);
            arr2[arr[idx]] = modAdd(arr2[arr[idx]], modAdd(s, 1));
            st.update(arr[idx], arr2[arr[idx]]);
        }

        for (long num : arr2) {
            ans = modAdd(ans, num);
        }

        System.out.println(ans);
        stdin.close();
    }

    public static long modAdd(long a , long b) {
        long MOD = (long) (1e9 + 7);
        return (a + b) % MOD;
    }

    public static class STree {
        int left;
        int right;
        STree lc;
        STree rc;
        long sum = 0L;

        public STree(int left, int right, long[] arr) {
            this.left = left;
            this.right = right;

            if (left == right){
                sum = arr[left];
            } else {
                int mid = (left + right) / 2;
                lc = new STree(left, mid, arr);
                rc = new STree(mid + 1, right, arr);
                helper();
            }
        }

        public void helper() {
            if (left == right) {
                return;
            }

            sum = modAdd(lc.sum, rc.sum);
        }

        public void update(int idx, long val){
            if (left == right) {
                sum = val;
                return;
            }

            if (idx <= lc.right) {
                lc.update(idx,val);
            } else {
                rc.update(idx,val);
            }
            helper();
        }

        public long rangeQuery(int l, int r) {
            if (r < left || l > right) {
                return 0L;
            }

            if (l <= left && r >= right) {
                return sum;
            }
            return modAdd(lc.rangeQuery(l,r), rc.rangeQuery(l,r));
        }
    }
}*/
