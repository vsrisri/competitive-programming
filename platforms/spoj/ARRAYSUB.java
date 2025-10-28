import java.util.*;
import java.io.*;

public class ARRAYSUB {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] arr = new int[n];
        for (int idx = 0; idx < n; idx++) {
            arr[idx] = Integer.parseInt(st.nextToken());
        }
        int k = Integer.parseInt(br.readLine());
        Deque<Integer> dq = new ArrayDeque<>();
        for (int idx = 0; idx < n; idx++) {
            while (!dq.isEmpty() && dq.peekFirst() <= idx - k) {
                dq.pollFirst();
            }
            while (!dq.isEmpty() && arr[dq.peekLast()] <= arr[idx]) {
                dq.pollLast();
            }

            dq.offerLast(idx);
            if (idx >= k - 1) {
                System.out.print(arr[dq.peekFirst()] + " ");
            }
        }
        br.close();
    }
}

