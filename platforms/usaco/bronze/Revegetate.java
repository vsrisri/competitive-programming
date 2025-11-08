import java.util.*;
import java.io.*;

public class Revegetate {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("revegetate.in"));
        PrintWriter pw = new PrintWriter(new File("revegetate.out"));
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();
        ArrayList[] graph = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			graph[i] = new ArrayList<Integer>();
        }
		for (int i = 0; i < m; i++) {
			int a = sc.nextInt()-1;
			int b = sc.nextInt()-1;
			graph[a].add(b);
			graph[b].add(a);
		}

        int[] arr = new int[n];
		Revegetate.helper(arr, graph, 0);

        for (int i = 0; i < n; i++) {
			pw.print(arr[i]);
        }
		pw.println();
		pw.close();
		sc.close();	
    }

    public static boolean helper(int[] arr, ArrayList[] graph, int k) {
        if (k == arr.length) {
            return true;
        }
		for (int i = 1; i <= 4; i++) {
			boolean ok = true;
			for (Integer x: (ArrayList<Integer>) graph[k]) {
				if (arr[x] == i) {
					ok = false;
					break;
				}
			}
			if (!ok) {
                continue;
            }
			arr[k] = i;
			boolean tmp = helper(arr, graph, k+1);
			if (tmp) {
                return true;
            }
			arr[k] = 0;
		}
		return false;
    }
}
