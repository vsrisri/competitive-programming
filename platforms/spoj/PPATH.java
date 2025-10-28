/*
Old approach:

    public static int bfs(int source, int dest) {
        if (source == dest) {
            return 0;
        }

        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(source);
        int dist[] = new int[10001];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;
        int prev = source;

        //int price = 0;
        while (!queue.isEmpty()) {
            System.out.println("prev: " + prev);
            int curr = queue.poll();
            String str = String.valueOf(curr);

            for (int i = 0; i < 4; i++) {
                String temp = "";
                for (char c = '0'; c <= '9'; c++) {
                    if (str.charAt(i) == c || (i == 0 && c == '0')) {
                        continue; 
                    }

                    temp = str.substring(0, i) +  Character.toString(c) + str.substring(i + 1, str.length());
                    if (dist[Integer.parseInt(temp)] < Integer.MAX_VALUE) {
                        continue;
                    }
                    int newNum = Integer.parseInt(temp.toString());
                    if (newNum == dest) {
                        System.out.println("dest: " + dest);
                        return dist[prev] + 1;
                    }
                    // System.out.println(newNum);

                    if (isPrime(newNum)) {
                        queue.offer(newNum);
                    }
                }
            }
            dist[Integer.parseInt(str)] = dist[prev] + 1; 

            prev = Integer.parseInt(str);
        }

        return -1;
    }
*/
import java.io.*;
import java.util.*;

public class PPATH {
    public static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) { 
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static int bfs2(int source, int dest) {
        if (source == dest) {
            return 0;
        }

        LinkedList<Node> queue = new LinkedList<Node>();
        Node n = new Node(source, 0);
        queue.add(n);
        HashSet<Integer> allNumsSet = new HashSet<Integer>();

        while (!queue.isEmpty()) {
            Node currNode = queue.poll();
            if (allNumsSet.contains(currNode.value)) {
                continue;
            }
            queue.add(currNode);
            String str = String.valueOf(currNode.value);

            for (int i = 0; i < 4; i++) {
                String temp = "";
                for (char c = '0'; c <= '9'; c++) {
                    if (str.charAt(i) == c || (i == 0 && c == '0')) {
                        continue; 
                    }

                    temp = str.substring(0, i) +  Character.toString(c) + str.substring(i + 1, str.length());
                    int newNum = Integer.parseInt(temp.toString());
                    Node node = new Node(newNum, currNode.count + 1);
                    if (node.value == dest) {
                        return node.count;
                    }

                    if (isPrime(node.value) && !allNumsSet.contains(node.value)) {
                        queue.offer(node);
                    }
                }
            }
            allNumsSet.add(currNode.value);
        }

        return -1;
    }

    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        for (int idx = 0; idx < n; idx++) {
            int num1 = stdin.nextInt();
            int num2 = stdin.nextInt();
            int ans = bfs2(num1, num2);
            if (ans == -1) {
                System.out.println("IMPOSSIBLE");
            } else {
                System.out.println(ans);
            }
        }
    }

    static class Node {
        public int value;
        public int count;
        Node (int value, int count) {
            this.value = value;
            this.count = count;
        }
    }


}
