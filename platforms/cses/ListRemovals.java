import java.io.*;
import java.util.*;

public class ListRemovals {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] x = new int[n];
        int[] p = new int[n];
        STree st = new STree(0, n - 1, p);
        for (int idx = 0; idx < n; idx++){
            x[idx] = sc.nextInt();
            p[idx] = 1;
        }

        for (int idx = 0; idx < n; idx++){
            int k = sc.nextInt() - 1;
            int i = st.find(k);
            System.out.print(x[i] + " ");
            st.upd(i, 0);
        }
        sc.close();
    }

    public static class Node {
        int o;
        public Node (int o){
            this.o = o;
        }
    }

    public static class STree {
        Node node;
        int leftE;
        int rightE;
        STree leftC;
        STree rightC;

        public STree(int leftE, int rightE, int[] x) {
            this.leftE = leftE;
            this.rightE = rightE;
            if (leftE == rightE){
                node = new Node(x[leftE]);
            } else {
                int mid = (leftE + rightE) / 2;
                leftC = new STree(leftE, mid, x);
                rightC = new STree(mid + 1, rightE, x);
                helper();
            }
        }

        public Node rangeQuery(int l, int r) {
            if (l > rightE || r < leftE) {
                return new Node(0);
            } else if (leftE >= l && rightE <= r) {
                return node;
            }

            Node lN = leftC.rangeQuery(l, r);
            Node rN = rightC.rangeQuery(l, r);
            return merge(lN, rN);
        }

        public int find(int k) {
            if (leftE == rightE) {
                return leftE;
            }

            int leftSide = leftC.node.o;
            if (leftSide > k) {
                return leftC.find(k);
            } else {
                return rightC.find(k - leftSide);
            }
        }

        public void helper() {
            if (leftE == rightE) {
                return;
            }
            node = new Node(leftC.node.o + rightC.node.o);
        }

        public Node merge(Node l, Node r) {
            return new Node(l.o + r.o);
        }

        public void upd(int idx, int val) {
            if (leftE == rightE) {
                node.o = val;
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
}*/
