import java.util.*;
import java.io.*;

public class MajorityOpinion {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
        int t = Integer.parseInt(st.nextToken());
        for (int tc = 0; tc < t; tc++) {
            st = new StringTokenizer(reader.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int[] arr = new int[n];
            st = new StringTokenizer(reader.readLine(), " ");
            for (int idx = 0; idx < n; idx++) {
                arr[idx] = Integer.parseInt(st.nextToken());
            }
            if (n == 2) {
                if (arr[0] == arr[1]) {
                    System.out.println(arr[0]);
                } else {
                    System.out.println(-1);
                }
            } else {
                int prev = arr[0];
                boolean isAllSame = true;
                for (int idx = 1; idx < n; idx++) {
                    if (prev != arr[idx]) {
                        isAllSame = false;
                        break;
                    }
                    prev = arr[idx];
                }
                if (isAllSame) {
                    System.out.println(prev);
                } else {
                    HashMap<Integer, Integer> end = new HashMap<Integer, Integer>();
                    for (int idx = 1; idx <= n; idx++) {
                        end.put(idx, 0);
                    }

                    for (int idx = 0; idx < n - 2; idx++) {
                        int a = arr[idx];
                        int b = arr[idx + 1];
                        int c = arr[idx + 2];
                        if (a == c &&  b != c) {
                            end.put(a, end.get(a) + 1);
                        }

                        if (a == b && c != a) {
                            end.put(a, end.get(a) + 1);
                        }

                        if (b == c && a != b) {
                            end.put(b, end.get(b) + 1);
                        }
                    }

                    boolean isNone = true;
                    StringBuilder str = new StringBuilder();
                    for (int idx = 1; idx <= n; idx++) {
                        if (end.get(idx) > 0) {
                            isNone = false;
                            str.append(idx);
                            str.append(" ");
                        }
                    }
                    if (str.length() > 0) {
                        str.deleteCharAt(str.length() - 1);
                        System.out.println(str);
                    }

                    if (isNone) {
                        System.out.println(-1);
                    }
                }
            }
        }
        reader.close();
    }
}

