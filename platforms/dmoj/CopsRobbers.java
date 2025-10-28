import java.util.*;
import java.io.*;

public class CopsRobbers {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
        ArrayList<Integer> arr = new ArrayList<Integer>();
        ArrayList<Integer> guarded = new ArrayList<Integer>();
        int[] guardedS = new int[n];
        int[] count = new int[n];

        for (int idx = 0; idx < n; idx++) {
            int a = Integer.parseInt(st.nextToken());
            arr.add(a);
            guardedS[a - 1] = 1;

            if (count[a - 1] == 0) {
                guarded.add(a);
            }
            count[a - 1]++;
        }
        reader.close();

        if (guarded.size() == 1) {
            System.out.println(-1);
        } else {
            int[] ans = new int[n];
            count = new int[n];
            int idx = 0;
            int bIdx = guarded.size() - 1;

            for (int cIdx = 0; cIdx < arr.size(); cIdx++) {
                int a = arr.get(cIdx);
                if (a == guarded.get(idx) && count[a - 1] == 0) {
                    ans[cIdx] = guarded.get(bIdx);
                    if (idx == guarded.size() - 1) {
                        break;
                    }
                    bIdx = idx;
                    idx++;
                    count[a - 1]++;
                }
            }

            ArrayList<Integer> temp = new ArrayList<Integer>();
            for (int i = 1; i <= n; i++) {
                if (guardedS[i - 1] == 0) {
                    temp.add(i);
                }
            }

            int tIdx = 0;
            for (int i = 0; i < n; i++) {
                if (ans[i] == 0) {
                    ans[i] = temp.get(tIdx);
                    tIdx++;
                }
            }
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < n; i++) {
                str.append(ans[i]);;
                str.append(" ");
            }
            System.out.println(str);
        }
    }
}
