import java.io.*;
import java.util.*;

public class Rectangles {
    public static int eventCount;
    public static int maxXCoord;
    public static int maxYCoord;
    public static int numRects;
    public static Rectangle[] Rects;
    public static Event[] events;

    public static class Event implements Comparable<Event> {
        public int yCoord;
        public boolean isStart;

        public Event(int yCoord, boolean isStart) {
            this.yCoord = yCoord;
            this.isStart = isStart;
        }

        public int compareTo(Event other) {
            if (this.yCoord != other.yCoord) {
                return Integer.compare(this.yCoord, other.yCoord);
            }
            if (this.isStart && !other.isStart) {
                return -1;
            }
            if (!this.isStart && other.isStart) {
                return 1;
            }
            return 0;
        }
    }

    public static long ceil(long a, long b) {
        if (b == 0) {
            return Long.MAX_VALUE; 
        }
        return (a + b - 1) / b;
    }

    public static long floor(long a, long b) {
        if (b == 0) {
            return Long.MIN_VALUE;
        }
        return a / b;
    }

    public static void helper(int x, int y, boolean isStart) {
        if (x == 0) {
            return; 
        }
        long ratio;
        if (isStart) {
            ratio = ceil((long) y * maxXCoord, x);
        } else {
            ratio = floor((long) y * maxXCoord, x);
        }

        if (ratio > maxYCoord) {
            return;
        }

        if (events == null || eventCount >= events.length) {
            events = Arrays.copyOf(events, Math.max(eventCount * 2, 20000));
        }
        events[eventCount++] = new Event((int) ratio, isStart);
    }

    public static Point findCross() {
        eventCount = 0;
        events = new Event[numRects * 2];
        for (int i = 0; i < numRects; i++) {
            Rectangle curr = Rects[i];
            helper(curr.x2, curr.y1, true);
            helper(curr.x1, curr.y2, false);
        }

        if (eventCount > 0) {
            Arrays.sort(events, 0, eventCount);
        }

        int currCross = 0;
        int maxCross = 0;
        int maxY = 0;
        for (int i = 0; i < eventCount; i++) {
            if (events[i].isStart) currCross++;
            else currCross--;

            if (currCross > maxCross) {
                maxCross = currCross;
                maxY = events[i].yCoord;
            }
        }

        return new Point(maxCross, maxXCoord, maxY);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        maxXCoord = R;
        maxYCoord = C;
        numRects = N;
        Rects = new Rectangle[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            Rects[i] = new Rectangle(x1, y1, x2, y2);
        }

        Point best = findCross();
        int tmp = maxXCoord;
        maxXCoord = maxYCoord;
        maxYCoord = tmp;
        for (int i = 0; i < numRects; i++) {
            Rectangle r = Rects[i];
            int tx1 = r.x1, tx2 = r.x2;
            int ty1 = r.y1, ty2 = r.y2;
            r.x1 = ty1; r.y1 = tx1;
            r.x2 = ty2; r.y2 = tx2;
        }

        Point rotated = findCross();
        Point finalAns = best;
        if (rotated.crossings > finalAns.crossings) {
            finalAns = new Point(rotated.crossings, rotated.yCoord, rotated.xCoord);
        }

        System.out.println(finalAns.crossings + " " + finalAns.xCoord + " " + finalAns.yCoord);
        br.close();
    }

    public static class Point {
        public int crossings;
        public int xCoord;
        public int yCoord;
        public Point(int crossings, int xCoord, int yCoord) {
            this.crossings = crossings;
            this.xCoord = xCoord;
            this.yCoord = yCoord;
        }
    }

    public static class Rectangle {
        public int x1, y1, x2, y2;
        public Rectangle(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }
}

