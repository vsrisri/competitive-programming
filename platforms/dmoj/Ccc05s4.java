import java.util.*;
import java.io.*;

public class Ccc05s4 {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        int tc = stdin.nextInt();
        for (int testCase = 0; testCase < n; testCase++) {
            int n = stdin.nextInt();
            stdin.nextLine();
            int old = n * 10;
            String[] arr = new String[n];
            for (int idx = 0; idx < n; idx++) {
                arr[idx] = stdin.nextLine();
            }
            HashMap<String, Integer> all = new HashMap<String, Integer>();
            map.put(arr[n - 1], 0);
            int count = 0;
            int total = 0;
            int b = 0;
            for (int idx = 0; idx < n; idx++) {
                if (!all.containsKey(arr[idx]) {
                    count+= 1;
                    all.put(arr[idx], count);
                    total = Math.max(total, count);
                } else {

                }
            }
        }
        
    }
}


