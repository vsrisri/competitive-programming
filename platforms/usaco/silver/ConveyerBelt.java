import java.util.*;

public class ConveyerBelt {
    public static final int dirs = 4;
    public static final int[] rowc = {-1, 0, 1, 0};
    public static final int[] colc = {0, 1, 0, -1};
    public static int[][] positionIndex = new int[1000 + 2][1000 + 2];
    public static int[] rowQuery = new int[200000];
    public static int[] colQuery = new int[200000];
    public static int[] queryResults = new int[200000];
    public static int totalConnections;
    public static boolean[] visited = new boolean[1000 * 1000 + 1];
    public static boolean[][] isQueryCell = new boolean[1000 + 1][1000 + 1];
    public static char[] queryDirection = new char[200000];
    public static List<Integer>[] graph = new ArrayList[1000 * 1000 + 1];
    public static Queue<Integer> bfsQueue = new LinkedList<>();
    public static void addConnection(int start, int end) {
        if (visited[end]) {
            if (!visited[start]) {
                bfsQueue.offer(start);
                visited[start] = true;
                totalConnections--;
                while (!bfsQueue.isEmpty()) {
                    int current = bfsQueue.poll();
                    for (int neighbor : graph[current]) {
                        if (!visited[neighbor]) {
                            visited[neighbor] = true;
                            totalConnections--;
                            bfsQueue.offer(neighbor);
                        }
                    }
                    graph[current].clear();
                }
            }
        } else {
            graph[end].add(start);
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        
        int indexCounter = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                positionIndex[i][j] = ++indexCounter;
            }
        }
        
        totalConnections = (int) Math.pow(n, 2);
        visited[0] = true;
        
        for (int i = 0; i < q; i++) {
            rowQuery[i] = sc.nextInt();
            colQuery[i] = sc.nextInt();
            queryDirection[i] = sc.next().charAt(0);
            
            for (int d = 0; d < dirs; d++) {
                if (queryDirection[i] == "URDL".charAt(d)) {
                    addConnection(positionIndex[rowQuery[i]][colQuery[i]], positionIndex[rowQuery[i] + rowc[d]][colQuery[i] + colc[d]]);
                }
            }
            isQueryCell[rowQuery[i]][colQuery[i]] = true;
        }
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (!isQueryCell[i][j]) {
                    for (int dir = 0; dir < dirs; dir++) {
                        addConnection(positionIndex[i][j], positionIndex[i + rowc[dir]][j + colc[dir]]);
                    }
                }
            }
        }
        
        for (int i = q - 1; i >= 0; i--) {
            queryResults[i] = totalConnections;
            for (int dir = 0; dir < dirs; dir++) {
                if (queryDirection[i] != "URDL".charAt(dir)) {
                    addConnection(positionIndex[rowQuery[i]][colQuery[i]], positionIndex[rowQuery[i] + rowc[dir]][colQuery[i] + colc[dir]]);
                }
            }
        }
        
        for (int i = 0; i < q; i++) {
            System.out.println(queryResults[i]);
        }
    }
}

