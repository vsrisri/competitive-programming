import java.util.*;
import java.io.*;

public class PhilClass {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Integer[][] pairs = new Integer[m][2];
        ArrayList<Node> arr = new ArrayList<Node>();

        arr.add(new Node(0));
        for (int idx = 1; idx <= n; idx++) {
            arr.add(new Node(idx));
        }

        for (int idx = 0; idx < m; idx++) {
            st = new StringTokenizer(reader.readLine(), " ");
            pairs[idx][0] = Integer.parseInt(st.nextToken());
            pairs[idx][1] = Integer.parseInt(st.nextToken());
            arr.get(pairs[idx][0]).connect.add(arr.get(pairs[idx][1]));
            arr.get(pairs[idx][1]).connect.add(arr.get(pairs[idx][0]));
        }

        ArrayList<Integer> currMin = new ArrayList<Integer>();
        boolean hasChanged = false;
        for (int idx = 0; idx < 3; idx++) {
            currMin.add(3000);
        }

        for (int idx = 1; idx <= n; idx++) {
            for (int idx2 = 0; idx2 < m; idx2++) {
                Node a = arr.get(pairs[idx2][0]);
                Node b = arr.get(pairs[idx2][1]);
                if (a.value != idx && b.value != idx && a.connect.contains(arr.get(idx)) && b.connect.contains(arr.get(idx))) {
                    ArrayList<Integer> curr = new ArrayList<Integer>();
                    curr.add(a.value);
                    curr.add(b.value);
                    curr.add(idx);
                    Collections.sort(curr);
                    for (int i = 0; i < 3; i++) {
                        if (curr.get(i) < currMin.get(i)) {
                            currMin = curr;
                            hasChanged = true;
                            break;
                        }

                        if (curr.get(i) > currMin.get(i)) {
                            break;
                        }

                    }
                }
            }
        }

        if (!hasChanged) {
            currMin.add(3000);
            for (int idx = 0; idx < m; idx++) {
                for (int idx2 = 0; idx2 < m; idx2++) {
                    HashSet<Integer> hs = new HashSet<Integer>();
                    hs.add(pairs[idx][0]);
                    hs.add(pairs[idx][1]);
                    hs.add(pairs[idx2][0]);
                    hs.add(pairs[idx2][1]);
                    if (hs.size() == 4) {
                        ArrayList<Integer> curr = new ArrayList<Integer>();
                        curr.add(pairs[idx][0]);
                        curr.add(pairs[idx][1]);
                        curr.add(pairs[idx2][0]);
                        curr.add(pairs[idx2][1]);
                        Collections.sort(curr);
                        for (int i = 0; i < 4; i++) {
                            if (curr.get(i) < currMin.get(i)) {
                                currMin = curr;
                                hasChanged = true;
                                break;
                            }

                            if (curr.get(i) > currMin.get(i)) {
                                break;
                            }
                        }
                    }
                }
            }
        }

        if (!hasChanged) {
            System.out.println(-1);
        } else {
            System.out.println(currMin.size());
            for (int idx = 0; idx < currMin.size(); idx++) {
                System.out.print(currMin.get(idx) + " ");
            }

        }
        reader.close();

    }

    public static class Node {
        public int value;
        public HashSet<Node> connect;

        public Node(int value) {
            this.value = value;
            this.connect = new HashSet<Node>();
        }
    }
}
