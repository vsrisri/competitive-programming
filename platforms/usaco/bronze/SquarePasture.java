// USACO 2016 December Contest, Bronze Problem 1. Square Pasture
import java.util.*;
import java.io.*;

public class SquarePasture {
    public static void main(String[] args) throws Exception {
        File in = new File("square.in");
        PrintWriter p = new PrintWriter("square.out");
        Scanner s = new Scanner(in);
        int x1 = s.nextInt();
        int y1 = s.nextInt();
        int x2 = s.nextInt();
        int y2 = s.nextInt();
        s.nextLine();
        int x3 = s.nextInt();
        int y3 = s.nextInt();
        int x4 = s.nextInt();
        int y4 = s.nextInt();

        int minx = Math.max(x2, x4);
        int maxx = Math.max(x1, x3);
        int miny = Math.max(y2, y4);
        int maxy = Math.max(x1, x3);
        int y = maxy - miny;
        int x = maxx - minx;
        double area = Math.max(Math.pow(y, 2), Math.pow(x, 2));
        p.println(area);
        p.close();
    }
}

