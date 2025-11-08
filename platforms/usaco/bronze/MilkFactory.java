// USACO 2019 US Open Contest, Bronze Problem 2. Milk Factory
import java.util.*;
import java.io.*;

public class MilkFactory {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new File("factory.in"));
		PrintWriter pw = new PrintWriter("factory.out");
        int n = sc.nextInt();
        sc.nextLine();

		int[] arr = new int[n];
		for (int i = 0; i < n - 1; i++) {
			arr[sc.nextInt() - 1]++;
            sc.nextLine();
		}

		int ans = -1;
		for (int idx = 0; idx < n; idx++) {
			if (arr[idx] == 0) {
				if (ans == -1)
					ans = idx + 1; 
				else {
					ans = -1;
					break;
				}
			}
		}

		pw.println(ans);
		pw.close();
	}
}


