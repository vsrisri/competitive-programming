import java.io.*;
import java.util.*;

public class Prod1ModN {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Integer> subsequence = new ArrayList<>();
        long product = 1;
        
        for (int i = 1; i < n; i++) {
            if (gcd(i, n) == 1) {
                subsequence.add(i);
                product = (product * i) % n;
            }
        }
        
        if (product != 1) {
            subsequence.remove(subsequence.size() - 1);
        }
        
        System.out.println(subsequence.size());
        for (int num : subsequence) {
            System.out.print(num + " ");
        }
        br.close();
    }
    
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}

