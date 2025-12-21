import java.util.*;
import java.io.*;

public class FencedIn {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("fencedin.in"));
        PrintWriter out = new PrintWriter(new FileWriter("fencedin.out"));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int maxRL = Integer.parseInt(tokenizer.nextToken());
        int maxCL = Integer.parseInt(tokenizer.nextToken());
        int rowDiv = Integer.parseInt(tokenizer.nextToken());
        int colDiv = Integer.parseInt(tokenizer.nextToken());

        int[] rowCoord = new int[rowDiv + 2];
        for (int i = 0; i < rowDiv; i++) {
            rowCoord[i] = Integer.parseInt(reader.readLine());
        }
        rowCoord[rowDiv] = 0;
        rowCoord[rowDiv + 1] = maxRL;
        Arrays.sort(rowCoord);

        int[] colCoordinates = new int[colDiv + 2];
        for (int i = 0; i < colDiv; i++) {
            colCoordinates[i] = Integer.parseInt(reader.readLine());
        }
        colCoordinates[colDiv] = 0;
        colCoordinates[colDiv + 1] = maxCL;
        Arrays.sort(colCoordinates);

        FenceSeg[] full = new FenceSeg[rowDiv + colDiv + 2];
        for (int i = 0; i <= rowDiv; i++) {
            full[i] = new FenceSeg(i, rowCoord[i + 1] - rowCoord[i]);
        }
        for (int i = 0; i <= colDiv; i++) {
            full[rowDiv + 1 + i] = new FenceSeg(rowDiv + 1 + i, colCoordinates[i + 1] - colCoordinates[i]);
        }
        Arrays.sort(full);
        DisjointSetUnion dsu = new DisjointSetUnion(rowDiv * (colDiv + 1) + colDiv * (rowDiv + 1));
        long minFenceCost = 0;
        int edgesAdded = 0;
        int segmentIndex = 0;

        while (edgesAdded < (rowDiv + 1) * (colDiv + 1) - 1) {
            FenceSeg currentSeg = full[segmentIndex];
            if (currentSeg.id <= rowDiv) {
                int rowIdx = currentSeg.id;
                for (int i = 0; i < colDiv; i++) {
                    if (dsu.union(rowIdx * (colDiv + 1) + i, rowIdx * (colDiv + 1) + i + 1)) {
                        minFenceCost += currentSeg.length;
                        edgesAdded++;
                    }
                }
            } else {
                int colIdx = currentSeg.id - rowDiv - 1;
                for (int i = 0; i < rowDiv; i++) {
                    if (dsu.union(i * (colDiv + 1) + colIdx, (i + 1) * (colDiv + 1) + colIdx)) {
                        minFenceCost += currentSeg.length;
                        edgesAdded++;
                    }
                }
            }
            segmentIndex++;
        }

        out.println(minFenceCost);
        out.close();
        reader.close();
    }

    class Node {
        public int parent;
        public int rank;

        public Node(int parent, int rank) {
            this.parent = parent;
            this.rank = rank;
        }
    }

    public class FenceSeg implements Comparable<FenceSeg> {
        public int id;
        public int length;

        public FenceSeg(int id, int length) {
            this.id = id;
            this.length = length;
        }

        public int compareTo(FenceSeg other) {
            return Integer.compare(this.length, other.length);
        }
    }

    public class DisjointSetUnion {
        public Node[] nodes;

        public DisjointSetUnion(int size) {
            nodes = new Node[size];
            for (int i = 0; i < size; i++) {
                nodes[i] = new Node(i, 0);
            }
        }

        public int find(int element) {
            if (nodes[element].parent != element) {
                nodes[element].parent = find(nodes[element].parent);
            }
            return nodes[element].parent;
        }

        public boolean union(int elem1, int elem2) {
            int root1 = find(elem1);
            int root2 = find(elem2);

            if (root1 == root2) {
                return false;
            }

            if (nodes[root1].rank > nodes[root2].rank) {
                nodes[root2].parent = root1;
            } else if (nodes[root1].rank < nodes[root2].rank) {
                nodes[root1].parent = root2;
            } else {
                nodes[root2].parent = root1;
                nodes[root1].rank++;
            }

            return true;
        }
    }
}
