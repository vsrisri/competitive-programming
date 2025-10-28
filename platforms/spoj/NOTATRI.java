import java.util.*;
import java.io.*;

public class NOTATRI {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        while (n != 0) {
            StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
            ArrayList<Integer> arr = new ArrayList<Integer>();
            for (int idx = 0; idx < n; idx++) {
                arr.add(Integer.parseInt(st.nextToken()));
            }
            Collections.sort(arr);

            int ans = 0;
            for (int idx = 0; idx < n - 2; idx++) {
                for (int idx2 = idx + 1; idx2 < n - 1; idx2++) {
                    int i = helper(idx2 + 1, n - 1, arr, arr.get(idx) + arr.get(idx2));
                    if (i != -1) {
                        ans += (n - 1 - i);
                    } else {
                        ans += (n - 1 - idx2);
                    }
                }
            }

            System.out.println(ans);
            n = Integer.parseInt(reader.readLine());
        }
    }

    public static int helper(int lhs, int rhs, ArrayList<Integer> arr, int sum) {
        int mid = (lhs + rhs) / 2;
        if (lhs > rhs) {
            return -1;
        }
        if (lhs == rhs) {
            if (sum >= arr.get(lhs)) {
                return lhs;
            }
            return -1;
        }

        if (arr.get(mid) > sum) {
            return helper(lhs, mid - 1, arr, sum);
        } else if (arr.get(mid) <= sum) {
            if (arr.get(mid + 1) > sum) {
                return mid;
            }
            return helper(mid + 1, rhs, arr, sum);
        }
        return -1;
    }
}


