//USACO 2017 December Contest, Bronze Problem 3. Milk Measurement
import java.util.*;
import java.io.*;

public class MilkMeasurement {
    static class Day implements Comparable<Day> {
        public String name;
        public int date;
        public int change;

        public Day(String name, int date, int change) {
            this.name = name;
            this.date = date;
            this.change = change;
        }

        public int compareTo(Day d) {
            return this.date - d.date;
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(new File ("measurement.in"));
        PrintWriter p = new PrintWriter(new File ("measurement.out"));
        int m = 7;
        int e = 7;
        int b = 7;
        int n = s.nextInt();
        Day[] days = new Day[n];
        s.nextLine();

        for (int idx = 0; idx < n; idx++) {
            String line = s.nextLine();
            int change = line.charAt(line.length() - 1);
            if (line.charAt(line.length() - 2) == '-') {
                change = change * -1;
            }
            int spaceIdx = line.indexOf(' ');
            int date = Integer.parseInt(line.substring(0, spaceIdx));
            days[idx] = new Day(Character.toString(line.charAt(spaceIdx + 1)), date, change);
        }
        Arrays.sort(days);

        String top = "BEM";
        int ans = 0;
        for (int idx = 0; idx < n; idx++) {
            if (days[idx].name.equals("M")) {
                m += days[idx].change;
            } else if (days[idx].name.equals("B")) {
                b +=  days[idx].change;
            } else if (days[idx].name.equals("E")) {
                e +=  days[idx].change;
            }
            String newTop = MilkMeasurement.checkTop(b, e, m, top);
            if (!top.equals(newTop)) {
                ans++;
                top = newTop;
            }
        }
        p.print(ans);
        s.close();
        p.close();
    }

    public static String checkTop(int b, int e, int m, String top) {
        String newTop = top;
    
        if ((b == e) && (e == m)) {
            newTop = "BEM";
        } else if ((b == e) && (e > m)) {
            newTop = "BE";
        } else if ((b == m) && (m > e)) {
            newTop ="BM";
        } else if ((e == m) && (m > b)) {
            newTop ="EM";
        } else if ((b > e) && (e >= m)) {
            newTop = "B";
        } else if ((b > m) && (m >= e)) {
            newTop = "B";
        } else if ((e > b) && (b >= m)) {
            newTop = "E";
        } else if ((e > m) && (m >= b)) {
            newTop = "E";
        } else if ((m > b) && (b >= e)) {
            newTop = "M";
        } else if ((m > e) && (e >= b)) {
            newTop = "M";
        }
        return newTop;
       /* 
        if (top == "M") {
            if (e > m) {
                return "E";
            } else if (b > m) {
                return "B";
            } else {
                return "M";
            }
        } else if (top == "E") {
            if (m > e) {
                return "M";
            } else if (b > e) {
                return "B";
            } else {
                return "E";
            }
        } else if (top == "B") {
            if (m > b) {
                return "M";
            } else if (e > b) {
                return "E";
            } else {
                return "B";
            }
        } else if (top == "BEM") {
            if (m > b) {
                return "M";
            } else if (e > b) {
                return "E";
            } else if (b > m) {
                return "B";
            }
        }
        return "";
        */
    }

}
