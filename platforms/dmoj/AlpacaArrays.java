import java.io.*;
import java.util.*;

public class AlpacaArrays {
    public static int helper(List<Integer> arr, int value) {
        int low = 0;
        int high = arr.size();
        while (low < high) {
            int mid = (low + high) / 2;
            if (arr.get(mid) >= value) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        int[] a = new int[n + 1];
        ArrayList<Integer>[] idxArr = new ArrayList[100002];
        for (int i = 1; i < 100002; i++) {
            idxArr[i] = new ArrayList<>();
        }
        
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
            idxArr[a[i]].add(i);
        }
        
        boolean isFound = false;
        for (int qIdx = 0; qIdx < q; qIdx++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            for (int i = 1; i * i < x; i++) {
                if (x % i != 0 || x / i >= 100002) {
                    continue;
                }
                
                int fi1 = helper(idxArr[i], l);
                int fi2 = helper(idxArr[x / i], l);
                if (fi1 < idxArr[i].size() && fi2 < idxArr[x / i].size()) {
                    if (idxArr[i].get(fi1) <= r && idxArr[x / i].get(fi2) <= r) {
                        System.out.println("YES");
                        isFound = true;
                        break;
                    }
                }
            }
            
            if (!isFound) {
                System.out.println("NO");
            }
            isFound = false;
        }
    }
}

