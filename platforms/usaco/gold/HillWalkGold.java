import java.io.*;
import java.util.*;

public class HillWalkGold {
    static int currX = 0;
    public static class Hill {
        int id, x1, y1, x2, y2;

        Hill(int id, int x1, int y1, int x2, int y2) {
            this.id = id;
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        double getY(int x) {
            return y1 + (double) (y2 - y1) * (x - x1) / (x2 - x1);
        }
    }

    public static class Event implements Comparable<Event> {
        int x, type;
        Hill hill;

        Event(int x, int type, Hill hill) {
            this.x = x;
            this.type = type;
            this.hill = hill;
        }

        public int compareTo(Event o) {
            if (x != o.x) {
                return Integer.compare(x, o.x);
            }
            return Integer.compare(type, o.type);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("hillwalk.in"));
        PrintWriter pw = new PrintWriter("hillwalk.out");
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        Hill[] hills = new Hill[n];
        List<Event> events = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            Hill h = new Hill(i, x1, y1, x2, y2);
            hills[i] = h;
            events.add(new Event(x1, 0, h));
            events.add(new Event(x2, 1, h));
        }

        events.sort(null);

        TreeSet<Hill> set = new TreeSet<>((a, b) -> {
            double ya = a.getY(currX);
            double yb = b.getY(currX);
            if (ya != yb) {
                return Double.compare(ya, yb);
            }
            return Integer.compare(a.id, b.id);
        });

        Set<Integer> visited = new HashSet<>();
        Hill curr = null;
        for (Hill h : hills) {
            if (h.x1 == 0 && h.y1 == 0) {
                curr = h;
                break;
            }
        }

        int idx = 0;
        while (curr != null) {
            visited.add(curr.id);
            while (idx < events.size() && events.get(idx).x <= curr.x2) {
                currX = events.get(idx).x;
                Event e = events.get(idx);
                if (e.type == 0) {
                    set.add(e.hill);
                } else {
                    set.remove(e.hill);
                }
                idx++;
            }
            currX = curr.x2;
            set.remove(curr);
            Hill next = null;
            Hill probe = new Hill(-1, curr.x2, curr.y2, curr.x2 + 1, curr.y2 + 1);
            SortedSet<Hill> head = set.headSet(probe);
            if (!head.isEmpty()) {
                next = ((TreeSet<Hill>) head).last();
            }
            curr = next;
        }

        pw.println(visited.size());
        pw.close();
    }

}

