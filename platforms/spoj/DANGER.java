import java.io.*;
import java.util.*;

public class DANGER {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        while ((input = br.readLine()) != null) {
            input = input.trim();
            if (input.equals("00e0")) {
                break;
            }
            int x = input.charAt(0) - '0';
            int y = input.charAt(1) - '0';
            int z = input.charAt(3) - '0';
            int n = (x * 10 + y) * (int) Math.pow(10, z);

            System.out.println(helper(n));
        }
        br.close();
    }

    public static int helper(int n) {
        int p = Integer.highestOneBit(n);
        return 2 * (n - p) + 1;
    }
}

