import java.util.*;
import java.io.*;

public class RestStops {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(new File("reststops.in"));
        PrintWriter pw = new PrintWriter(new File("reststops.out"));
        int l = stdin.nextInt();
        int n = stdin.nextInt();int jRate = stdin.nextInt();
        int bRate = stdin.nextInt();
        Stop[] arr = new Stop[n];
        for (int i = 0; i < n; i++) {
            int idx = stdin.nextInt();
            int value =  stdin.nextInt();
            arr[i] = new Stop(idx, value);
        }
        Arrays.sort(arr);
        int currIdx = 0;
        int output = 0;
        for (int i = 0; i < n; i++) {
            if (currIdx >= arr[i].idx) {
                continue;
            }
            int idxDiff = (arr[i].idx - currIdx);
            int time = idxDiff * (jRate - bRate);
            output+= time * arr[i].value;
            currIdx = arr[i].idx;
        }
        pw.println(output);
        stdin.close();
        pw.close();
    }
    public static class Stop implements Comparable<Stop>{
        public int idx;
        public int value;
        public Stop(int idx, int value) {
            this.idx = idx; 
            this.value = value;
        }
        public int compareTo(Stop other) {
            return other.value - this.value;
        }
    }
}
