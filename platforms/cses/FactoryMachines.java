import java.io.*;
import java.util.*;
public class FactoryMachines {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int t = stdin.nextInt();
        int[] arr = new int[n];
        stdin.nextLine();

        for (int idx = 0; idx < n; idx++) {
            arr[idx] = stdin.nextInt();
        }

        int low = 0;
        int high = 1e8; 
        int ans = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            int sum = 0;
            for (int idx = 0; idx < n; idx++) {
                sum+= (mid / arr[idx]);
                if (sum>= t){ 
                    break;
                }
            }
            if (sum>= t) {
                ans = mid;
                high = mid - 1;
            } else {
                 low = mid + 1;
            }
        }

        System.out.println(ans);
        stdin.close();
    }
}
