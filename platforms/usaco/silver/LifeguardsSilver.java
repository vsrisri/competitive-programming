import java.io.*;
import java.util.*;

public class LifeguardsSilver {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("lifeguards.in"));
        PrintWriter pw = new PrintWriter(new File("lifeguards.out"));
        int n = sc.nextInt();
		int[] on = new int[n];
		int[] off = new int[n];

		TreeSet<Integer> set = new TreeSet<Integer>();
		for (int i = 0; i < n; i++) {
            on[i] = sc.nextInt();
            off[i] = sc.nextInt();
		}

		int id = 0;
		TreeMap<Integer,Integer> map = new TreeMap<Integer,Integer>();
		ArrayList<Integer> gaps = new ArrayList<Integer>();
		int prev = 0;
		boolean iter = false;
		while (set.size() > 0) {
			int next = set.pollFirst();
			map.put(next, id++);
			if (iter) {
                gaps.add(next-prev);
            }
			prev = next;
			iter = true;
		}

		int[] nums = new int[map.size()];
		for (int i = 0; i < n; i++) {
			nums[map.get(on[i])]++;
			nums[map.get(off[i])]--;
		}

		for (int i = 1; i < nums.length; i++)
			nums[i] += nums[i-1];

		int[] arr = new int[map.size()];
		int max = 0;
		for (int i = 0; i < nums.length - 1; i++) {
			if (nums[i] > 0) {
                max += gaps.get(i);
            }
			arr[i + 1] = arr[i];
			if (nums[i] == 1) {
                arr[i + 1]+= gaps.get(i);
            }
		}

		int ans = 0;

		for (int i = 0; i < n; i++) {
			int left = map.get(on[i]);
			int right = map.get(off[i]);
			int sub = arr[right] - arr[left];

			ans = Math.max(ans, max - sub);
		}

		pw.println(ans);
		pw.close();
		sc.close();
	}
}


