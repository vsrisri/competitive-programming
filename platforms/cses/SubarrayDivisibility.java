//Incomplete
import java.io.*;
import java.util.*;

public class SubarrayDivisibility {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        long[] arr = new long[n];
        int psums = 0;
        arr[psums] = 1;

        for (int i = 0; i < n; i++) {
            int curr = stdin.nextInt();
            psums+= curr;
            arr[((psums % n) + n) % n]++;
        }

        long ans = 0; 
        for (long i : arr) {
            ans += i * (i - 1) / 2;
        }
        System.out.println(ans);
        stdin.close();

    }


}
