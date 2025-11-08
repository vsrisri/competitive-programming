// USACO 2022 Feb Silver
// Incomplete
import java.util.*;
import java.io.*;

public class RobotInstructions {
    static class Pair {
        long first, second;

        public Pair(long first, long second) {
            this.first = first;
            this.second = second;
        }

        public Pair add(Pair other) {
            return new Pair(this.first + other.first, this.second + other.second);
        }

        public Pair subtract(Pair other) {
            return new Pair(this.first - other.first, this.second - other.second);
        }
    }

    public static List<Pair> findSubsets(List<Pair> dirs) {
        List<Pair> subsets = new ArrayList<>();
        subsets.add(new Pair(0, 0));

        for (Pair d : dirs) {
            int size = subsets.size();
            subsets.addAll(subsets.subList(0, size));

            for (int i = 0; i < size; i++) {
                subsets.set(i + size, subsets.get(i).add(d));
            }
        }

        Collections.sort(subsets, (a, b) -> {
            if (a.first == b.first) {
                return Long.compare(a.second, b.second);
            }
            return Long.compare(a.first, b.first);
        });

        return subsets;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Pair goal = new Pair(scanner.nextLong(), scanner.nextLong());

        List<Pair> dirs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            dirs.add(new Pair(scanner.nextLong(), scanner.nextLong()));
        }

        List<Pair> a = findSubsets(dirs.subList(0, n / 2));
        List<Pair> b = findSubsets(dirs.subList(n / 2, n));
        Collections.reverse(b);

        long[] ans = new long[n + 1];
        List<Integer> withNum = new ArrayList<>();
        Pair restPrev = new Pair((int) 1e18, (int) 1e18);
        int count = 0;

        for (Pair curr : a) {
            Pair rest = goal.subtract(curr);
            if (!rest.equals(restPrev)) {
                restPrev = rest;
                withNum = new ArrayList<>(Collections.nCopies(n - n / 2 + 1, 0));

                while (count < b.size() && b.get(count).first > rest.first) {
                    count++;
                }

                while (count < b.size() && b.get(count).first == rest.first) {
                    withNum.set((int) b.get(count).second, withNum.get((int) b.get(count).second) + 1);
                    count++;
                }
            }

            for (int i = 0; i < withNum.size(); i++) {
                ans[i + (int) curr.second] += withNum.get(i);
            }
        }

        for (int i = 1; i <= n; i++) {
            System.out.println(ans[i]);
        }
        scanner.close();
    }
}

