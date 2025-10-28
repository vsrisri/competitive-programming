import java.io.*;
import java.util.*;

public class Sorting {
    public static class FreqEnt implements Comparable<FreqEnt> {
        int freq;
        int origIdx;
        FreqEnt(int freq, int origIdx) {
            this.freq = freq;
            this.origIdx = origIdx;
        }

        public int compareTo(FreqEnt o) {
            return Integer.compare(this.freq, o.freq);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long m = Long.parseLong(st.nextToken());
        long k = Long.parseLong(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        FreqEnt[] arr = new FreqEnt[n];
        long[] prefixSum = new long[n + 1];
        int[] posArr = new int[n];
        int count = 1;
        int minValid = 0;
        int maxValid = n;
        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < n; idx++) {
            arr[idx] = new FreqEnt(Integer.parseInt(st.nextToken()), idx);
        }

        Arrays.sort(arr);
        for (int idx = 1; idx <= n; idx++) {
            prefixSum[idx] = prefixSum[idx - 1] + arr[idx - 1].freq;
        }

        for (; minValid <= n; minValid++) {
            if (prefixSum[minValid] >= k) {
                break;
            }
        }

        for (; maxValid >= 1; maxValid--) {
            if (prefixSum[n] - prefixSum[maxValid] >= k) {
                break;
            }
        }

        boolean imposs = (x > minValid || x < n - maxValid);
        if (imposs) {
            System.out.println(-1);
            return;
        }

        for (int end = x; end <= n; end++) {
            int start = end - x;
            long windowSum = prefixSum[end] - prefixSum[start];
            if (windowSum >= k) {
                int assign = 1;
                for (int idx = start; idx < end; idx++) {
                    posArr[arr[idx].origIdx] = assign++;
                }

                for (int idx = 0; idx < n; idx++) {
                    if (posArr[idx] == 0) {
                        posArr[idx] = assign++;
                    }
                }

                helper(posArr);
                return;
            }
        }
    }

    public static void helper(int[] in) {
        System.out.println(Arrays.toString(in).replaceAll("[\\[\\],]", ""));
    }
}

