//Incomplete
import java.util.*;
import java.io.*;

public class Abeceda {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        ArrayList<String> words = new ArrayList<String>();
        for (int idx = 0; idx < n; idx++) {
            words.add(reader.readLine());
        }

        HashMap<Character, HashSet<Character>> connections = new HashMap<Character, HashSet<Character>>();
        HashSet<Character> letters = new HashSet<Character>();
        boolean isImposs = false;

        for (int idx = 0; idx < 26; idx++) {
            connections.put((char) (idx + 97), new HashSet<Character>());
        }

        String word1 = words.get(0);
        for (int i = 0; i < word1.length(); i++) {
            letters.add(word1.charAt(i));
        }

        for (int idx = 1; idx < n; idx++) {
            String word2 = words.get(idx);
            int len = Math.min(word1.length(), word2.length());

            for (int i = 0; i < len; i++) {
                if (word1.charAt(i) != word2.charAt(i)) {
                    if (!connections.get(word2.charAt(i)).contains(word1.charAt(i))) {
                        connections.get(word1.charAt(i)).add(word2.charAt(i));
                    } else {
                        isImposs = true;
                    }
                    break;
                }
            }

            word1 = word2;
            if (isImposs) {
                break;
            }

            for (int i = 0; i < word2.length(); i++) {
                letters.add(word2.charAt(i));
            }
        }

        if (isImposs) {
            System.out.println("!");
        } else {
            HashMap<Character, Node> nodes = new HashMap<Character, Node>();
            for (Character c : letters) {
                nodes.put(c, new Node(c));
            }
            createGraph(nodes, connections, letters);
            System.out.print(topoSort(nodes));
        }
    }

    public static String topoSort(HashMap<Character, Node> nodes) {
        if (hasCycle(nodes)) {
            return "!";
        }

        ArrayList<Node> t = new ArrayList<Node>();
        Stack<Node> inDegZero = new Stack<Node>();
        HashMap<Node, Integer> inDegs = new HashMap<Node, Integer>();
        for (Character c : nodes.keySet()) {
            inDegs.put(nodes.get(c), 0);
        }

        for (Character c : nodes.keySet()) {
            Node curr = nodes.get(c);
            for (Node n : curr.after) {
                int num = inDegs.get(n);
                inDegs.replace(n, num + 1);
            }
        }

        boolean hasSeenZero1 = false;
        for (Character c : nodes.keySet()) {
            Node curr = nodes.get(c);
            if (inDegs.get(curr) == 0) {
                if (!hasSeenZero1) {
                    inDegZero.push(curr);
                    hasSeenZero1 = true;
                } else {
                    return "?";
                }
            }
        }

        while (!inDegZero.empty()) {
            Node curr = inDegZero.pop();
            t.add(curr);
            for (Node next : curr.after) {
                int num = inDegs.get(next);
                inDegs.replace(next, num - 1);
                if (num - 1 == 0) {
                    inDegZero.push(next);
                }
            }

            boolean hasSeenZero = false;
            for (Node next : curr.after) {
                if (inDegs.get(next) == 0) {
                    if (hasSeenZero) {
                        return "?";
                    } else {
                        hasSeenZero = true;
                    }
                }
            }
        }

        StringBuilder str = new StringBuilder();
        for (Node node : t) {
            str.append(node.letter);
        }

        String out = str.toString();
        return out;
    }

    public static boolean cycleHelper(Node curr, HashMap<Character, Boolean> visited, HashMap<Character, Boolean> path) {
        if (path.get(curr.letter)) {
            return true;
        }

        if (visited.get(curr.letter)) {
            return false;
        }

        visited.replace(curr.letter, true);
        path.replace(curr.letter, true);
        for (Node n : curr.after) {
            if (cycleHelper(n, visited, path)) {
                return true;
            }
        }

        path.replace(curr.letter, false);
        return false;
    }



    public static boolean hasCycle(HashMap<Character, Node> nodes) {
        HashMap<Character, Boolean> visited = new HashMap<Character, Boolean>();
        HashMap<Character, Boolean> path = new HashMap<Character, Boolean>();

        for (Character c : nodes.keySet()) {
            visited.put(c, false);
            path.put(c, false);
        }

        for (Character c : nodes.keySet()) {
            Node curr = nodes.get(c);
            if (cycleHelper(curr, visited, path)) {
                return true;
            }
        }
        return false;
    }

    public static void createGraph(HashMap<Character, Node> nodes, HashMap<Character, HashSet<Character>> connections, HashSet<Character> letters) {
        for (Character letter : letters) {
            Node curr = nodes.get(letter);
            for (Character connection : connections.get(curr.letter)) {
                curr.after.add(nodes.get(connection));
            }
        }
    }

    public static class Node {
        char letter;
        ArrayList<Node> after;

        public Node(char letter) {
            this.letter = letter;
            after = new ArrayList<Node>();
        }
    }
}
