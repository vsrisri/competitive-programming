// Incomplete
import java.util.*;
import java.io.*;

public class SumThreeVal {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int x = stdin.nextInt();
        int[] arr = new int[n];
        stdin.nextLine();
        for (int idx = 0; idx < n; idx++) {
            arr[idx] = stdin.nextInt();
        }
        Arrays.sort(arr);
        int l = 0;
        int r = 0;
        int target = 0;
        int ans = 0;
        boolean b = false;

        for (int i = 0; i < n; i++) {
			l = 0; 
            r = n - 1;
			while (l != r) {
                target = x - arr[i];
                if (l != i &&  r != i && arr[l] + arr[r] == target) {
                    if (b == false) {
					    System.out.println((i + 1) + " " + (l + 1) + " " + (r + 1));
                        b = true;
                    }
                    //System.out.println((i + 1) + " " + (l + 1) + " " + (r + 1));
                    //System.out.println("x: " + x + " i: " + i + " arr[i]: " + arr[i] + " arr[l]: " + arr[l] + " arr[r]: " + arr[r] + " target: " + target);
                    ans++;
                    break;
                }
                if (arr[l] + arr[r] < target) {
                    l++;
                } else {
                    r--;
                }
            } 
        }
        if (ans == 0) {
           System.out.println("IMPOSSIBLE");
        }
        stdin.close();
    }
    
}
