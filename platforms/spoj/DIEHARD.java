import java.util.*;
import java.io.*;

public class DIEHARD {
    public static void main(String[] args) throws Exception {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int tc = Integer.parseInt(reader.readLine());
            for (int t = 1; t <= tc; t++) {
                StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
                int H = Integer.parseInt(st.nextToken()) + 3;
                int A = Integer.parseInt(st.nextToken()) + 2;
                int time = 1;

                if (H == 3 && A == 2) {
                    System.out.println(0);
                    continue;
                }

                while (H > 0 && A > 0) {
                    if (H > 5 && A > 10) {
                        H -= 2;
                        A -= 8;
                        time += 2;
                    } else if (H > 20 && A <= 10) {
                        H -= 17;
                        A += 7;
                        time += 2;
                    } else {
                        break;
                    }
                }
                System.out.println(time);
            }
            reader.close();
        } catch (Exception e) {
            return;
        }
    }
}

