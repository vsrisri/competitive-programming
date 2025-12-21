import java.io.*;
import java.util.*;

public class PhotoOp {
    public static final int max = (int) 2e6;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int numLocations = Integer.parseInt(tokenizer.nextToken());
        int numTimes = Integer.parseInt(tokenizer.nextToken());
        tokenizer = new StringTokenizer(reader.readLine());
        int startX = Integer.parseInt(tokenizer.nextToken());
        int startY = Integer.parseInt(tokenizer.nextToken());
        List<Integer> times = new ArrayList<>();
        List<Integer> xCoords = new ArrayList<>();
        List<Integer> yCoords = new ArrayList<>();
        for (int i = 0; i < numLocations; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            times.add(Integer.parseInt(tokenizer.nextToken()));
            xCoords.add(Integer.parseInt(tokenizer.nextToken()));
            yCoords.add(Integer.parseInt(tokenizer.nextToken()));
        }

        int[] results = new int[numTimes];
        Arrays.fill(results, startX + startY);
        xCoords.add(startX);

        for (int currentX : xCoords) {
            Range yRange = new Range(0, max);
            for (int time = 0; time < numTimes; time++) {
                updateYRange(currentX, xCoords, yCoords, times, time, yRange);
                if (yRange.lower <= yRange.upper) {
                    results[time] = Math.min(results[time], calculateCost(currentX, yRange, startX, startY));
                } else {
                    break;
                }
            }
        }

        for (int time = 0; time < numTimes; time++) {
            System.out.println(results[time]);
        }
    }

    public static class Range {
        int lower, upper;

        Range(int lower, int upper) {
            this.lower = lower;
            this.upper = upper;
        }
    }

    public static int computeDistance(long a, long b) {
        long squaredSum = a * a + b * b;
        long root = (long) Math.sqrt(squaredSum) - 1;
        while ((root + 1) * (root + 1) <= squaredSum) {
            root++;
        }
        return (int) root;
    }

    public static int fixValue(int lower, int upper, int value) {
        if (value < lower) {
            return lower;
        }
        if (value > upper) {
            return upper;
        }
        return value;
    }

    public static int calculateCost(int currentX, Range yLimits, int X, int Y) {
        int fixedY = fixValue(yLimits.lower, yLimits.upper, Y);
        return Math.abs(currentX - X) + Math.abs(fixedY - Y) + computeDistance(currentX, fixedY);
    }

    public static void updateYRange(int currentX, List<Integer> xCoords, List<Integer> yCoords, List<Integer> times, int time, Range yRange) {
        int idx = 0;
        while (idx < times.size() && times.get(idx) <= time) {
            int tmpX = xCoords.get(idx);
            int tmpY = yCoords.get(idx);
            if (tmpX < currentX) {
                yRange.lower = Math.max(yRange.lower, tmpY);
            } else if (tmpX > currentX) {
                yRange.upper = Math.min(yRange.upper, tmpY);
            }
            idx++;
        }
    }
}

