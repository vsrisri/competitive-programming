import java.util.*;
import java.io.*;

public class ARITH2 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
        int tc = Integer.parseInt(st.nextToken());
        for (int t = 0; t < tc; t++) {
            char op = ' ';
            reader.readLine();
            st = new StringTokenizer(reader.readLine(), " ");
            int ans = Integer.parseInt(st.nextToken());
            while (st.hasMoreTokens()) {
                String s = st.nextToken();
                if (s.equals("=")) {
                    break;
                } else if (s.equals("*") || s.equals("-") || s.equals("+") || s.equals("/")) {
                    op = s.charAt(0);
                } else {
                    int num = Integer.parseInt(s);
                    if (op == '*') {
                        ans *= num;
                    } else if (op == '+') {
                        ans += num;
                    } else if (op == '/') {
                        ans /= num;
                    } else if (op == '-') {
                        ans -= num;
                    }
                }
            }
            System.out.println(ans);
        }
        reader.close();
    }
}

