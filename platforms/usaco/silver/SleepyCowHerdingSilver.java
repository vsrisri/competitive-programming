import java.util.*;
import java.io.*;

public class SleepyCowHerdingSilver {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("herding.in"));
        PrintWriter pw = new PrintWriter(new File("herding.out"));
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        sc.nextLine();
        pw.println(min(arr));
        pw.println(max(arr));
        sc.close();
        pw.close();
    }

    public static int min(int[] arr) {
		int n = arr.length;
		if (arr[n - 1] - arr[0] == n - 1) {
            return 0;
        }
		if (arr[n - 1] - arr[0] == n) {
            return 1;
        }
		if (arr[n - 2] - arr[0] == n - 1 || arr[n - 1] - arr[1] == n - 1) {
            return 1;
        }
		if (arr[n - 2] - arr[0] == n - 2 || arr[n - 1] - arr[1] == n - 2) {
            return 2;
        }
		int max = getMax(arr);

		int[] end = new int[n];
		for (int i = 0; i < n; i++)
			end[i] = arr[n - 1 - i];
		max = Math.max(max, getMax(end));
		return n - max;
	}


    public static int getMax(int[] arr) {
		int low = 0, high = 0, max = 1, n = arr.length;
		while (high < n) {
			if (Math.abs(arr[high] - arr[low]) <= n - 1) {
				max = Math.max(max, high - low + 1);
				high++;
			} else {
				low++;
			}
		}
		return max;
	}

	public static int max(int[] arr) {
		int n = arr.length;
		return Math.max(arr[n - 2] - arr[0] - (n - 2), arr[n - 1] - arr[1] - (n - 2));
	}
}
