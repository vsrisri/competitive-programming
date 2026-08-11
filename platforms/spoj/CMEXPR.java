import java.io.*;
import java.util.*;

public class CMEXPR {
    public static char[] s;
    public static int p;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        int t = Integer.parseInt(br.readLine().trim());
        for (int tc = 0; tc < t; tc++) {
            String line = br.readLine();
            while (line != null && line.trim().isEmpty()) {
                line = br.readLine();
            }
            s = line.trim().toCharArray();
            p = 0;
            Node root = helper();
            StringBuilder sb = new StringBuilder();
            pNode(root, sb);
            out.append(sb).append('\n');
        }
        System.out.print(out);
        br.close();
    }

    public static class Node {
        char op;
        String name;
        Node left;
        Node right;
        Node(String name) {
            this.op = 0;
            this.name = name;
        }

        Node(char op, Node left, Node right) {
            this.op = op;
            this.left = left;
            this.right = right;
        }
    }

    public static Node helper() {
        Node left = hTerm();
        while (p < s.length && (s[p] == '+' || s[p] == '-')) {
            char op = s[p];
            p++;
            Node right = hTerm();
            left = new Node(op, left, right);
        }
        return left;
    }

    public static Node hTerm() {
        Node left = hFact();
        while (p < s.length && (s[p] == '*' || s[p] == '/')) {
            char op = s[p];
            p++;
            Node right = hFact();
            left = new Node(op, left, right);
        }
        return left;
    }

    public static Node hFact() {
        if (s[p] == '(') {
            p++;
            Node e = helper();
            p++;
            return e;
        }
        int start = p;
        while (p < s.length && Character.isLetter(s[p])) {
            p++;
        }
        return new Node(new String(s, start, p - start));
    }

    public static int prec(char op) {
        if (op == '+' || op == '-') {
            return 1;
        }
        return 2;
    }

    public static boolean paren(Node child, char parentOp, boolean isRight) {
        if (child.op == 0) {
            return false;
        }
        int pc = prec(child.op);
        int pp = prec(parentOp);
        if (pc < pp) {
            return true;
        }
        if (pc > pp) {
            return false;
        }
        if (!isRight) {
            return false;
        }
        if (parentOp == '+' || parentOp == '*') {
            return false;
        }
        return true;
    }

    public static void pNode(Node node, StringBuilder sb) {
        if (node.op == 0) {
            sb.append(node.name);
            return;
        }
        boolean lp = paren(node.left, node.op, false);
        if (lp) {
            sb.append('(');
        }
        pNode(node.left, sb);
        if (lp) {
            sb.append(')');
        }
        sb.append(node.op);
        boolean rp = paren(node.right, node.op, true);
        if (rp) {
            sb.append('(');
        }
        pNode(node.right, sb);
        if (rp) {
            sb.append(')');
        }
    }
}
