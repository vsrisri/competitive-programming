import java.io.*;
import java.util.*;

public class JustStalling {
    static int N;
    static int[] cows, stalls;
    static long count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cows = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        stalls = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        
        count = countArrangements();
        System.out.println(count);
    }

    static long countArrangements() {
        List<Integer> validStalls = new ArrayList<>();
        for (int cow : cows) {
            validStalls.clear();
            for (int j = 0; j < N; j++) {
                if (cow <= stalls[j]) {
                    validStalls.add(j);
                }
            }
            if (validStalls.isEmpty()) {
                return 0;
            }
        }

        return factorial(N) / factorial(N - validStalls.size());
    }

    static long factorial(int num) {
        long result = 1;
        for (int i = 1; i <= num; i++) {
            result *= i;
        }
        return result;
    }
}

