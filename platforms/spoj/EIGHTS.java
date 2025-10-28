import java.util.*;
import java.io.*;

public class EIGHTS {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        int t = stdin.nextInt();
        long[] arr = new long[t];
        for (int i = 0; i < t; i++) {
            arr[i] = stdin.nextLong();
        }
        for (int j = 0; j < t; j++) {
         long k = (arr[j] - 1) * 250 + 192;
         System.out.println(k);
       }
    }   
}
