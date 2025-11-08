import java.util.*;
import java.io.*;

public class HungryCow{
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        long t = Long.parseLong(st.nextToken());
        ArrayList<long[]> arr = new ArrayList<long[]>();
        for (int idx = 0; idx < n; idx++) {
            st = new StringTokenizer(reader.readLine(), " ");
            long[] arr2 = new long[2];
            arr2[0] = Long.parseLong(st.nextToken());
            arr2[1] = Long.parseLong(st.nextToken());
            arr.add(arr2);
        }
        long[] temp = new long[2];
        temp[0] = t + 1;
        temp[1] = 0;
        arr.add(temp);

        long remainder = 0;
        long total = 0;
        long currDay = 0;

        for (long[] arr2 : arr) {
            long day = arr2[0];
            long num = arr2[1];
            total+= num;
            remainder -= day - currDay;
            currDay = day;
            remainder = Math.max(remainder, 0) + num;
        }

        System.out.println(total - remainder);
    }
}
