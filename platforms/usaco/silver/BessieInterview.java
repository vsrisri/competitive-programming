import java.io.*;
import java.util.*;

public class BessieInterview {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);
        int[] iTimeArr = new int[K];
        String[] timeStrings = reader.readLine().split(" ");
        
        for (int i = 0; i < K; i++) {
            iTimeArr[i] = Integer.parseInt(timeStrings[i]);
        }

        helper(N, K, iTimeArr);
    }

    public static void helper(int N, int K, int[] iTimeArr) {
        PriorityQueue<Integer> endTimes = new PriorityQueue<>();
        Map<Integer, List<Integer>> endToStartMap = new HashMap<>();
        
        for (int i = 0; i < K; i++) {
            endTimes.add(0);
        }

        for (int time : iTimeArr) {
            int start = endTimes.poll();
            int end = start + time;
            endToStartMap.putIfAbsent(end, new ArrayList<>());
            endToStartMap.get(end).add(start);
            endTimes.add(end);
        }

        List<Integer> sequence = new ArrayList<>();
        sequence.add(endTimes.poll());
        Set<Integer> visited = new HashSet<>();

        for (int end : sequence) {
            if (!visited.contains(end)) {
                visited.add(end);
                if (endToStartMap.containsKey(end)) {
                    sequence.addAll(endToStartMap.get(end));
                }
            }
        }

        System.out.println(sequence.get(0));

        StringBuilder ans = new StringBuilder();
        for (int time : iTimeArr) {
            ans.append(visited.contains(time) ? "1" : "0");
        }

        System.out.println(ans.toString());
    }
}

