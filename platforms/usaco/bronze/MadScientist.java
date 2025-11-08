import java.util.*;
import java.io.*;

public class MadScientist {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("breedflip.in"));
        PrintWriter pw = new PrintWriter(new File("breedflip.out"));
        int n = sc.nextInt();
        sc.nextLine();
        char[] str1 = sc.next().toCharArray();
		char[] str2 = sc.next().toCharArray();
        int i = 0, ans = 0;

		while (i < n) {
            int j = i;
			boolean b = str1[j] == str2[j];
			while (j < n && ((str1[j] == str2[j]) == b)) {
                j++;
            }
            if (!b) {
                ans++;
            }
            i = j;
        }
        pw.println(ans);
        pw.close();

    }
}
