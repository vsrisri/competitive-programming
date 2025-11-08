// USACO 2020 US Open Contest, Bronze Problem 3. Cowntact Tracing
import java.util.*;
import java.io.*;

public class CowntactTracing {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("tracing.in"));
        PrintWriter p = new PrintWriter(new File("tracing.out"));
        int n = sc.nextInt();
        int numShakes = sc.nextInt();
        sc.nextLine();
        String str = sc.nextLine();
        boolean[] cowResults = new boolean[n + 1];
        for (int idx = 0; idx < n; idx++) {
            if (str.charAt(idx) == '1') {
                cowResults[idx + 1] = true;
            } else {
              cowResults[idx + 1] = false;
            }
        }

        int[] xcows = new int[251];
        int[] ycows = new int[251];
        for (int idx = 0; idx < numShakes; idx++) {
            String line = sc.nextLine();
            int spaceIdx = line.indexOf(' ');
            int t = Integer.parseInt(line.substring(0, spaceIdx));
            int spaceIdx2 = line.indexOf(' ', spaceIdx + 1);
            int x = Integer.parseInt(line.substring(spaceIdx + 1, spaceIdx2));
            int y = Integer.parseInt(line.substring(spaceIdx2 + 1, line.length()));
            xcows[t] = x;
            ycows[t] = y;
        }
        
        boolean[] isPzero = new boolean[101];
        boolean[] isK = new boolean[252];
        for (int idx = 1; idx <= n; idx++) {
            for (int k = 0; k < 252; k++) {
                if (CowntactTracing.checkPatient(n, idx, k, cowResults, xcows, ycows)) {
                    isPzero[idx] = true;
                    isK[k] = true;
                }
                //System.out.println("isPzero[" + idx + "] " + isPzero[idx] + " isK[" + k + "] " + isK[k]);
        
            }
        }
        
        int low = 251;
        int high = 0;
        int numpZero = 0;
        for (int k = 0; k < 252; k++) {
            if (isK[k]) {
                              high = k;
            }
        }

        for (int k = 251; k >= 0; k--) {
            if (isK[k]) {
                low = k;
            }
        }
        for (int i = 1; i <= n; i++) {
            if (isPzero[i]) {
                numpZero++;
            }
        }

        p.print(numpZero);
        p.print(" " + low + " ");
        if (high == 251) {
            p.print("Infinity");
        } else {
            p.print(high);
        }
        
        checkPatient(n, 1, 1, cowResults, xcows, ycows);
        sc.close();
        p.close();
    }

    public static boolean checkPatient(int n, int pzero, int k, boolean[] cowResults, int[] xcows, int[] ycows) {
        boolean[] isInfected = new boolean[n + 1];
        int[] numShakes = new int[n + 1];
        isInfected[pzero] = true;
        for (int t = 0; t < xcows.length; t++) {
            int x = xcows[t];
            int y = ycows[t];
            if (x > 0) {
                if (isInfected[x]) {
                    numShakes[x]++;
                }
                if (isInfected[y]) {
                    numShakes[y]++;
                }
                if ((numShakes[x] <= k) && (isInfected[x])) {
                    isInfected[y] = true;
                }
                if ((numShakes[y] <= k) && (isInfected[y])) {
                    isInfected[x] = true;
                }
                //System.out.println(Arrays.toString(numShakes));
                //System.out.println(Arrays.toString(isInfected));
            }

        }
        //System.out.println(Arrays.toString(isInfected));
        //System.out.println(Arrays.toString(cowResults));
        for (int idx = 0; idx <= n; idx++) {
            if (isInfected[idx] != cowResults[idx]) {
                return false;
            }
        }
        return true;
    }
}




