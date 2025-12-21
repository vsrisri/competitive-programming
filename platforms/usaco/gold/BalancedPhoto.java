import java.io.*;
import java.util.*;

public class BalancedPhoto {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(new File("bphoto.in"));
        PrintWriter pw = new PrintWriter(new File("bphoto.out"));
        int n = stdin.nextInt();
        int[] a = new int[n];
        int[] b = new int[100004];
        int[] c = new int[100004];
        STree lst = new STree(0, 100003, b);
        STree rst = new STree(0, 100003, c);
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a[i] = stdin.nextInt();
            arr.add(a[i]);
        }

        Collections.sort(arr);
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int j = 1;
        for (int num: arr) {
            if (!map.containsKey(num)){
                map.put(num, j);
                j++;
            }
        }

        for (int i = 0; i < n; i++){
            a[i] = map.get(a[i]);
        }

        for (int i = 0; i < n; i++){
            rst.upd(a[i],1);
        }

        long ans = 0L;
        for (int i = 0; i < n; i++){
            long l = (lst.rq(a[i] + 1, 100003).o);
            long r = (rst.rq(a[i] + 1, 100003).o);
            if (Math.max(l, r) > 2 * Math.min(l, r)){
                ans++;
            }
            lst.upd(a[i], 1);
            rst.upd(a[i], -1);
        }

        pw.println(ans);
        stdin.close();
        pw.close();
    }

    public static class Node {
        long o;
        public Node(long o) {
            this.o = o;
        }
    }

    public static class STree {
        Node node;
        int leftE;
        int rightE;
        STree leftC;
        STree rightC;

        public STree(int leftE, int rightE, int[] arr) {
            this.leftE = leftE;
            this.rightE = rightE;
            if (leftE == rightE){
                node = new Node(arr[leftE]);
            } else {
                int mid = (leftE + rightE) / 2;
                leftC = new STree(leftE, mid, arr);
                rightC = new STree(mid + 1, rightE, arr);
                helper();
            }
        }
        public Node merge(Node l, Node r){
            return new Node(l.o + r.o);
        }

        public void upd(int idx,int val){
            if (leftE == rightE){
                node.o += val;
                return;
            }

            if (idx <= leftC.rightE) {
                leftC.upd(idx, val);
            } else {
                rightC.upd(idx, val);
            }
            helper();
        }

        public Node rq(int l, int r){
            if (l > rightE || r < leftE) {
                return new Node(0);
            } else if (leftE >= l && rightE <= r) {
                return node;
            }
            Node ln = leftC.rq(l, r);
            Node rn = rightC.rq(l, r);

            return merge(ln, rn);
        }

        public void helper() {
            if (leftE == rightE) {
                return;
            }
            node = new Node((leftC.node.o + rightC.node.o));
        }
    }
}
