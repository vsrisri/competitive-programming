import java.io.*;
import java.util.*;

public class FieldReduction {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(new File("reduce.in"));
        PrintWriter pw = new PrintWriter(new File("reduce.out"));
        int n = stdin.nextInt();
        Cow[] cows = new Cow[n];
        for (int idx = 0; idx < n; idx++) {
            cows[idx] = new Cow(stdin.nextInt(), stdin.nextInt());
        }
        Arrays.sort(cows);
        for (int idx = 0; idx < 3; idx++) {
            cows[idx].isRemovable = true;
        }
        for (int idx = n - 1; idx > n - 4; idx--) {
            cows[idx].isRemovable = true;
        }

        for (int idx = 0; idx < n; idx++) {
            cows[idx].sortY = true;
        }

        Arrays.sort(cows);
        for (int idx = 0; idx < 3; idx++) {
            cows[idx].isRemovable = true;
        }

        for (int idx = n - 1; idx > n - 4; idx--) {
            cows[idx].isRemovable = true;
        }

        ArrayList<Cow> canRemove = new ArrayList<Cow>();
        for (int idx = 0; idx < n; idx++) {
            if (cows[idx].isRemovable) {
                canRemove.add(cows[idx]);
            }
        }

        int ans = Integer.MAX_VALUE;
        for (Cow a : canRemove) {
            for (Cow b : canRemove) {
                for (Cow c : canRemove) {
                    if (a.equals(b) || b.equals(c) || a.equals(c)) {
                        continue;
                    }
                    ans = Math.min(ans, helper(cows, a, b, c));
                }
            }
        }

        pw.println(ans);
        stdin.close();
        pw.close();
    }

    public static int helper(Cow[] cows, Cow a, Cow b, Cow c) {
        int maxX = Integer.MIN_VALUE;
        int minX = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;
        for (Cow curr : cows) {
            if (curr.equals(a) || curr.equals(b) || curr.equals(c)) {
                continue;
            }
            maxX = Math.max(maxX, curr.x);
            minX = Math.min(minX, curr.x);
            maxY = Math.max(maxY, curr.y);
            minY = Math.min(minY, curr.y);
        }

        return (Math.abs(maxX - minX) * Math.abs(maxY - minY));
    }

    static class Cow implements Comparable<Cow> {
        public int x;
        public int y;
        public boolean isRemovable;
        public boolean sortY;
        Cow (int x, int y) {
            this.x = x;
            this.y = y;
            this.isRemovable = false;
            this.sortY = false;
        }
        
        public int compareTo(Cow other) {
            if (other.sortY == true) {
                return this.y - other.y;
            }
            return this.x - other.x;
        }
    }
}

