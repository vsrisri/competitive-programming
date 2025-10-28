import java.io.*;
import java.util.*;

public class CommonDivisors {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		int[] divCnt = new int[10000003];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int idx = 0; idx < n; idx++) {
			arr[idx] = Integer.parseInt(st.nextToken());
		}


		for (int num : arr) {
			divCnt[num]++;
		}

		int cnt = 0;
		for (int divisor = 1000000; divisor >= 1; divisor--) {
			for (int mult = divisor; mult <= 1000000; mult += divisor) {
				cnt += divCnt[mult];
			}
			if (cnt >= 2) {
				System.out.println(divisor);
				break;
			}
			cnt = 0;
		}
        br.close();
	}
}

