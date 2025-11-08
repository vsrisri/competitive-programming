import java.util.*;
import java.io.*;

public class CowntactTracing2 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int ans = n;
        String str = reader.readLine();
        ArrayList<Integer> nums = new ArrayList<Integer>();
        int currCount = 0;
        for (int idx = 0; idx < n; idx++) {
            if (str.charAt(idx) == '1') {
                currCount++;
            } else if (currCount > 0) {
                nums.add(currCount);
                currCount = 0;
            }
        }

        if (currCount > 0) {
            nums.add(currCount);
        }

        boolean toBreak = false;
        for (int idx = 1; idx <= n; idx += 2) {
           int curr = 0;
            for (int idx2 = 0; idx2 < nums.size(); idx2++) {
                if ((idx2 == nums.size() - 1 && str.charAt(n - 1) == '1') || (idx2 == 0 && str.charAt(0) == '1')) {
                    if (idx > (nums.get(idx2) * 2) - 1) {
                        toBreak = true;
                        break;
                    }
                } else if (idx > nums.get(idx2)) {
                    toBreak = true;
                    break;
                }
                if (!toBreak) {
                    curr += (idx + nums.get(idx2) - 1) / idx;
                }
            }
            if (toBreak) {
                break;
            }
            ans = Math.min(ans, curr);
        }

        System.out.println(ans);
        reader.close();
    }
}

