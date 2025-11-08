import java.util.*;
import java.io.*;

public class ClockwiseFence {
    final public static String[] left = {"WS", "EN", "SE", "NW"};
	final public static String[] right = {"WN", "ES", "SW", "NE"};

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        HashMap<String,Integer> map = new HashMap<String,Integer>();

		for (int i = 0; i < left.length; i++) {
            map.put(left[i], -1);
        }
		for (int i = 0; i < right.length; i++) {
            map.put(right[i], 1);
        }

		for (int idx = 0; idx < n; idx++) {
			String dir = stdin.next();
			int count = 0;
			for (int i = 0; i < dir.length() - 1; i++) {
				String tmp = dir.substring(i, i + 2);
				if (map.containsKey(tmp))
					count += map.get(tmp);
			}
			if (count > 0)
				System.out.println("CW");
			else
				System.out.println("CCW");
		}
        sc.close();
    }

}
