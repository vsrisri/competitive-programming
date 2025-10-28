import java.io.*;
import java.util.*;

public class EverythingArray {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = br.readLine().split(" ");
        int N = Integer.parseInt(tokens[0]);
        int M = Integer.parseInt(tokens[1]);
        int bestLength = N + 1;
        int curr = -1;
        for (int i = 3; i <= N - 3; i++) {
            int totalLength = (int) Math.ceil((2.0 * i * i + N) / (2.0 * i + 1));
            if (totalLength < bestLength) {
                bestLength = totalLength;
                curr = i;
            }
        }

        int jump = 2 * curr + 1;
        List<Integer> ansArr = new ArrayList<>();
        for (int i = 1; i <= curr; i++) {
            ansArr.add(i);
        }

        for (int val = jump; val <= N; val += jump) {
            ansArr.add(val);
        }

        int last = ansArr.get(ansArr.size() - 1);
        if (last + curr < N) {
            ansArr.add(last + curr);
        }

        if (ansArr.size() > M) {
            System.out.println(-1);
            return;
        }

        System.out.println(ansArr.size());
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < ansArr.size(); i++) {
            if (i > 0) {
                output.append(' ');
            }
            output.append(ansArr.get(i));
        }
        System.out.println(output);
        br.close();
    }
}

