import java.io.*;
import java.util.*;
public class MilkSum {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        String[] milkAmountsStr = reader.readLine().split(" ");
        long[] milkAmounts = new long[n];
        for (int i = 0; i < n; i++) {
            milkAmounts[i] = Long.parseLong(milkAmountsStr[i]);
        }

        long[] sortedMilk = milkAmounts.clone();
        Arrays.sort(sortedMilk);
        long[] prefixSums = new long[n + 1];
        long totalWeight = 0;
        
        for (int i = 0; i < n; i++) {
            prefixSums[i + 1] = prefixSums[i] + sortedMilk[i];
            totalWeight += (i + 1) * sortedMilk[i];
        }

        Map<Long, Integer> valSIdx = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            valSIdx.put(sortedMilk[i], i);
        }
        valSIdx.put(Long.MIN_VALUE, -1);

        StringBuilder ans = new StringBuilder();
        int queryCount = Integer.parseInt(reader.readLine());

        while (queryCount-- > 0) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int index = Integer.parseInt(tokenizer.nextToken()) - 1;
            long currVal = milkAmounts[index];
            long newValue = Long.parseLong(tokenizer.nextToken());

            int oldSortedIdx = valSIdx.get(currVal);
            int newSortedIdx = valSIdx.lowerEntry(newValue).getValue() + 1;

            long updatedWeight = totalWeight
                    - (long) (oldSortedIdx + 1) * currVal
                    + (long) (newSortedIdx + (newSortedIdx > oldSortedIdx ? 0 : 1)) * newValue
                    - (prefixSums[newSortedIdx] - prefixSums[oldSortedIdx + (newSortedIdx > oldSortedIdx ? 1 : 0)]);

            ans.append(updatedWeight).append('\n');

            valSIdx.put(currVal, -1);
            milkAmounts[index] = newValue;
            valSIdx.put(newValue, oldSortedIdx);
        }

        System.out.print(ans);
    }
}

