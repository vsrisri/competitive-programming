import java.io.*;
import java.util.*;
public class AngryCown {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("angry.in"));
        PrintWriter pw = new PrintWriter(new File("angry.out"));
        int n = sc.nextInt();
        sc.nextLine();
        int[] arr = new int[n];
        int idx = 0;
        while (sc.hasNextInt()) {
            arr[idx] = sc.nextInt();
        }

        Arrays.sort(arr);
        int ans = 0;

        for (idx = 0; idx < n; idx++) {
            int leftBlast = getBlastIdx(arr, idx, true);

            int rightBlast = getBlastIdx(arr, idx, false);

            int numExploded = rightBlast - leftBlast + 1;

            if (numExploded > ans) {
                ans = numExploded;
            }
        }

        pw.println(ans);
        pw.close();
    }

    public static int getBlastIdx(int[] location, int idx, boolean goLeft) {
        int lastBlastIdx = idx;
        int currRad = 1;
        int dir = 0;
        if (goLeft) {
          dir = -1;
        } else {
          dir = 1;
        }

        while (lastBlastIdx >= 0 && lastBlastIdx <= location.length - 1) {
            int nextIdx = lastBlastIdx;
            while (nextIdx + dir >= 0 && nextIdx + dir < location.length && Math.abs(location[nextIdx + dir] - location[lastBlastIdx]) <= currRad) {
                nextIdx += dir;
            }
            if (nextIdx == lastBlastIdx) {
                break;
            }
            
            lastBlastIdx = nextIdx;
            currRad++;
            System.out.println("lastBlastIdx: " + lastBlastIdx);
        }
        return lastBlastIdx;
    }

}



