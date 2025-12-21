import java.io.*;
import java.util.*;

public class Haircut {
    public static void main(String[] args) throws Exception  {
        Scanner stdin = new Scanner(new File("haircut.in"));
        PrintWriter pw = new PrintWriter(new File("haircut.out"));
        int n = stdin.nextInt();
        int[] a = new int[n];
        int[] a2 = new int[(int)(1e5) + 5];
        STree st = new STree(0, (int)(1e5) + 4, a2);
        long[] inv = new long[(int)(1e5) + 5];
        long ans = 0L;

        for (int idx = 0; idx < n; idx++) {
            a[idx] = stdin.nextInt();
            inv[a[idx]] += st.rq(a[idx] + 1, (int)(1e5) + 4).o;
            st.upd(a[idx], 1);
        }

        for (int idx = 0; idx < n; idx++){
            pw.println(ans);
            ans += inv[idx];
        }

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

        public STree(int leftE, int rightE, int[] a){
            this.leftE = leftE;
            this.rightE = rightE;
            if (leftE == rightE){
                node = new Node(a[leftE]);
            } else {
                int mid = (leftE + rightE) / 2;
                leftC = new STree(leftE, mid, a);
                rightC = new STree(mid + 1, rightE, a);
                helper();
            }
        }

        public Node rq(int l, int r) {
            if (l > rightE|| r < leftE) {
                return new Node(0);
            } else if (leftE >= l && rightE <= r) {
                return node;
            }

            Node lNode = leftC.rq(l, r);
            Node rNode = rightC.rq(l, r);
            return merge(lNode,rNode);
        }

        public Node merge(Node l, Node r) {
            return new Node(l.o + r.o);
        }


        public void helper() {
            if (leftE == rightE){
                return;
            }

            node = new Node((leftC.node.o + rightC.node.o));
        }

        public void upd(int idx,int val) {
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

    }
}
