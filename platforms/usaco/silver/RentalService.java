// USACO 2018 January Contest, Silver
// Problem 2. Rental Service
import java.util.*;
import java.io.*;

public class RentalService {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("rental.in"));
        PrintWriter pw = new PrintWriter(new File("rental.out"));
        int n = sc.nextInt();
        int m = sc.nextInt();
        int r = sc.nextInt();
        sc.nextLine();

        ArrayList<Integer> arr = new ArrayList<Integer>();
        for (int idx = 0; idx < n; idx++) {
            arr.add(sc.nextInt());
        }
        sc.nextLine();

        Collections.sort(arr, Collections.reverseOrder());
        ArrayList<Store> stores = new ArrayList<Store>();

        for (int idx = 0; idx < n; idx++) {
            int a = sc.nextInt();
            int c = sc.nextInt();
            stores.add(new Store(a, c));
        }
        Collections.sort(stores);
        sc.nextLine();

        ArrayList<Integer> rentArr = new ArrayList<Integer>();
        for (int idx = 0; idx < r; idx++) {
            rentArr.add(sc.nextInt());
        }

        Collections.sort(rentArr, Collections.reverseOrder());
        long[] pArr = new long[n];
        long prevProf = 0;

        for (int idx = 0; idx < n; idx++) {
            int ans = arr.get(idx);
            long curProf = 0;
            for (int idx2 = stores.size() - 1; idx2 >= 0; idx2--) {
                if (ans >= stores.get(idx2).amount) {
                    ans -= stores.get(idx2).amount;
                    curProf += stores.get(idx2).amount * stores.get(idx2).cost;
                    stores.remove(idx2);
                } else {
                    stores.get(idx2).amount-= ans;
                    curProf += ans * stores.get(idx2).cost;
                    ans = 0;
                }

                if (ans == 0) {
                    break;
                }
            }
            pArr[idx] = prevProf + curProf;
            prevProf = pArr[idx];
        }

        long p = 0;
        for (int idx = 1; idx < n; idx++) {
            if (idx <= r) {
                p += rentArr.get(idx - 1);
            }
            pArr[n - idx - 1] += p;
        }

        long max = 0;
        for (int idx = 0; idx < pArr.length; idx++) {
            max = Math.max(max, pArr[idx]);
        }

        pw.print(max);
        sc.close();
        pw.close();
    }

    static class Store implements Comparable<Store> {
        int amount, cost;
        Store (int amount, int cost) {
            this.amount = amount;
            this.cost = cost;
        }
        public int compareTo(Store other) {
            return cost - other.cost;
        }
    }
}
