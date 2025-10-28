import java.io.*;
import java.util.*;

public class DilucAndKaeya {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(reader.readLine());
        StringBuilder result = new StringBuilder();
        
        while (t-- > 0) {
            int n = Integer.parseInt(reader.readLine());
            String s = reader.readLine();
            int[] countD = new int[n + 1];
            int[] countK = new int[n + 1];
            HashMap<String, Integer> ratioMap = new HashMap<>();
            ratioMap.put("0:1", 0);
            ratioMap.put("1:0", 0);
            int[] answer = new int[n];
            
            for (int i = 1; i <= n; i++) {
                countD[i] = countD[i - 1] + (s.charAt(i - 1) == 'D' ? 1 : 0);
                countK[i] = countK[i - 1] + (s.charAt(i - 1) == 'K' ? 1 : 0);
                int a = countD[i], b = countK[i];
                
                if (a == 0) {
                    ratioMap.put("0:1", ratioMap.get("0:1") + 1);
                    answer[i - 1] = ratioMap.get("0:1");
                } else if (b == 0) {
                    ratioMap.put("1:0", ratioMap.get("1:0") + 1);
                    answer[i - 1] = ratioMap.get("1:0");
                } else {
                    int gcd = gcd(a, b);
                    String key = (a / gcd) + ":" + (b / gcd);
                    ratioMap.put(key, ratioMap.getOrDefault(key, 0) + 1);
                    answer[i - 1] = ratioMap.get(key);
                }
            }
            for (int i = 0; i < n; i++) {
                result.append(answer[i]).append(" ");
            }
            result.append("\n");
        }
        System.out.print(result);
    }

    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}

