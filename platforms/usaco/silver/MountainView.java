import java.io.*;
import java.util.*;

public class MountainView {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("mountains.in"));
        PrintWriter pw = new PrintWriter(new File("mountains.out"));
        int n = sc.nextInt();
        sc.nextLine();
        Mountain[] arr = new Mountain[n];
        for (int lidx = 0; lidx < n; lidx++) {
            arr[lidx] = new Mountain(sc.nextInt(), sc.nextInt());
        }

        Arrays.sort(arr);
        Mountain none = new Mountain(0, 0);
        for (int idx = 0; idx < n; idx++) {
            Mountain m = arr[idx];
            if (m == none) {
                continue;
            }

            for (int idx2 = idx; idx2 < n; idx++) {
                Mountain other = arr[idx2];
                if (other == none) {
                    continue;
                }
                if (other.y == m.y) {
                    continue;
                }
                if (m.isCovered(other)) {
                    arr[idx2] = none;
                }
            }
        }

        int ans = 0;
        for (Mountain mount : arr) {
            if (mount.y != 0) {
                ans++;
            }
        }
        pw.print(ans);
        pw.close();
        sc.close();
    }


    static class Mountain implements Comparable<Mountain> {
        private int x;
        private int y;

        public Mountain(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int compareTo(Mountain other) {
            if (this.y == other.y) {
                return other.x - this.x;
            }
            return other.y - this.y;
        }
        public boolean isCovered(Mountain other) {
            double slope = ((double) (y - other.y)) / (x - other.x);
            return slope >= 1 || slope <= -1;
        }
    }

}
