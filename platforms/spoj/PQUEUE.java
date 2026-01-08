import java.io.*;
import java.util.*;

public class PQUEUE {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int[] arr = new int[10];
            int time = 0;
            st = new StringTokenizer(br.readLine());
            Queue<Job> q = new ArrayDeque<>();
            for (int i = 0; i < n; i++) {
                int p = Integer.parseInt(st.nextToken());
                arr[p]++;
                q.add(new Job(p, i == m));
            }

            while (true) {
                Job curr = q.poll();
                boolean higher = false;
                for (int i = curr.p + 1; i <= 9; i++) {
                    if (arr[i] > 0) {
                        higher = true;
                        break;
                    }
                }

                if (higher) {
                    q.add(curr);
                } else {
                    time++;
                    arr[curr.p]--;
                    if (curr.mi) {
                        sb.append(time).append('\n');
                        break;
                    }
                }
            }
        }
        System.out.print(sb.toString());
        br.close();
    }

    public static class Job {
        int p;
        boolean mi;
        Job(int p, boolean mi) {
            this.p = p;
            this.mi = mi;
        }
    }

}

