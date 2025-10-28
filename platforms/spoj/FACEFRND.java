import java.util.*;
import java.io.*;

public class FACEFRND {
    public static void main(String[] args) throws Exception {
        Scanner stdin = new Scanner(System.in);
        int n = stdin.nextInt();
        int[] ids = new int[n];
        HashMap<Integer, Boolean> hm = new HashMap<Integer, Boolean>();
        for (int idx = 0; idx < n; idx++) {
            ids[idx] = stdin.nextInt();
            int num = stdin.nextInt();
            for (int idx2 = 1; idx2 <= num; idx2++) {
                hm.put(stdin.nextInt(), true);
            }
        }

        for (int idx = 0; idx < n; idx++) {
            if (hm.containsKey(ids[idx])) {
                hm.put(ids[idx], false);
            }
        }
        int ans = 0;
        Set<Integer> keys = hm.keySet();
        Iterator<Integer> i = keys.iterator();
        for (int idx = 0; idx < hm.size(); idx++) {
            if (hm.get(i.next()) == true) {
                ans++;
            }
        }
        System.out.println(ans);
        stdin.close();
    }
}

