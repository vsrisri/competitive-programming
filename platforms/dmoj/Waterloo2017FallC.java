import java.io.*;
import java.util.*;

public class Waterloo2017FallC {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        int ans = 0;
        TreeSet<MargObj> margSet = new TreeSet<>();
        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < n; idx++) {
            arr[idx] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        for (int idx = 0; idx < n; idx++) {
            if (idx <= n - k) {
                int m = arr[idx + k - 1] - arr[idx];
                margSet.add(new MargObj(m, idx));
            }

            while (!margSet.isEmpty()) {
                if (margSet.first().index < idx - k + 1) {
                    margSet.pollFirst();
                } else {
                    break;
                }
            }

            if (!margSet.isEmpty()) {
                ans = Math.max(ans, margSet.first().margin);
            }
        }

        System.out.println(ans);
    }

    public static class MargObj implements Comparable<MargObj> {
        public int margin;
        public int index;

        public MargObj(int margin, int index) {
            this.margin = margin;
            this.index = index;
        }

        public int compareTo(MargObj other) {
            return Integer.compare(this.margin, other.margin);
        }
    }

}

