import java.util.*;
import java.io.*;

public class PHONELST {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(reader.readLine());

        for (int tc = 0; tc < t; tc++) {
            int n = Integer.parseInt(reader.readLine());
            ArrayList<String> inputs = new ArrayList<String>();

            for (int idx = 0; idx < n; idx++) {
                String str = reader.readLine();
                inputs.add(str);
            }
            Node root = new Node('0', true);
            boolean areTwoStrSame = insert(inputs, root);
            if (areTwoStrSame) {
                System.out.println("NO");
            } else {
                System.out.println(search(inputs, root));
            }
        }
    }

    public static boolean insert(ArrayList<String> inputs, Node root) {
        root.isTerminal = false;
        boolean areTwoStrSame = false;

        for (String str : inputs) {
            Node curr = root;

            for (int idx = 0; idx < str.length(); idx++) {
                char c = str.charAt(idx);

                if (curr.children.get(c) == null) {
                     if (idx == str.length() - 1) {
                         curr.children.put(c, new Node(c, true));
                     } else {
                         curr.children.put(c, new Node(c, false));
                     }
                     curr = curr.children.get(c);
                } else {
                    if (idx < str.length() - 1) {
                        curr = curr.children.get(c);
                    } else {
                        if (curr.children.get(c).isTerminal) {
                            areTwoStrSame = true;
                        } else {
                            curr.children.get(c).isTerminal = true;
                        }
                    }
                }
            }
        }

        return areTwoStrSame;
    }

    public static String search(ArrayList<String> inputs, Node root) {
        String ans = "YES";
        for (String str : inputs) {
            Node curr = root;
            for (int idx = 0; idx < str.length(); idx++) {
                curr = curr.children.get(str.charAt(idx));
                if (curr.isTerminal && idx < str.length() - 1) {
                    ans = "NO";
                    break;
                }
            }
        }
        return ans;
    }
                
    public static class Node {
        public char val;
        public HashMap<Character, Node> children;
        public boolean isTerminal;

        public Node(char val, boolean isTerminal) {
            this.val = val;
            this.isTerminal = isTerminal;
            children = new  HashMap<Character, Node>();
        }
    }
}

