import java.io.*;
import java.util.*;

public class Cowlendar {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int monthCount = Integer.parseInt(input.readLine());
        HashSet<Long> dur = new HashSet<Long>();
        StringTokenizer tokens = new StringTokenizer(input.readLine());
        long ans = Long.MAX_VALUE;

        for (int i = 0; i < monthCount; i++) {
            long duration = Long.parseLong(tokens.nextToken());
            dur.add(duration);
            ans = Math.min(ans, duration / 4L);
        }

        if (dur.size() <= 3) {
            System.out.println(findSum(ans));
        } else {
            System.out.println(determineValidWeeks(dur, ans));
        }
    }


    public static long determineValidWeeks(Set<Long> dur, long ans) {
        Set<Long> possibleDivisors = computeDivisors(dur);

        long validTotal = 0;
        for (long weekLength : possibleDivisors) {
            if (weekLength <= ans && validateWeek(dur, weekLength)) {
                validTotal += weekLength;
            }
        }
        return validTotal;
    }

    public static HashSet<Long> computeDivisors(Set<Long> dur) {
        HashSet<Long> divisors = new HashSet<Long>();
        Long[] durationArray = dur.toArray(new Long[0]);

        for (int i = 0; i < durationArray.length; i++) {
            for (int j = 0; j < i; j++) {
                long difference = Math.abs(durationArray[i] - durationArray[j]);
                for (long div = 1; div * div <= difference; div++) {
                    if (difference % div == 0) {
                        divisors.add(div);
                        divisors.add(difference / div);
                    }
                }
            }
        }

        return divisors;
    }

    public static long findSum(long a) {
        return (a * (a + 1)) / 2;
    }

    public static boolean validateWeek(Set<Long> dur, long weekLength) {
        HashSet<Long> rem = new HashSet<Long>();
        for (long duration : dur) {
            rem.add(duration % weekLength);
        }
        return rem.size() <= 3;
    }
}

