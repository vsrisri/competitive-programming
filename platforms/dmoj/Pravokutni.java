import java.io.*;
import java.util.*;

public class Pravokutni {
    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static class Vector {
        int x, y;

        Vector(int x, int y) {
            int g = gcd(Math.abs(x), Math.abs(y));
            x /= g;
            y /= g;
            if (y < 0 || (y == 0 && x < 0)) {
                x = -x;
                y = -y;
            }

            this.x = x;
            this.y = y;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Vector)) {
                return false;
            }
            Vector v = (Vector) o;
            return x == v.x && y == v.y;
        }

        public int hashCode() {
            return 31 * x + y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[][] points = new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            points[i][0] = Integer.parseInt(st.nextToken());
            points[i][1] = Integer.parseInt(st.nextToken());
        }

        long ans = 0;
        for (int idx = 0; idx < n; idx++) {
            Map<Vector, Integer> dirMap = new HashMap<>();
            for (int idx2 = 0; idx2 < n; idx2++) {
                if (idx == idx2) {
                    continue;
                }

                int x = points[idx2][0] - points[idx][0];
                int y = points[idx2][1] - points[idx][1];
                Vector vec = new Vector(x, y);
                dirMap.put(vec, dirMap.getOrDefault(vec, 0) + 1);
            }

            for (Vector vec : dirMap.keySet()) {
                Vector perp = new Vector(-vec.y, vec.x);
                int fc1 = dirMap.get(vec);
                int fc2 = dirMap.getOrDefault(perp, 0);
                ans += (long) fc1 * fc2;
            }
        }

        System.out.println(ans / 2);
        reader.close();
    }
}

