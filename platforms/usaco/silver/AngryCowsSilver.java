import java.util.*;
import java.io.*;
public class AngryCowsSilver {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("angry.in"));
        PrintWriter pw = new PrintWriter(new File("angry.out"));
        int n = sc.nextInt();
        int k = sc.nextInt();
        sc.nextLine();
        int[] arr = new int[n];

	    // For loop: read in integers
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            sc.nextLine();
        }
        Arrays.sort(arr);
        int low = 1, high = arr[n - 1] - arr[0];
        // Start of while loop
		while (low < high - 1) {
			int mid = (low + high) / 2;
			if (!AngryCowsSilver.isValid(arr, k, mid)) {
				low = mid + 1;
			} else {
				high = mid;
            }
		}

		if (!AngryCowsSilver.isValid(arr, k, low)) {
            low++;
        }
        pw.println(low);
        sc.close();
        pw.close();
    }

    public static boolean isValid(int[] arr, int numCows, int range) {
        int curBale = 0;
        for (int i = 0; i < numCows; i++) {
            int start = arr[curBale];
            while (curBale < arr.length && arr[curBale] - start <= 2 * range) {
                curBale++;
            }
            if (curBale == arr.length) {
                return true;
            }
        }
        return false;
    }
}
