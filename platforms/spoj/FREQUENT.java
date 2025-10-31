// Incomplete 
import java.io.*;
import java.util.*;

public class FREQUENT {
    public static int[] start, end, count, blockFreq, seg;
    public static int[] a, blockId;
    public static int size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String line;

        while (!(line = br.readLine()).equals("0")) {
            st = new StringTokenizer(line);
            int n = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            a = new int[n + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }
            start = new int[n + 1];
            end = new int[n + 1];
            count = new int[n + 1];
            blockId = new int[n + 1];
            int blocks = 0;
            size = 1;
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= n; ) {
                int j = i;
                while (j <= n && a[j] == a[i]) j++;
                blocks++;
                for (int k = i; k < j; k++) {
                    blockId[k] = blocks;
                    start[blocks] = i;
                    end[blocks] = j - 1;
                    count[blocks] = j - i;
                }
                i = j;
            }

            blockFreq = new int[blocks + 1];
            for (int i = 1; i <= blocks; i++){
                blockFreq[i] = count[i];
            }

            while (size < blocks) {
                size <<= 1;
            }
            seg = new int[size * 2];
            for (int i = 1; i <= blocks; i++) {
                seg[size + i - 1] = blockFreq[i];
            }
            for (int i = size - 1; i > 0; i--) {
                seg[i] = Math.max(seg[i << 1], seg[i << 1 | 1]);
            }

            for (int t = 0; t < q; t++) {
                st = new StringTokenizer(br.readLine());
                int l = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                int bL = blockId[l];
                int bR = blockId[r];

                if (bL == bR) {
                    sb.append(r - l + 1).append('\n');
                } else {
                    int leftPart = end[bL] - l + 1;
                    int rightPart = r - start[bR] + 1;
                    int midPart = 0;
                    if (bL + 1 <= bR - 1) {
                        midPart = querySeg(bL + 1, bR - 1);
                    }
                    sb.append(Math.max(Math.max(leftPart, rightPart), midPart)).append('\n');
                }
            }
            System.out.print(sb);
        }
        br.close();
    }

    public static int querySeg(int l, int r) {
        int ans = 0;
        l += size - 1;
        r += size - 1;
        while (l <= r) {
            if ((l & 1) == 1) ans = Math.max(ans, seg[l++]);
            if ((r & 1) == 0) ans = Math.max(ans, seg[r--]);
            l >>= 1;
            r >>= 1;
        }
        return ans;
    }

}

