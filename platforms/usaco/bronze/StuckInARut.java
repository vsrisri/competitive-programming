import java.util.*;
import java.io.*;

public class StuckInARut {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        char[] direc = new char[n];
        for (int idx = 0; idx < n; idx++) {
            direc[idx] = stdin.next().charAt(0);
            x[idx] = stdin.nextInt();
            y[idx] = stdin.nextInt();
        }
        ArrayList<Collision> collisionArr = new ArrayList<Collision>();
        for (int idx = 0; idx < n; idx++) {
            for (int idx2 = 0; idx2 < n; idx2++) {
                if (idx == idx2) {
                    continue;
                }
                int[] arr = helper(idx, idx2, direc, x, y);
                if (arr[0] != Integer.MAX_VALUE) {
                    collisionArr.add(new Collision(arr[0], arr[1], idx, idx2));
                }
            }
        }
        Collections.sort(collisionArr);
        int[] ansArr = new int[n];
        Arrays.fill(ansArr, Integer.MAX_VALUE);
        for (int idx = 0; idx < collisionArr.size(); idx++) {
            if (ansArr[collisionArr.get(idx).cow2] < collisionArr.get(idx).time1) {
                continue;
            }
            ansArr[collisionArr.get(idx).cow1] = Math.min(ansArr[collisionArr.get(idx).cow1], collisionArr.get(idx).time2);
        }

        for (int idx = 0; idx < n; idx++) {
            if (ansArr[idx] == Integer.MAX_VALUE) {
                System.out.println("Infinity");
            } else {
                System.out.println(ansArr[idx]);
            }
        }
        stdin.close();
    }

    public static int[] helper(int idx, int idx2, char[] direc, int[] x, int[] y)  {
        if (direc[idx] == direc[idx2]) {
            if (direc[idx] == 'N') {
                if (x[idx] == x[idx2] && y[idx] <= y[idx2]) {
                    int[] arr = new int[2];
                    arr[0] = 0;
                    arr[1] = y[idx2] - y[idx];
                    return arr;
                } else {
                    int[] arr = new int[2];
                    arr[0] = Integer.MAX_VALUE;
                    arr[1] = Integer.MAX_VALUE;
                    return arr;
                }
            } else {
                if (y[idx] == y[idx2] && x[idx] <= x[idx2]) {
                    int[] arr = new int[2];
                    arr[0] = 0;
                    arr[1] = x[idx2] - y[idx2];
                    return arr;
                } else {
                    int[] arr = new int[2];
                    arr[0] = Integer.MAX_VALUE;
                    arr[1] = Integer.MAX_VALUE;
                    return arr;
                }
            }
        } 
        if (direc[idx] == 'E') {
            if (x[idx2] > x[idx] && y[idx2] <= y[idx] && x[idx2] + y[idx2] > x[idx] + y[idx]) {
                int[] arr = new int[2];
                arr[0] = y[idx] - y[idx2];
                arr[1] = x[idx2] - x[idx];
                return arr;
            } else {
                int[] arr = new int[2];
                arr[0] = Integer.MAX_VALUE;
                arr[1] = Integer.MAX_VALUE;
                return arr;
            }
        } else {
            if (y[idx2] > y[idx] && x[idx2] <= x[idx] && x[idx2] + y[idx2] > x[idx] + y[idx]) {
                int[] arr = new int[2];
                arr[0] = x[idx] - x[idx2];
                arr[1] = y[idx2] - y[idx];
                return arr;
            } else {
                int[] arr = new int[2];
                arr[0] = Integer.MAX_VALUE;
                arr[1] = Integer.MAX_VALUE;
                return arr;
            }

        }
    }

    static class Collision implements Comparable<Collision> {
        public int time1;
        public int time2;
        public int cow1;
        public int cow2;

        Collision(int time1, int time2, int cow1, int cow2) {
            this.time1 = time1;
            this.time2 = time2;
            this.cow1 = cow1;
            this.cow2 = cow2;
        }

        public int compareTo(Collision other) {
            if (this.time1 != other.time1) {
                return this.time1 - other.time1;
            }
            return this.cow1 - other.cow1;
        }
    }
}
