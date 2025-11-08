import java.util.*;
import java.io.*;

public class MilkVisits {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("lemonade.in"));
        PrintWriter pw = new PrintWriter(new File("lemonade.out"));
        int n = sc.nextInt();
        sc.nextLine();
        ArrayList<Integer> vals = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
			vals.add(sc.nextInt());
        }
        Collections.sort(vals);
		Collections.reverse(vals);

        int ans = 0;
		for (int i = 0; i < n; i++) {
			if (vals.get(i) >= ans) {
				ans++;
            }
        }
        pw.print(ans);
        sc.close();
        pw.close();

    }

}
