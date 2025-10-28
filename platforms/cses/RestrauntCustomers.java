import java.util.*;
import java.io.*;

public class RestrauntCustomers {
    public static void main(String[] args) throws Exception {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(stdin.readLine());
        TreeMap<Integer, Integer> treeMap = new TreeMap<Integer, Integer>();
        for (int idx = 0; idx < n; idx++) {
            StringTokenizer st = new StringTokenizer(stdin.readLine());
            treeMap.put(Integer.parseInt(st.nextToken()), 1);
            treeMap.put(Integer.parseInt(st.nextToken()), -1);
        }
        int max = 0;
        int curr = 0;
        for (int t: treeMap.values()) {
            curr+= t;
            max = Math.max(max, curr);
        }
        System.out.println(max);
    }
}
