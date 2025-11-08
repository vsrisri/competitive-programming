// USACO 2018 February Contest, Bronze Problem 1. Teleportation
import java.util.*;
import java.io.*;

public class Teleportation {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("teleport.in"));
        PrintWriter p = new PrintWriter(new File("teleport.out"));
        int a = sc.nextInt();
        int b = sc.nextInt();
        int ans = Math.abs(a - b);
        int x = sc.nextInt();
        int y = sc.nextInt();
        ans = Math.min(ans, Math.abs(a - x) + Math.abs(y - b));
        ans = Math.min(ans, Math.abs(a - y) + Math.abs(x - b));
        p.print(ans);
        p.close();
        sc.close();
    }
    
}
