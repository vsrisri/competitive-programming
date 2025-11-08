// USACO 2018 December Contest, Bronze Problem 3. Back and Forth
import java.util.*;
import java.io.*;

public class BackAndForth {
    public static ArrayList<Integer> arr1;
    public static ArrayList<Integer> arr2;
    public static HashSet<Integer> hs;
    public static int m;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("backforth.in"));
        PrintWriter pw = new PrintWriter(new File("backforth.out"));
        arr1 = new ArrayList<Integer>();
        arr2 = new ArrayList<Integer>();
        hs = new HashSet<Integer>();
        for (int i = 0; i < 10; i++) {
            arr1.add(sc.nextInt());
        }
        for (int i = 0; i < 10; i++) {
            arr2.add(sc.nextInt());
        }
        m = 1000;
        int ans = hs.size();
        pw.print(ans);
        sc.close();
        pw.close();
    }

    public static void simulate(int day) {
        if (day == 4) {
            hs.add(m);
            return;
        }
        if (day % 2 == 0) {
            for (int idx = 0; idx < arr1.size(); idx++) {
                m -= arr1.get(idx);
                int curr = arr1.remove(idx);
                arr2.add(curr);

                BackAndForth.simulate(day + 1);

                curr = arr2.remove(arr2.size() - 1);
                arr1.add(idx, curr);
                m += arr1.get(idx);
            }
        } else {
            for (int idx = 0; idx < arr2.size(); idx++) {
                m += arr2.get(idx);
                int curr = arr2.remove(idx);
                arr1.add(curr);

                BackAndForth.simulate(day + 1);

                curr = arr1.remove(arr1.size() - 1);
                arr2.add(idx, curr);
                m -= arr2.get(idx);
            }
        }
    }
}
