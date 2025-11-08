import java.io.*;
import java.util.*;

public class SUBSUMS {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        int[] S = new int[N];
        int mid = N / 2;
        long ans = 0;
        for (int i = 0; i < N; i++) {
            S[i] = Integer.parseInt(br.readLine());
        }
        ArrayList<Long> left = new ArrayList<>();
        ArrayList<Long> right = new ArrayList<>();
        sums(S, 0, mid, left);
        sums(S, mid, N, right);
        Collections.sort(right);
        for (long x : left) {
            long low = A - x;
            long high = B - x;
            int l = lowerBound(right, low);
            int r = upperBound(right, high);
            ans += (r - l);
        }
        System.out.println(ans);
        br.close();
    }

    public static int lowerBound(ArrayList<Long> list, long val) {
        int l = 0;
        int r = list.size();
        while (l < r) {
            int m = (l + r) >>> 1;
            if (list.get(m) < val) {
                l = m + 1; 
            } else {
                r = m;
            }
        }
        return l;
    }

    public static int upperBound(ArrayList<Long> list, long val) {
        int l = 0;
        int r = list.size();
        while (l < r) {
            int m = (l + r) >>> 1;
            if (list.get(m) <= val) {
                l = m + 1; 
            } else { 
                r = m;
            }
        }
        return l;
    }

    public static void sums(int[] arr, int start, int end, ArrayList<Long> list) {
        int n = end - start;
        int total = 1 << n;
        for (int mask = 0; mask < total; mask++) {
            long sum = 0;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    sum += arr[start + i];
                }
            }
            list.add(sum);
        }
    }

}

