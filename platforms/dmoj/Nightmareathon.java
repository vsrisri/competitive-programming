import java.io.*;
import java.util.*;

public class Nightmareathon {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        int[] arr = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] prefBest = new int[n + 1];
        int[] prefCount = new int[n + 1];
        prefBest[1] = arr[1];
        prefCount[1] = 1;
        for (int i = 2; i <= n; i++) {
            if (arr[i] > prefBest[i - 1]) {
                prefBest[i] = arr[i];
                prefCount[i] = 1;
            } else if (arr[i] == prefBest[i - 1]) {
                prefBest[i] = arr[i];
                prefCount[i] = prefCount[i - 1] + 1;
            } else {
                prefBest[i] = prefBest[i - 1];
                prefCount[i] = prefCount[i - 1];
            }
        }

        int[] sufBest = new int[n + 2];
        int[] sufCount = new int[n + 2];
        sufBest[n] = arr[n];
        sufCount[n] = 1;
        for (int i = n - 1; i >= 1; i--) {
            if (arr[i] > sufBest[i + 1]) {
                sufBest[i] = arr[i];
                sufCount[i] = 1;
            } else if (arr[i] == sufBest[i + 1]) {
                sufBest[i] = arr[i];
                sufCount[i] = sufCount[i + 1] + 1;
            } else {
                sufBest[i] = sufBest[i + 1];
                sufCount[i] = sufCount[i + 1];
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int lb = (a > 1) ? prefBest[a - 1] : 0;
            int rb = (b < n) ? sufBest[b + 1] : 0;
            int best = 0;
            int count = 0;
            if (lb > rb) {
                best = lb;
                count = prefCount[a - 1];
            } else if (rb > lb) {
                best = rb;
                count = sufCount[b + 1];
            } else {
                best = lb;
                count = 0;
                if (a > 1) {
                    count += prefCount[a - 1];
                }
                if (b < n) {
                    count += sufCount[b + 1];
                }
            }
            sb.append(best);
            sb.append(" ");
            sb.append(count);
            sb.append("\n");
        }
        System.out.print(sb.toString());
        br.close();

    }
}

