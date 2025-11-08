import java.util.*;
import java.io.*;

public class P2 {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
        int n1 = Integer.parseInt(st.nextToken());
        int m1 = Integer.parseInt(st.nextToken());
        Cow[] cows = new Cow[n1];
        AC[] acs = new AC[m1];

        for (int idx = 0; idx < n1; idx++) {
            st = new StringTokenizer(reader.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            cows[idx] = new Cow(s, t, c);
        }

        for (int idx = 0; idx < m1; idx++) {
            st = new StringTokenizer(reader.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            acs[idx] = new AC(a, b, p, m);
        }

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < (1 << m1); i++) {
            int curr = 0;
            int[] stalls = new int[100];
            boolean b = true;
            for (int bit = 0; bit < m1; bit++) {
                if ((i & (1 << bit)) > 0) {
                    curr+= acs[bit].m;
                    for (int idx = acs[bit].a; idx <= acs[bit].b; idx++) {
                        stalls[idx]+= acs[bit].p;
                    }
                }
            }
            
            for (int idx = 0; idx < n1; idx++) {
                for (int idx2 = cows[idx].s; idx2 <= cows[idx].t; idx2++) {
                    if (stalls[idx2] < cows[idx].c) {
                        b = false;
                        break;
                    }
                }
            }
            if (b) {
                ans = Math.min(ans, curr);
            }
        }
        System.out.println(ans);
        reader.close();
    }

    public static class Cow {
        public int s;
        public int t;
        public int c;

        Cow (int s, int t, int c) {
            this.s = s; 
            this.t = t;
            this.c = c;
        }
    }

    public static class AC {
        public int a;
        public int b;
        public int p;
        public int m;

        AC (int a, int b, int p, int m) {
            this.a = a; 
            this.b = b;
            this.p = p;
            this.m = m;
        }
    }
} 
