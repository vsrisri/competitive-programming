import java.util.*;
import java.io.*;

public class CANDY {
	public static void main(String[] args) throws Exception {
		Scanner stdin = new Scanner(System.in);
		int n = stdin.nextInt();

		while (n != -1){
			int total = 0;
			int[] candies = new int[n];
			for (int idx = 0; idx < n; idx++){
				int c = stdin.nextInt();
				candies[idx] = c;
				total += c;
			}

			if (total % n != 0){
                System.out.println(-1);
			} else { 
				int num = 0;
				int modVal = total/n;
				for (int idx2 = 0; idx2 < n; idx2++){
					if (candies[idx2] < modVal){
						num+= modVal - candies[idx2];
					}
				}
                System.out.println(num);
			}
			n = stdin.nextInt();
		}
        stdin.close();
	}
}
