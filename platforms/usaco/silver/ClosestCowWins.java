import java.io.*;
import java.util.*;

public class ClosestCowWins {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        
        int numPatches = Integer.parseInt(tokenizer.nextToken());
        int numCows = Integer.parseInt(tokenizer.nextToken());
        int maxCowsToPlace = Integer.parseInt(tokenizer.nextToken());

        Patch[] grassyPatches = readGrassyPatches(reader, numPatches);
        int[] enemyCows = readCowposs(reader, numCows);

        Arrays.sort(grassyPatches);
        Arrays.sort(enemyCows);

        long[] patchTasSum = computePatchTasSum(grassyPatches);
        Map<Integer, Integer> cowposMap = createCowposMap(enemyCows);

        long[][] tasValue = computeTasValue(grassyPatches, enemyCows, patchTasSum, cowposMap);
        long[] finalTasValues = getFinTVals(tasValue, numCows);
        
        long ans = findMaxT(finalTasValues, maxCowsToPlace);
        System.out.println(ans);
    }

    public static Patch[] readGrassyPatches(BufferedReader reader, int numPatches) throws IOException {
        Patch[] patches = new Patch[numPatches];
        for (int i = 0; i < numPatches; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int pos = Integer.parseInt(tokenizer.nextToken());
            long tas = Long.parseLong(tokenizer.nextToken());
            patches[i] = new Patch(pos, tas);
        }
        return patches;
    }

    public static int[] readCowposs(BufferedReader reader, int numCows) throws IOException {
        int[] cows = new int[numCows];
        for (int i = 0; i < numCows; i++) {
            cows[i] = Integer.parseInt(reader.readLine());
        }
        return cows;
    }

    public static long[] computePatchTasSum(Patch[] patches) {
        long[] sum = new long[patches.length + 1];
        for (int i = 0; i < patches.length; i++) {
            sum[i + 1] = sum[i] + patches[i].tas;
        }
        return sum;
    }

    public static Map<Integer, Integer> createCowposMap(int[] cows) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < cows.length; i++) {
            map.put(cows[i], i);
        }
        return map;
    }

    public static long[][] computeTasValue(Patch[] patches, int[] cows, long[] patchTasSum, Map<Integer, Integer> cowposMap) {
        long[][] tasValue = new long[3][cows.length + 1];

        for (int i = 0; i < patches.length; i++) {
            Map.Entry<Integer, Integer> nextCowEntry = cowposMap.ceilingEntry(patches[i].pos);
            int nextCow = nextCowEntry == null ? cows.length : nextCowEntry.getValue();

            tasValue[2][nextCow] += patches[i].tas;
            if (nextCow == 0 || nextCow == cows.length) {
                tasValue[1][nextCow] += patches[i].tas;
            } else {
                int johnsCowpos = Math.min(cows[nextCow], (2 * patches[i].pos) - cows[nextCow - 1]);
                int farthestPatch = getFarthestPatch(patches, johnsCowpos);
                tasValue[1][nextCow] = Math.max(tasValue[1][nextCow], patchTasSum[farthestPatch + 1] - patchTasSum[i]);
            }
        }
        return tasValue;
    }

    public static int getFarthestPatch(Patch[] patches, int pos) {
        int left = 0, right = patches.length - 1;
        int ans = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (patches[mid].pos <= pos) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    public static long[] getFinTVals(long[][] tasValue, int numCows) {
        long[] finalValues = new long[2 * (numCows + 1)];

        for (int i = 0; i <= numCows; i++) {
            finalValues[2 * i] = tasValue[1][i];
            finalValues[(2 * i) + 1] = tasValue[2][i] - tasValue[1][i];
        }
        return finalValues;
    }

    public static long findMaxT(long[] values, int maxCowsToPlace) {
        Arrays.sort(values);
        long totalTas = 0;
        for (int i = Math.max(0, values.length - maxCowsToPlace); i < values.length; i++) {
            totalTas += values[i];
        }
        return totalTas;
    }

    static class Patch implements Comparable<Patch> {
        int pos;
        long tas;

        Patch(int pos, long tas) {
            this.pos = pos;
            this.tas = tas;
        }

        public int compareTo(Patch other) {
            return Integer.compare(this.pos, other.pos);
        }
    }
}

