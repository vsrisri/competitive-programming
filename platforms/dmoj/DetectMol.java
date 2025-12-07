import java.io.*;
import java.util.*;

public class DetectMol {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        int u = Integer.parseInt(st.nextToken());
        int[] wArr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            wArr[i] = Integer.parseInt(st.nextToken());
        }

        List<Integer> ans = helper(l, u, wArr);
        if (ans.isEmpty()) {
            System.out.println(0);
        } else {
            System.out.println(ans.size());
            for (int i = 0; i < ans.size(); i++) {
                System.out.print(ans.get(i));
                if (i + 1 < ans.size()) {
                    System.out.print(" ");
                }
            }
        }
        br.close();
    }

    public static List<Integer> helper(int l, int u, int[] wArr) {
        int n = wArr.length;
        long tot = 0;
        int right = 0;
        List<Integer> ans = new ArrayList<>();
        Integer[] arr2 = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr2[i] = i;
        }
        Arrays.sort(arr2, Comparator.comparingInt(i -> wArr[i]));
        for (int left = 0; left < n; left++) {
            while (tot < l && right < n) {
                tot += wArr[arr2[right]];
                right++;
            }

            if (l <= tot && tot <= u) {
                for (int i = left; i < right; i++) {
                    ans.add(arr2[i]);
                }
                break;
            }

            if (left < right) {
                tot -= wArr[arr2[left]];
            } else {
                right = left + 1;
            }
        }

        return ans;
    }
}

