import java.io.*;
import java.util.*;

public class CycleCorres {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");
        int totalElements = Integer.parseInt(input[0]);
        int cycleSize = Integer.parseInt(input[1]);
        int[] c1 = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] c2 = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        
        Set<Integer> uEl = new HashSet<>();
        for (int i = 1; i <= totalElements; i++) {
            uEl.add(i);
        }
        for (int item : c1) {
            uEl.remove(item);
        }
        for (int item : c2) {
            uEl.remove(item);
        }
        
        int bestAlignment = Math.max(helper(c1, c2), helper(c1, reverse(secondCycle)));
        System.out.println(uEl.size() + bestAlignment);
    }

    public static int helper(int[] c1, int[] c2) {
        Map<Integer, Integer> elementToIndex = new HashMap<>();
        for (int i = 0; i < c1.length; i++) elementToIndex.put(c1[i], i);

        int[] matchCounts = new int[c1.length];
        for (int i = 0; i < c2.length; i++) {
            if (elementToIndex.containsKey(c2[i])) {
                int posDiff = (i - elementToIndex.get(c2[i]) + c1.length) % c1.length;
                matchCounts[posDiff]++;
            }
        }
        
        return Arrays.stream(matchCounts).max().orElse(0);
    }

    public static int[] reverse(int[] arr) {
        int[] reversedArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            reversedArr[i] = arr[arr.length - 1 - i];
        }
        return reversedArr;
    }
}

