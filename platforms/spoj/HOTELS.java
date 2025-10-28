import java.util.*;
import java.io.*;

public class HOTELS {
	public static void main (String [] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
		int n = stdin.nextInt()
        int m = stdin.nextInt();
		int [] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = stdin.nextInt();
        }
		
		long curr = 0;
		int prev = 0;
		long max = 0;
		for (int i = 0; i < n; i++) {
			curr += arr [i];
			while (curr > m) {
                curr-= arr[prev++];
            }
			if (curr > max) {
                max = curr;
            }
		}
		
		System.out.println(max);
        stdin.close();
	}
}
