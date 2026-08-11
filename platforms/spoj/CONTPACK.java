import java.io.*;
import java.util.*;

public class CONTPACK {
    public static void main(String[] args) throws IOException {
        StreamTokenizer in = new StreamTokenizer(new BufferedInputStream(System.in));
        StringBuilder sb = new StringBuilder();
        in.nextToken();
        int t = (int) in.nval;
        for (int tc = 0; tc < t; tc++) {
            in.nextToken();
            int n = (int) in.nval;
            int maxSize = 1000;
            int[] sizeArr = new int[n];
            int[] valArr = new int[n];
            int[] count = new int[maxSize + 2];
            for (int i = 0; i < n; i++) {
                in.nextToken();
                int s = (int) in.nval;
                in.nextToken();
                int v = (int) in.nval;
                sizeArr[i] = s;
                valArr[i] = v;
                count[s]++;
            }
            int[] start = new int[maxSize + 3];
            for (int i = 0; i <= maxSize; i++) {
                start[i + 1] = start[i] + count[i];
            }
            int[] bucket = new int[n];
            int[] pos = new int[maxSize + 2];
            for (int i = 0; i <= maxSize; i++) {
                pos[i] = start[i];
            }
            for (int i = 0; i < n; i++) {
                int s = sizeArr[i];
                bucket[pos[s]] = valArr[i];
                pos[s]++;
            }
            for (int i = 0; i <= maxSize; i++) {
                Arrays.sort(bucket, start[i], start[i + 1]);
            }
            in.nextToken();
            int q = (int) in.nval;
            int[] need = new int[maxSize + 2];
            int maxContSize = 0;
            for (int i = 0; i < q; i++) {
                in.nextToken();
                int s = (int) in.nval;
                in.nextToken();
                int c = (int) in.nval;
                need[s] += c;
                if (c > 0 && s > maxContSize) {
                    maxContSize = s;
                }
            }
            long[] leftover = new long[0];
            long ans = 0;
            boolean poss = true;
            for (int lvl = 0; lvl <= maxContSize; lvl++) {
                int bs = start[lvl];
                int be = start[lvl + 1];
                int boxCount = be - bs;
                int tot = boxCount + leftover.length;
                long[] items = new long[tot];
                int a = bs;
                int b = 0;
                int idx = 0;
                while (a < be && b < leftover.length) {
                    if (bucket[a] <= leftover[b]) {
                        items[idx++] = bucket[a++];
                    } else {
                        items[idx++] = leftover[b++];
                    }
                }
                while (a < be) {
                    items[idx++] = bucket[a++];
                }
                while (b < leftover.length) {
                    items[idx++] = leftover[b++];
                }
                int needCurr = need[lvl];
                if (tot < needCurr) {
                    poss = false;
                    break;
                }
                for (int k = 0; k < needCurr; k++) {
                    ans += items[k];
                }
                int restLen = tot - needCurr;
                int newremLen = restLen / 2;
                long[] newrem = new long[newremLen];
                for (int j = 0; j < newremLen; j++) {
                    newrem[j] = items[needCurr + 2 * j] + items[needCurr + 2 * j + 1];
                }
                leftover = newrem;
            }
            if (poss) {
                sb.append(ans).append('\n');
            } else {
                sb.append("No").append('\n');
            }
        }
        System.out.print(sb);
    }
}
