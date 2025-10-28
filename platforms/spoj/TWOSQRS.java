import java.util.*;
import java.io.*;

public class TWOSQRS {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        int tc = stdin.nextInt();
        HashMap<Long, Long> map = new HashMap<Long, Long>();
        for (int idx = 0; idx < 1000001; idx++) {
            map.put((long) idx, (long) idx * idx);
        }

        for (int t = 0; t < tc; t++) {
            long num = stdin.nextLong();
            long idx = 0;
            long idx2 = (long) Math.sqrt(num);
            while (idx <= idx2) {
                long poss = map.get(idx) + map.get(idx2);
                if (num == poss) {
                    break;
                } 
                if (poss < num) {
                    idx++;
                } else {
                    idx2--;
                }
            }
            if (idx <= idx2) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
        stdin.close();
    }
}
