import java.io.*;
import java.util.*;

public class Hydration {
    public static int n;
    public static int m;
    public static int k;
    public static int[] cows;
    public static int[] troughs;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        cows = new int[n];
        troughs = new int[m];
        for (int i = 0; i < n; i++) {
            cows[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(cows);

        for (int i = 0; i < m; i++) {
            troughs[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(troughs);
        System.out.println(findMin());
        br.close();
    }

    public static int findMin() {
        int a = 1;
        int b = n + 1;
        while (a < b) {
            int mid = (a + b) / 2;
            if (helper(mid)) {
                b = mid;
            }
            else {
                a = mid + 1;
            }
        }
        return b > n ? -1 : b;
    }

    public static boolean helper(int max) {
        int currTrough = 0;
        int numCows = 0;
        for (int cow = 0; cow < n; cow++) {
            while (((currTrough < m)) && (numCows == max || isinValid(currTrough, cow))) { 
                numCows = 0;
                currTrough++;
                }
            }

            if (currTrough == m) {
                return false;
            }
            numCows++;
        }
        return true;
    }

    public static boolean isinValid(int currTrough, int cow) {
        return (troughs[currTrough] < cows[cow] - k || troughs[currTrough] > cows[cow]);
    }
}

