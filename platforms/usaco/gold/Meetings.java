import java.util.*;
import java.io.*;

public class Meetings {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(new File("meetings.in"));
        PrintWriter pw = new PrintWriter(new File("meetings.out"));
        int n = stdin.nextInt();
        int l = stdin.nextInt();
        int barn1 = 0;
        int barn2 = l;
        Cow[] cows = new Cow[n];
        HashSet<Cow> barnCollision = new HashSet<Cow>();
        for (int idx = 0; idx < n; idx++) {
          int a = stdin.nextInt();
          int b = stdin.nextInt();
          int c = stdin.nextInt();
          cows[idx] = new Cow(a, b, c);
        }

        int count = 0;
        while ((findTotalSum(barnCollision, cows)/2) <= findWeightSum(barnCollision)) {
            Cow[] collision = findCollision(cows, n);
            count++;
            int time = (collision[0].x - collision[1].x) / 2;
            for (int idx = 0; idx < n; idx++) {
                if (!barnCollision.contains(cows[idx])) {
                    cows[idx].x += (time * cows[idx].d);
                }
                if (idx == collision[0].x || idx == collision[1].x) {
                    collision[idx].d *= -1;
                }
                if (cows[idx].x <= 0 || cows[idx].x >= l) {
                    barnCollision.add(cows[idx]);
                }
            }
        }
        pw.println(count);
    }
      
    public static int findWeightSum(HashSet<Cow> barnCollision) {
        int sum = 0;
        for (Cow c : barnCollision) {
            sum+= c.w; 
        }
    }

    public static int findTotalSum(HashSet<Cow> barnCollision, Cow[] cows) {
        int sum = findWeightSum(barnCollision);
        for (int idx = 0; idx < cows.length; idx++) {
          sum+= cows[idx].w;
        }
    }
      
    public static Cow[] findCollision(Cow[] cows, int n) {
        Cow[] collision = new Cow[2];
        int minDiff = Integer.MAX_VALUE;
        Cow prev = cows[0];
        for (int idx = 1; idx < n; idx++) {
            if (cows[idx].x - prev.x > minDiff) {
                minDiff = cows[idx].x - prev.x;
                collision[0] = prev;
                collision[1] = cows[idx];
            }
        }
    }

    class Cow {
        int w;
        int x; 
        int d;
        Cow(int w, int x, int d) {
          this.w = w;
          this.x = x;
          this.d = d;
        }
    }
}
