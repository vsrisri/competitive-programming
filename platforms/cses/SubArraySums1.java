//Incomplete
import java.io.*;
import java.util.*;

public class SubArraySums1 {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int x = stdin.nextInt();
        int[] arr = new int[n];
        stdin.nextLine();
        for (int idx = 0; idx < n; idx++) {
            arr[idx] = stdin.nextInt();
        }
        long sum = 0, ans = 0;
        HashMap<long, int> sums = new HashMap<long, int>();
        for (int i : arr) {
            ans += sums[sum - target];
            sums[sum]++;
        }
        System.out.println(ans);
    }
}
