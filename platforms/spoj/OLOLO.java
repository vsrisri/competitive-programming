import java.util.*;
import java.io.*;

public class OLOLO {
    public static void main(String[] arsg) throws Exception {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        //HashSet<Integer> hs = new HashSet<Integer>();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int pyani = stdin.nextInt();
            ans^= pyani;
            /*
            if (hs.contains(pyani)) {
                hs.remove(pyani);
            } else {
                hs.add(pyani);
            } */
        }
        /*
        for (Integer t : hs) {
            System.out.println(t);
        } */
        System.out.println(ans);
        stdin.close(); 
    }
}
