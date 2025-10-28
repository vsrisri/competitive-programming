import java.util.*;
import java.io.*;

public class SBANK {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        int tc = stdin.nextInt();
        for (int t = 0; t < tc; t++) {
            int n = stdin.nextInt();
            stdin.nextLine();
            String[] arr = new String[n];
            for (int idx = 0; idx < n; idx++) {
                arr[idx] = stdin.nextLine();
            }
            Arrays.sort(arr);
            for (int idx = 0; idx < n; idx++) {
                int ans = 1;
                while (idx < n && idx + 1 < n) {
                    if (arr[idx].equals(arr[idx + 1])) {
                        idx++;
                        ans++;
                    } else { 
                        break;
                    }

                }
                System.out.println(arr[idx] + " " + ans);
            }
            System.out.println();
        }
        stdin.close();
    }
}

