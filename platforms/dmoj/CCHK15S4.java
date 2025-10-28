import java.util.*;
import java.io.*;

public class CCHK15S4 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(reader.readLine(), " ");
        int[] arr = new int[n + 1];

        for (int idx = 1; idx <= n; idx++) {
            arr[idx] = Integer.parseInt(st.nextToken());
        }

        HashMap<Integer, Boolean> isSeen = new HashMap<Integer, Boolean>();
        HashMap<Integer, Boolean> inCycle = new HashMap<Integer, Boolean>();
        for (int idx = 1; idx <= n; idx++) {
            isSeen.put(idx, false);
            inCycle.put(idx, false);
        }

        int beforeCycle = 0;
        int curr = 1;
        while (!isSeen.get(curr)) {
            isSeen.put(curr, true);
            curr = arr[curr];
        }

        int cycle = 1;
        int curr2 = arr[curr];
        inCycle.put(curr, true);
        int prev = curr;
        while (curr2 != curr) {
            cycle++;
            prev = curr2;
            inCycle.put(curr2, true);
            curr2 = arr[curr2];
        }

        int start = curr;
        int end = prev;
        beforeCycle = helper(n, arr, start, end, inCycle);
        System.out.print(2 * (beforeCycle + cycle - 1));
    }

    public static int helper(int n, int[] arr, int start, int end, HashMap<Integer, Boolean> inCycle) {
        HashMap<Integer, Boolean> isSeen = new HashMap<Integer, Boolean>();
        HashMap<Integer, Integer> dist = new HashMap<Integer, Integer>();

        for (int idx = 1; idx <= n; idx++) {
            isSeen.put(idx, false);
            dist.put(idx, -1);
        }

        if (start > end) {
            int temp = start;
            start = end;
            end = temp;
        }

        int max = 0;
        for (int idx = 1; idx <= n; idx++) {
            if (isSeen.get(idx) || inCycle.get(idx)) {
                continue;
            }
            ArrayList<Integer> path = new ArrayList<Integer>();
            path.add(idx);
            int current = arr[idx];
            int length = 1;
            while (!inCycle.get(current) && dist.get(current) == -1) {
                length++;
                path.add(current);
                current = arr[current];
            }
            if (dist.get(current) != -1) {
                length += dist.get(current);
            }
            max = Math.max(length, max);

            for (int num : path) {
                isSeen.put(num, true);
                dist.put(num, length);
                length--;
            }
        }
        return max;
    }
}
