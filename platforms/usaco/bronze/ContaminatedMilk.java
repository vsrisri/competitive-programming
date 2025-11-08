// USACO 2015 December Contest, Bronze Problem 3. Contaminated Milk
import java.util.*;
import java.io.*;

public class ContaminatedMilk {
    public static int[] pDrink;
    public static int[] mDrink;
    public static int[] tDrink;
    public static int[] pSick;
    public static int[] tSick;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("badmilk.in"));
        PrintWriter p = new PrintWriter(new File("badmilk.out"));

        int n = sc.nextInt();
        int m = sc.nextInt();
        int d = sc.nextInt();
        int s = sc.nextInt();

        sc.nextLine();
        pDrink = new int[d];
        mDrink = new int[d];
        tDrink = new int[d];
        pSick = new int[s];
        tSick = new int[s];

        for (int idx = 0; idx < d; idx++) {
            pDrink[idx] = sc.nextInt();
            mDrink[idx] = sc.nextInt();
            tDrink[idx] = sc.nextInt();
            sc.nextLine();
        }

        for (int idx = 0; idx < s; idx++) {
            pSick[idx] = sc.nextInt();
            tSick[idx] = sc.nextInt();
            sc.nextLine();
        }

        int max = 0;
        for (int idx = 0; idx <= m; idx++) {
            if (ContaminatedMilk.isMilkTypeBad(idx)) {
                int numDrunk = ContaminatedMilk.checkNumDrunkType(idx);
                if (numDrunk > max) {
                    max = numDrunk;
                }
            }
        }

        p.print(max);
        sc.close();
        p.close();
    }

    public static boolean isMilkTypeBad(int type) {
        for (int idx = 0; idx < pSick.length; idx++) {
            if (!ContaminatedMilk.didpDrinkBefore(type, pSick[idx], tSick[idx])) {
                return false;
            }
        }
        return true;
    }

    public static boolean didpDrinkBefore(int type, int p, int t) {
        for (int idx = 0; idx < pDrink.length; idx++) {
            if ((pDrink[idx] == p) && (mDrink[idx] == type) && (tDrink[idx] < t)) {
                return true;
            }
        }
        return false;
    }

    public static int checkNumDrunkType(int type) {
        boolean[] didDrink = new boolean[51];
        for (int idx = 0; idx < pDrink.length; idx++) {
            if (mDrink[idx] == type) {
                didDrink[pDrink[idx]] = true;
            }
        }

        int num = 0;
        for (int idx = 1; idx < didDrink.length; idx++) {
            if (didDrink[idx]) {
                num++;
            }
        }
        return num;
    }
}
