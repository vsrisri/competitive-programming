import java.io.*;
import java.util.*;

public class PowProds {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str1 = br.readLine().split(" ");
        int n = Integer.parseInt(str1[0]);
        int k = Integer.parseInt(str1[1]);
        String[] str2 = br.readLine().split(" ");
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(str2[i]);
        }
        
        Map<Integer, Integer> countMap = new HashMap<>();
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int ai = a[i];
            for (int j = 1; j <= 100; j++) {
                int pow = (int) Math.pow(j, k);
                if (pow > 100000) {
                    break;
                }
                if (ai % pow == 0) {
                    ans += countMap.getOrDefault(ai / pow, 0);
                }
            }
            countMap.put(ai, countMap.getOrDefault(ai, 0) + 1);
        }
        
        System.out.println(ans);
        br.close();
    }
}

