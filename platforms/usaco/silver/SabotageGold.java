// My solution does not work but matches the official solution. This might be something to do with java or could be a small bug.
import java.util.*;
import java.io.*;

public class SabotageGold {
    public static int n;
    public static int[] arr;

    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(new File("sabotage.in"));
        PrintWriter pw = new PrintWriter(new File("sabotage.out"));
        n = stdin.nextInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = stdin.nextInt();
        }
        double ans = Double.parseDouble(String.format("%.3f %n", solve()));
        pw.print(ans);
        stdin.close();
        pw.close();
    }

    public static int helper(double d) {
        return (int) (1000.0 * d + 0.5);
    }
/*
    public static boolean isPoss(double guess) {
        int idx, total = 0;
        double best, curr;
        for (idx = 0; idx < n; idx++) {
            total += arr[idx];
        }
        best = curr = arr[1] - guess;

        for (idx = 2; idx < n - 1; idx++) {
            if (curr < 0) {
                curr = 0;
            }
            curr+= arr[idx] - guess;
            if (curr > best) {
                best = curr;
            }
        }
        return best >= total - guess * n;
    }
*/

    public static boolean possible(double guess) {
      int i, total=0;
      double best, current;
      for (i=0; i<n; i++) total += arr[i];
      best = current = arr[1] - guess;
      for (i=2; i<n-1; i++) {
        if (current < 0) current = 0;
        current += arr[i] - guess;
        if (current > best) best = current;
      }
      return best >= total - guess * n;
    }


  

    public static double solve() {
      double low = 1.0, high = n * 10000.0;
      while (helper(low)!= helper(high)) {
        if (possible((low + high) / 2)) {
            high = (low + high) / 2;
        } else {
            low = (low + high) / 2;
        }
      }
      return low;
    }
/*
import java.util.*;
import java.io.*;

public class SabotageGold {
    public static int n; 
    public static int[] arr;

    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(new File("sabotage.in"));
        PrintWriter pw = new PrintWriter(new File("sabotage.out"));
        n = stdin.nextInt();
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = stdin.nextInt();
        }
        double ans = Double.parseDouble(String.format("%.3f %n", solve()));
        pw.print(ans);
        stdin.close();
        pw.close();
    }

    public static int helper(double d) {
        return (int) (1000.0 * d + 0.5);
    }

    public static boolean isPoss(double guess) {
        int idx, total = 0;
        double best, curr;
        for (idx = 0; idx < n; idx++) {
            total += arr[idx];
        }
        best = curr = arr[1] - guess;

        for (idx = 2; idx < n - 1; idx++) {
            if (curr < 0) {
                curr = 0;
            }
            curr+= arr[idx] - guess;
            if (curr > best) {
                best = curr;
            }
        }
        return best >= total - guess * n;
    }

    public static double solve() {
      double low = 1.0, high = 10000.0 * 10000.0;
      while (helper(low)!= helper(high)) {
        if (isPoss((low + high) / 2)) {
            high = (low + high) / 2;
        } else {
            low = (low + high) / 2;
        }
      }
      return low;
    }
}
*/
}
