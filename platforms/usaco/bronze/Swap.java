import java.util.*;
import java.io.*;

public class Swap {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("swap.in"));
        PrintWriter pw = new PrintWriter(new File("swap.out"));
        int n = sc.nextInt();
        int k = sc.nextInt();
        sc.nextLine();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        int s1 = sc.nextInt() - 1;
		int e1 = sc.nextInt() - 1;
		Swap.reverse(arr, s1, e1);
		int s2 = sc.nextInt() - 1;
		int e2 = sc.nextInt() - 1;
		Swap.reverse(arr, s2, e2);		
        int[] ans = go(arr, k);
        for (int i = 0; i < n; i++) {
            pw.println(ans[i] + 1);
        }
        pw.close();
    }

    public static int[] go(int[] arr, int k) {
        if (k == 1) {
            return arr;
        }

        if (k % 2 == 0) { 
            int[] tmp = Swap.go(arr, k / 2);
            return Swap.switchArr(tmp, tmp);
        }
        int[] tmp = go(arr, k - 1);
        return Swap.switchArr(tmp, arr);
    }

    public static int[] switchArr(int[] a, int[] b) {
        int n = a.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = b[a[i]];
        }
        return ans;
    }

    public static void reverse(int[] arr, int s, int e) {
        while (s < e) {
			int tmp = arr[s];
			arr[s] = arr[e];
			arr[e] = tmp;
			s++;
			e--;
		}
    }
}
