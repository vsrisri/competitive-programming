import java.util.*;

public class TargetPractice2 {
    static boolean isValid(List<long[]> positiveNeeds, List<Integer> positiveSlopes, long minY) {
        List<Long> maxSlope = new ArrayList<>();
        for (long[] xy : positiveNeeds) {
            long x = xy[0], y = xy[1];
            maxSlope.add((y - minY) / x);
        }
        Collections.sort(maxSlope);
        for (int i = 0; i < positiveSlopes.size(); i++) {
            if (maxSlope.get(i) < positiveSlopes.get(i)) return false;
        }
        return true;
    }

    static long findMinimum(List<long[]> positiveNeeds, List<Integer> positiveSlopes) {
        Collections.sort(positiveSlopes);

        long minY = positiveNeeds.stream().mapToLong(xy -> xy[1]).min().orElse(Long.MAX_VALUE);
        long high = minY;
        long low = minY - positiveSlopes.get(positiveSlopes.size() - 1) * positiveNeeds.stream().mapToLong(xy -> xy[0]).max().orElse(1);

        while (low < high) {
            long mid = (low + high + 1) / 2;
            if (isValid(positiveNeeds, positiveSlopes, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    static long findMaximum(List<long[]> negativeNeeds, List<Integer> negativeSlopes) {
        List<long[]> flippedNeeds = new ArrayList<>();
        List<Integer> flippedSlopes = new ArrayList<>();
        for (long[] xy : negativeNeeds) {
            flippedNeeds.add(new long[]{xy[0], -xy[1]});
        }
        for (int s : negativeSlopes) {
            flippedSlopes.add(-s);
        }
        return -findMinimum(flippedNeeds, flippedSlopes);
    }

    static void processTestCase(Scanner sc) {
        int N = sc.nextInt();
        int x1 = sc.nextInt();
        
        List<Long> valuesAtX1 = new ArrayList<>();
        List<long[]> positiveNeeds = new ArrayList<>();
        List<long[]> negativeNeeds = new ArrayList<>();
        
        for (int i = 0; i < N; i++) {
            long y1 = sc.nextLong();
            long y2 = sc.nextLong();
            long x2 = sc.nextLong();
            valuesAtX1.add(y1);
            valuesAtX1.add(y2);
            positiveNeeds.add(new long[]{x2, y1});
            negativeNeeds.add(new long[]{x2, y2});
        }
        
        List<Integer> slopes = new ArrayList<>();
        for (int i = 0; i < 4 * N; i++) {
            slopes.add(sc.nextInt());
        }
        
        List<Integer> negativeSlopes = new ArrayList<>();
        List<Integer> positiveSlopes = new ArrayList<>();
        
        for (int s : slopes) {
            if (s < 0) {
                negativeSlopes.add(s);
            } else {
                positiveSlopes.add(s);
            }
        }

        if (negativeSlopes.size() < N || positiveSlopes.size() < N) {
            System.out.println(-1);
            return;
        }
        
        Collections.sort(valuesAtX1);

        for (long y : valuesAtX1) {
            if (negativeNeeds.size() < negativeSlopes.size()) {
                negativeNeeds.add(new long[]{x1, y});
            } else {
                positiveNeeds.add(new long[]{x1, y});
            }
        }

        long minY = findMinimum(positiveNeeds, positiveSlopes);
        long maxY = findMaximum(negativeNeeds, negativeSlopes);
        
        System.out.println(maxY - minY);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int t = 0; t < T; t++) {
            processTestCase(sc);
        }
        sc.close();
    }
}

