import java.util.*;
import java.io.*;

public class LoanRepaymentSilver {
  public static void main(String[] args) throws Exception {
    Scanner stdin = new Scanner(new File("loan.in"));
    long n = stdin.nextLong();
    long k = stdin.nextLong();
    long m = stdin.nextLong();
    long low = 1, high = n;

    while (low < high) {
      long mid = (low + high + 1) / 2;
      if (isPoss(n, k, m, mid)) {
        low = mid;
      } else {
        high = mid - 1;
      }
    }

    PrintWriter pw = new PrintWriter(new File("loan.out"));
    pw.print(low);
    stdin.close();
    pw.close();
  }

  public static boolean isPoss(long n, long k, long m, long x) {
    long t = 0;
    long g = 0;
    while (t < k && g < n) {
      long y = (n - g) / x;
      long days = 0;
      if (y < m) {
        y = m;
        days = Math.min(k - t, (n - g + m - 1) /y);
      }
      else {
        days = ((n - g) % x)/y + 1;
      }
      g+=days*y;
      t+= days;
    }
    return g >= n;
  }

}
