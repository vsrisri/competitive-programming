import java.util.*;
import java.io.*;

public class CowCrossing3 {
    static class Time implements Comparable<Time> {
        public int start;
        public int len;
        public Time(int start, int len) {
            this.start = start;
            this.len = len;
        }
        public int compareTo(Time t2) {
            return this.start - t2.start;
        }
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("cowqueue.in"));
        PrintWriter pw  = new PrintWriter(new File("cowqueue.out"));
        int n = sc.nextInt();
        sc.nextLine();
        Time[] arr = new Time[n];
        for (int idx = 0; idx < n; idx++) {
            int start = sc.nextInt();
            int len = sc.nextInt();
            arr[idx] = new Time(start, len);
        }
        Arrays.sort(arr);
        int currTime = 0;
        for (int idx = 0; idx < n; idx++) {
            currTime = Math.max(currTime, arr[idx].start);
            currTime+= arr[idx].len;
        }
        pw.print(currTime);
        sc.close();
        pw.close();
    }
}
