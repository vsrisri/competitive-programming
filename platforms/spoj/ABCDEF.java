import java.io.*;
import java.util.*;

public class ABCDEF {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        Set<Integer> S = new HashSet<>();
        
        for (int i = 0; i < N; i++) {
            S.add(Integer.parseInt(br.readLine()));
        }
        
        Map<Integer, Integer> left = new HashMap<>();
        int ans = 0;
        for (int a : S) {
            for (int b : S) {
                for (int c : S) {
                    int left = a * b + c;
                    if (left != 0) {
                        left.put(left, left.getOrDefault(left, 0) + 1);
                    }
                }
            }
        }
        
        for (int d : S) {
            if (d == 0) {
                continue;
            }
            for (int e : S) {
                for (int f : S) {
                    int right = e + f;
                    if (left.containsKey(d * right)) {
                        ans += left.get(d * right);
                    }
                }
            }
        }

        System.out.println(ans);
        br.close();
    }
}

