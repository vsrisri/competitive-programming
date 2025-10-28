import java.util.*;
import java.io.*;

public class COINS {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        HashMap<Integer, Long> map = new HashMap<Integer, Long>();
        while (stdin.hasNext()) {
            int n = stdin.nextInt();
            System.out.println(helper(n, map));
        }
        stdin.close();
    }

    public static long helper(int n, HashMap<Integer, Long> map) {
        if (n < 12) {
            return n;
        } 
        if (map.containsKey(n)) {
            return map.get(n);
        }
        long ans = helper(n / 2, map) + helper(n / 3, map) + helper(n / 4, map);
        map.put(n, ans);
        return ans;
    }

}

