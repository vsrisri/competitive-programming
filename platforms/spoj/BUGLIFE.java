import java.util.*;
import java.io.*;

public class BUGLIFE {
    public static ArrayList<Integer>[] interArr;
    public static int[] genArr;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(reader.readLine());

        for (int tc = 1; tc <= t; tc++) {
            StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            interArr = new ArrayList[n];
            genArr = new int[n];
            boolean b = true;
            for (int idx = 0; idx < n; idx++) {
                interArr[idx] = new ArrayList<Integer>();
            }

            for (int idx = 0; idx < m; idx++) {
                st = new StringTokenizer(reader.readLine(), " ");
                int b1 = Integer.parseInt(st.nextToken());
                int b2 = Integer.parseInt(st.nextToken());
                interArr[b1 - 1].add(b2 - 1);
                interArr[b2 - 1].add(b1 - 1);
            }

            for (int idx = 0; idx < n; idx++) {
                if ((genArr[idx] == 0) && (!helper(idx, 1))) {
                    b = false; 
                    break;
                }   
            }

            System.out.println("Scenario #" + tc + ":");
            if (!b)  {
                System.out.println("Suspicious bugs found!");
            } else {
                System.out.println("No suspicious bugs found!");
            }
        }
        reader.close();
    }

    public static boolean helper(int curr, int g) {
        if (genArr[curr] != 0) {
            return (genArr[curr] == g);
        }

        int newG = 0;
        if (g == 1) {
            newG = 2;
        } else {
            newG = 1;
        }
        genArr[curr] = g;
        for (int neighb : interArr[curr]) {
            if (!helper(neighb, newG)) {
                return false;
            }
        }
        return true;
    }
}
