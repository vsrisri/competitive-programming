import java.util.*;
import java.io.*;

public class MusicalChairs {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        Chair[] arr = new Chair[n + 1];
        int[] studArr = new int[k + 2];
        int emptyCount = k;
        for (int i = 1; i <= n; i++) {
            arr[i] = new Chair(i, false, 0);
        }

        st = new StringTokenizer(reader.readLine(), " ");
        for (int i = 1; i <= k; i++) {
            int a = Integer.parseInt(st.nextToken());
            arr[a].empty = true;
        }

        st = new StringTokenizer(reader.readLine(), " ");
        for (int i = 1; i <= k + 1; i++) {
            int a = Integer.parseInt(st.nextToken());
            studArr[i] = a;
            arr[a].stud = i;
            if (arr[a].empty) {
                arr[a].empty = false;
                emptyCount--;
                arr[a].done = true;
            }
        }

        System.out.println(helper(arr, studArr, n, k, emptyCount, new Stack<Integer>()));
        reader.close();
    }
    
    public static int helper(Chair[] arr, int[] studArr, int n, int k, int emptyCount, Stack<Integer> stack) {
        while (emptyCount > 0) {
            for (int i = 1; i <= n; i++) {
                Chair chair = arr[i];
                if (chair.stud > 0 && !chair.done) {
                    stack.push(chair.stud);
                    chair.stud = 0;
                } else if (chair.empty && !stack.empty()) {
                    int currStud = stack.pop();
                    chair.stud = currStud;
                    chair.empty = false;
                    emptyCount--;
                    chair.done = true;
                }
            }
        }
        return stack.peek();
    }

    public static class Chair {
        public int idx;
        public boolean empty;
        public int stud;
        public boolean done;

        public Chair(int idx, boolean empty, int stud) {
          this.idx = idx;
          this.empty = empty;
          this.stud = stud;
          this.done = false;
        }
    }
}
