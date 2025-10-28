import java.io.*;
import java.util.*;

public class SalaryQueries {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        int[] a = new int[n];
        int[][] qArr = new int[q][3];
        TreeSet<Integer> ts = new TreeSet<Integer>();
        for (int idx = 0; idx < n; idx++){
            a[idx] = sc.nextInt();
            ts.add(a[idx]);
        }

        for (int idx = 0; idx < q; idx++) {
            String s = sc.next();
            if (s.equals("?")){
                qArr[idx][0] = 1;
                qArr[idx][1] = sc.nextInt();
                qArr[idx][2] = sc.nextInt();
                ts.add(qArr[idx][1]);
                ts.add(qArr[idx][2]);
            } else {
                qArr[idx][0] = 2;
                qArr[idx][1] = sc.nextInt() - 1;
                qArr[idx][2] = sc.nextInt();
                ts.add(qArr[idx][2]);
            }
        }

        int i = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int num: ts) {
            map.put(num, i);
            i++;
        }

        int[] arr = new int[ts.size()];
        for (int idx = 0; idx < n; idx++) {
            a[idx] = map.get(a[idx]);
            arr[a[idx]]++;
        }

        STree st = new STree(0, arr.length - 1, arr);
        for (int idx = 0; idx < q; idx++){
            if (qArr[idx][0] == 1) {
                int s = map.get(qArr[idx][1]);
                int e = map.get(qArr[idx][2]);
                long ans = st.rq(0, e);
                if (s > 0) {
                    ans -= st.rq(0, s - 1);
                }
                System.out.println(ans);
            } else {
                int indx = qArr[idx][1];
                int val = map.get(qArr[idx][2]);
                arr[a[idx]] -= 1;
                st.upd(a[indx], arr[a[indx]]);
                a[indx] = val;
                arr[val] += 1;
                st.upd(a[indx], arr[a[indx]]);
            }
        }

    }

    static class STree {
        int leftE, rightE;
        STree leftC, rightC;
        long sum;

        public STree(int leftE, int rightE, int[] a) {
            this.leftE = leftE;
            this.rightE = rightE;

            if (leftE == rightE) {
                sum = a[leftE];
            } else {
                int mid = (leftE + rightE) / 2;
                leftC = new STree(leftE, mid, a);
                rightC = new STree(mid + 1, rightE, a);
                helper();
            }
        }

        public void upd(int idx, int val) {
            if (leftE == rightE) {
                sum = val;
                return;
            } else if (idx <= leftC.rightE) {
                leftC.upd(idx, val);
            } else {
                rightC.upd(idx, val);
            }
            helper();
        }

        public long rq(int l,int r) {
            if (r < leftE || l > rightE) {
                return 0L;
            } else if (l <= leftE && r >= rightE) {
                return sum;
            } else {
                return leftC.rq(l, r) + rightC.rq(l, r);
            }
        }

        public void helper() {
            if (leftE == rightE) {
                return; 
            }
            sum = leftC.sum + rightC.sum;
        }
    }
}
