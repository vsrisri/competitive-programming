import java.util.*;
import java.io.*;

public class MilkExchange {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        String str = reader.readLine();
        st = new StringTokenizer(reader.readLine(), " ");
        int[] a = new int[n];
        int total = 0;
        for (int idx = 0; idx < n; idx++) {
            a[idx] = Integer.parseInt(st.nextToken());
            total += a[idx];
        }

        int[] inDeg = new int[n];
        for (int idx = 0; idx < str.length(); idx++) {
            char dir = str.charAt(idx);
            int num = 0;
            if (dir == 'L') {
                if (idx == 0) {
                    num = n - 1;
                } else {
                    num = idx - 1;
                }
            } else {
                if (idx == n - 1) {
                    num = 0;
                } else {
                    num = idx + 1;
                }
            }
            inDeg[num]++;
        }

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            if (inDeg[i] == 0) {
                map.put(i, 0);
            }
        }

        for (Integer i : map.keySet()) {
            int curr = i;
            int milk = 0;
            while (inDeg[curr] < 2) {
                milk += a[curr];
                if (str.charAt(curr) == 'L') {
                    if (curr == 0) {
                        curr = n - 1;
                    } else {
                        curr--;
                    }
                } else {
                    if (curr == n - 1) {
                        curr = 0;
                    } else {
                        curr++;
                    }
                }
            }
            map.put(i, milk);
        }

        int milkLost = 0;
        for (int num : map.values()) {
            milkLost += Math.min(m, num);
        }
        System.out.println(total - milkLost);
        reader.close();
    }
}

