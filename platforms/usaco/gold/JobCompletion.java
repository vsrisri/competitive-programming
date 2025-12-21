import java.io.*;
import java.util.*;

public class JobCompletion {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder st = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < t; tc++) {
            int N = Integer.parseInt(br.readLine());
            Job[] jobs = new Job[N];
            long ans = 0;
            for (int i = 0; i < N; i++) {
                String[] parts = br.readLine().split(" ");
                long si = Long.parseLong(parts[0]);
                long ti = Long.parseLong(parts[1]);
                jobs[i] = new Job(si, ti);
            }
            
            Arrays.sort(jobs, Comparator.comparingLong(j -> j.dl));
            PriorityQueue<Long> selArr = new PriorityQueue<>(Collections.reverseOrder());
            for (Job jb : jobs) {
                ans += jb.dur;
                selArr.add(jb.dur);
                if (ans > jb.dl) {
                    ans -= selArr.poll(); 
                }
            }
            
            st.append(selArr.size()).append("\n");
        }
        br.close();
        System.out.print(st);
    }

    public static class Job {
        long dl;
        long dur;
        public Job(long s, long t) {
            this.dl = s + t;
            this.dur = t;
        }
    }
}

