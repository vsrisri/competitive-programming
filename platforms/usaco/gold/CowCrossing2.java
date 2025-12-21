import java.util.*;
import java.io.*;

public class CowCrossing2 {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(new File("nocross.in"));
        PrintWriter pw = new PrintWriter(new File("nocross.out"));
        int n = stdin.nextInt();
        int[] inArr1 = new int[n];
        int[] inArr2 = new int[n];
        for (int idx = 0; idx < n; idx++) {
            inArr1[idx] = stdin.nextInt();
        }
        for (int idx = 0; idx < n; idx++) {
            inArr2[idx] = stdin.nextInt();
        }

        int[] revArr = new int[n];
        for (int idx = 0; idx < n; idx++) {
            revArr[inArr1[idx] - 1] = idx + 1;
        }

        int[] min = new int[n + 1];
        Arrays.fill(min, n + 1);
        min[0] = -1;
        int ans = 0;
        for (int idx = 0; idx < n; idx++) {
            int low = Math.max(inArr2[idx] - 5, 0);
            int high = Math.min(inArr2[idx] + 3, n - 1);
            int[] arr = new int[high - low + 1];
            for (int idx2 = low; idx2 <= high; idx2++) {
                int temp = helper(min, revArr[idx2]) + 1;
                arr[idx2 - low] = temp;
                ans = Math.max(ans, temp);
            }

            for (int idx2 = low; idx2 <= high; idx2++) {
                min[arr[idx2 - low]] = Math.min(min[arr[idx2 - low]], revArr[idx2]);
            }
        }
        pw.println(ans);
        stdin.close();
        pw.close();
    }

    public static int helper(int[] min, int idx) {
        int low = 0; 
        int high = min.length - 1;
        while (low < high) {
            int mid = (low + high + 1) / 2;
            if (min[mid] < idx) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }
}
