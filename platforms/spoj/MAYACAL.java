import java.util.*;
import java.io.*;

public class MAYACAL {
    public static String[] tzolkinNames = {"Imix","Ik","Akbal","Kan","Chikchan","Kimi","Manik","Lamat","Muluk","Ok","Chuen","Eb","Ben","Ix","Men","Kib","Kaban","Etznab","Kawak","Ajaw"};
    public static String[] haabNames = {"Pohp","Wo","Sip","Zotz","Sek","Xul","Yaxkin","Mol","Chen","Yax","Sak","Keh","Mak","Kankin","Muan","Pax","Kayab","Kumku","Wayeb"};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int d = Integer.parseInt(br.readLine().trim());
        long aLC = 8L * 144000L;
        long aTzNum = 9;
        long aTzDayIdx = 19;
        long aHaabNum = 3;
        long aHaabMonIdx = 2;
        long aHaabPos = (aHaabNum - 1) + aHaabMonIdx * 20L;
        long tzDay0 = ((aTzDayIdx - aLC) % 20 + 20) % 20;
        long tzNum0 = ((aTzNum - 1 - aLC) % 13 + 13) % 13;
        long haabPos0 = ((aHaabPos - aLC) % 365 + 365) % 365;
        for (int q = 0; q < d; q++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            int tzNum = Integer.parseInt(st.nextToken());
            String tzName = st.nextToken();
            int haabNum = Integer.parseInt(st.nextToken());
            String haabMonName = st.nextToken();
            int tzDayIdx = -1;
            for (int i = 0; i < 20; i++) {
                if (tzolkinNames[i].equals(tzName)) {
                    tzDayIdx = i;
                    break;
                }
            }
            int haabMonIdx = -1;
            for (int i = 0; i < 19; i++) {
                if (haabNames[i].equals(haabMonName)) {
                    haabMonIdx = i;
                    break;
                }
            }

            if (tzDayIdx == -1 || haabMonIdx == -1 || tzNum < 1 || tzNum > 13 || haabNum < 1) {
                System.out.println(0);
                continue;
            }
            int maxDay = (haabMonIdx == 18) ? 5 : 20;
            if (haabNum > maxDay) {
                System.out.println(0);
                continue;
            }

            long tTzDayIdx = tzDayIdx;
            long tTzNum = tzNum - 1;
            long tHaabPos = (haabNum - 1) + haabMonIdx * 20L;
            long r1 = ((tTzDayIdx - tzDay0) % 20 + 20) % 20;
            long r2 = ((tTzNum - tzNum0) % 13 + 13) % 13;
            long r3 = ((tHaabPos - haabPos0) % 365 + 365) % 365;
            long[] eg1b = extgcd(20 % 13, 13);
            long inv20mod13 = ((eg1b[1]) % 13 + 13) % 13;
            long diff1 = ((r2 - r1) % 13 + 13) % 13;
            long t1 = (inv20mod13 * diff1) % 13;
            long lcm1 = lcm(20, 13);
            long sol12 = ((( r1 + 20 * t1) % lcm1) + lcm1) % lcm1;
            long lcm2 = lcm(lcm1, 365L);
            long a2 = lcm1 % 365;
            long[] eg3 = extgcd(a2, 365);
            long g2 = eg3[0];
            long diffForS = ((r3 - sol12 % 365) % 365 + 365) % 365;
            if (diffForS % g2 != 0) {
                System.out.println(0);
                continue;
            }

            long invA2 = ((eg3[1]) % (365 / g2) + (365 / g2)) % (365 / g2);
            long s2 = (invA2 * (diffForS / g2)) % (365 / g2);
            long sol123 = ((( sol12 + lcm1 * s2) % lcm2) + lcm2) % lcm2;
            long baktun8Start = 8L * 144000L;
            long baktun9End = 10L * 144000L - 1L;
            long first = sol123;
            if (first < baktun8Start) {
                long steps = (baktun8Start - first + lcm2 - 1) / lcm2;
                first += steps * lcm2;
            }

            List<long[]> ans = new ArrayList<>();
            long day = first;
            while (day <= baktun9End) {
                long rem = day;
                long b = rem / 144000;
                rem %= 144000;
                long k = rem / 7200;
                rem %= 7200;
                long t = rem / 360;
                rem %= 360;
                long w = rem / 20;
                long i = rem % 20;
                ans.add(new long[]{b, k, t, w, i});
                day += lcm2;
            }

            System.out.println(ans.size());
            for (long[] r : ans) {
                System.out.println(r[0] + "." + r[1] + "." + r[2] + "." + r[3] + "." + r[4]);
            }
        }
    }

    public static long gcd(long a, long b) {
        while (b != 0) {
            long t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    public static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    public static long[] extgcd(long a, long b) {
        if (b == 0) {
            return new long[]{a, 1, 0};
        }
        long[] r = extgcd(b, a % b);
        return new long[]{r[0], r[2], r[1] - (a / b) * r[2]};
    }

}
