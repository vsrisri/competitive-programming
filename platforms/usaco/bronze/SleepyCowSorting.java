// USACO 2019 January Contest, Bronze Problem 2. Sleepy Cow Sorting
import java.util.*;
import java.io.*;

public class SleepyCowSorting {
    public static ArrayList<Integer> arr;
    public static ArrayList<Integer> arrSorted;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("sleepy.in"));
        PrintWriter pw = new PrintWriter(new File("sleepy.out"));
        int n = sc.nextInt();
        sc.nextLine();
        arr = new ArrayList<Integer>();
        while (sc.hasNextInt()) {
            arr.add(sc.nextInt());
        }
        int count = 0;

        for (int idx = 0; idx < n - 1; idx++) {
            if (arr.get(idx) > arr.get(idx + 1)) {
                count = idx + 1;
            }
        }
        pw.print(count);
        sc.close();
        pw.close();

    }
        /*
        Collections.sort(arrSorted);

        int count = 0;
        while (!arr.equals(arrSorted)) {
            int curr = arr.get(0);
            arr.remove(0);
            arr.add(SleepyCowSorting.checkNext(), curr);
            count++;
        }
        System.out.println(count);
        pw.print(count);
        sc.close();
        pw.close();
    }

    public static int checkNext() {
        int curr = arr.get(0);
        for (int idx = 0; idx < arr.size(); idx++) {
            if ((arr.get(idx) == arr.size()) && (curr == 1)) {
                return idx;
            }
            if (arr.get(idx) == curr - 1) {
                return idx;
            }
        }
        return 0;
    }
    */
}
