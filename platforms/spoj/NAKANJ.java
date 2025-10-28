import java.util.*;
import java.io.*;

public class NAKANJ {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        stdin.nextLine();

        for (int idx = 0; idx < n; idx++) {
            String str = stdin.nextLine();
            int c1 = charToInt(str.charAt(0));
            int c2 = charToInt(str.charAt(3));
            int i1 = Integer.parseInt(Character.toString(str.charAt(1))) - 1;
            int i2 = Integer.parseInt(Character.toString(str.charAt(4))) - 1;
            Integer[] source = new Integer[2];
            source[0] = c1;
            source[1] = i1;
            Integer[] dest = new Integer[2];
            dest[0] = c2;
            dest[1] = i2;
            System.out.println(bfs(source, dest));
        }
    }

    public static ArrayList<Integer[]> findNeighbors(Integer[] node) {
        ArrayList<Integer[]> nodes = new ArrayList<Integer[]>();
        ArrayList<Integer[]> outArr = new ArrayList<Integer[]>();
        int a = node[0];
        int d = node[1];
        Integer[] curr = new Integer[2];
        Integer[] curr2 = new Integer[2];
        Integer[] curr3 = new Integer[2];
        Integer[] curr4 = new Integer[2];
        Integer[] curr5 = new Integer[2];
        Integer[] curr6 = new Integer[2];
        Integer[] curr7 = new Integer[2];
        Integer[] curr8 = new Integer[2];

        curr[0] = a + 1;
        curr[1] = d + 2;
        nodes.add(curr);
        outArr.add(curr);
        
        curr2[0] = a - 1;
        curr2[1] = d + 2;
        nodes.add(curr2);
        outArr.add(curr2);

        curr3[0] = a + 2;
        curr3[1] = d + 1;
        nodes.add(curr3);
        outArr.add(curr3);

        curr4[0] = a - 2;
        curr4[1] = d + 1;
        nodes.add(curr4);
        outArr.add(curr4);

        curr5[0] = a + 1;
        curr5[1] = d - 2;
        nodes.add(curr5);
        outArr.add(curr5);

        curr6[0] = a - 1;
        curr6[1] = d - 2;
        nodes.add(curr6);
        outArr.add(curr6);

        curr7[0] = a + 2;
        curr7[1] = d - 1;
        nodes.add(curr7);
        outArr.add(curr7);

        curr8[0] = a - 2;
        curr8[1] = d - 1;
        nodes.add(curr8);
        outArr.add(curr8);


        for (Integer[] n : nodes) {
            if (n[0] < 0 || n[0] > 7 || n[1] < 0 || n[1] > 7) {
                outArr.remove(n);
            }
        }
        return outArr;
    }

    public static int charToInt(char c) {
        if (c == 'a') {
            return 0;
        } if (c == 'b') {
            return 1;
        } if (c == 'c') {
            return 2;
        } if (c == 'd') {
            return 3;
        } if (c == 'e') {
            return 4;
        } if (c == 'f') {
            return 5;
        } if (c == 'g') {
            return 6;
        } if (c == 'h') {
            return 7;
        }
        return -1;
    }

    public static int bfs(Integer[] source, Integer[] dest) {
        if ((source[0] == dest[0]) && (source[1] == dest[1])) {
            return 0;
        }

        LinkedList<Integer[]> queue = new LinkedList<Integer[]>();
        queue.add(source);
        Integer[][] dist = new Integer[8][8];
        for (int idx = 0; idx < 8; idx++) {
            Arrays.fill(dist[idx], Integer.MAX_VALUE);
        }
        dist[source[0]][source[1]] = 0;

        while (!queue.isEmpty()) {
            Integer[] currNode = queue.poll();
            if (dist[currNode[0]][currNode[1]] == Integer.MAX_VALUE) {
                continue;
            }


            for (Integer[] n : findNeighbors(currNode)) {
                if (dist[n[0]][n[1]] == Integer.MAX_VALUE) {
                    dist[n[0]][n[1]] = dist[currNode[0]][currNode[1]] + 1;
                    queue.add(n);
                }
            }
        }

        return dist[dest[0]][dest[1]];
    }
}
