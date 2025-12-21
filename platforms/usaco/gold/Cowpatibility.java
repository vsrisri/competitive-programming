import java.io.*;
import java.util.*;

public class Cowpatibility {
    static long[] factors = {-1, 1, -1, 1, -1, 1};

    static class Combo implements Comparable<Combo> {
        int[] flavors = new int[5];
        int size = 0;

        void add(int flavor) {
            flavors[size++] = flavor;
        }

        public int compareTo(Combo other) {
            for (int i = 0; i < 5; i++) {
                if (this.flavors[i] < other.flavors[i]) {
                    return -1;
                }

                if (this.flavors[i] > other.flavors[i]) {
                    return 1;
                }
            }
            return 0;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Combo other = (Combo) obj;
            return Arrays.equals(flavors, other.flavors);
        }

        public int hashCode() {
            return Arrays.hashCode(flavors);
        }
    }

    static Combo getSubset(Combo c, int mask) {
        Combo subset = new Combo();
        for (int i = 0; i < 5; i++) {
            if ((mask & (1 << i)) != 0) {
                subset.add(c.flavors[i]);
            }
        }
        return subset;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cowpatibility.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowpatibility.out")));

        int n = Integer.parseInt(br.readLine());
        long totalPairs = (long) n * (n - 1) / 2;
        Map<Combo, Integer> count = new HashMap<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Combo combo = new Combo();
            for (int j = 0; j < 5; j++) {
                combo.add(Integer.parseInt(st.nextToken()));
            }
            Arrays.sort(combo.flavors);

            for (int mask = 1; mask < 32; mask++) {
                Combo subset = getSubset(combo, mask);
                count.put(subset, count.getOrDefault(subset, 0) + 1);
            }
        }

        long compPairs = 0;
        for (Map.Entry<Combo, Integer> entry : count.entrySet()) {
            int size = entry.getKey().size;
            int occ = entry.getValue();
            compPairs += factors[size] * (long) occ * (occ - 1) / 2;
        }

        pw.println(totalPairs - compPairs);
        pw.close();
    }
}

