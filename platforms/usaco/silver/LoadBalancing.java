import java.util.*;
import java.io.*;

public class LoadBalancing {
  public static void main(String[] args) throws Exception {
    Scanner stdin = new Scanner(new File("balancing.in"));
    PrintWriter pw = new PrintWriter(new File("balancing.out"));
    int n = stdin.nextInt();
    Cow[] cows = new Cow[n];
    for (int idx = 0; idx < n; idx++) {
      cows[idx] = new Cow(stdin.nextInt(), stdin.nextInt());
    }
    Arrays.sort(cows);
    
    int ans = Integer.MAX_VALUE;
    for (int idx = 0; idx < n; idx++) {
      for (int idx2 = 0; idx2 < n; idx2++) {
        int leftBottom = 0;
        int leftTop = 0;
        for (int i = 0; i <= idx; i++) {
          if (cows[i].y <= cows[idx2].y) {
            leftBottom++;
          } else {
            leftTop++;
          }
          
        }
        int rightBottom = 0;
        int rightTop = 0;
        for (int i = idx + 1; i < n; i++) {
          if (cows[i].y <= cows[idx2].y) {
            rightBottom++;
          } else {
            rightTop++;
          }
        }
        int temp = Math.max(rightTop, leftTop);
        temp = Math.max(temp, leftBottom);
        temp = Math.max(temp, rightBottom);
        ans = Math.min(temp, ans); 
      }
    }
    pw.print(ans);
    stdin.close();
    pw.close();
  }

  static class Cow implements Comparable<Cow> {
    public int x;
    public int y;
    public Cow(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public int compareTo(Cow other) {
      return this.x - other.x;
    }
  }
}

