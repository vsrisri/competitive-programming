import java.io.*;
import java.util.*;

public class GCDMST {
    static class Pair {
        int first, second;
        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
    
    public static void helper(BufferedReader reader) throws IOException {
        String[] inputs = reader.readLine().split(" ");
        int n = Integer.parseInt(inputs[0]);
        int x = Integer.parseInt(inputs[1]);

        int[] a = new int[n];
        inputs = reader.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(inputs[i]);
        }

        boolean[] isConnected = new boolean[n];
        List<Pair> vals = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            vals.add(new Pair(a[i], i));
        }

        Collections.sort(vals, (p1, p2) -> Integer.compare(p1.first, p2.first));

        long ans = 0;
        for (Pair p : vals) {
            int cur_val = p.first;
            int i = p.second;

            if (cur_val >= x) {
                break;
            }

            while (i > 0) {
                if (isConnected[i - 1]) {
                    break;
                }
                if (a[i - 1] % cur_val == 0) {
                    isConnected[i - 1] = true;
                    ans += cur_val;
                    i--;
                } else {
                    break;
                }
            }

            i = p.second;  
            while (i < n - 1) {
                if (isConnected[i]) {
                    break;
                }
                if (a[i + 1] % cur_val == 0) {
                    isConnected[i] = true;
                    ans += cur_val;
                    i++;
                } else {
                    break;
                }
            }
        }

        for (int i = 0; i < n - 1; i++) {
            if (!isConnected[i]) {
                ans += x;
            }
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(reader.readLine());

        for (int idx = 0; idx < t; idx++) {
            helper(reader);
        }
    }
}

