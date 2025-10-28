import java.io.*;
import java.util.*;

public class temp {
    public static int[] arr;
    public static Node[] sTreeNodes;

    public static class Node {
        int maxVal;
        int pos;

        Node(int maxVal, int pos) {
            this.maxVal = maxVal;
            this.pos = pos;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine());
        arr = new int[size + 1];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 1; i <= size; i++) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }

        sTreeNodes = new Node[4 * size + 1];
        buildSegmentTree(1, 1, size);
        int queries = Integer.parseInt(reader.readLine());
        for (int q = 0; q < queries; q++) {
            tokenizer = new StringTokenizer(reader.readLine());
            char op = tokenizer.nextToken().charAt(0);
            if (op == 'Q') {
                pQuery(tokenizer);
            } else if (op == 'U') {
                pUpd(tokenizer);
            }
        }
        reader.close();
    }

    public static void buildSegmentTree(int node, int start, int end) {
        int mid = (start + end) / 2;
        if (start == end) {
            sTreeNodes[node] = new Node(arr[start], start);
            return;
        }

        buildSegmentTree(2 * node, start, mid);
        buildSegmentTree(2 * node + 1, mid + 1, end);
        sTreeNodes[node] = findMax(sTreeNodes[2 * node], sTreeNodes[2 * node + 1]);
    }

    public static void pQuery(StringTokenizer tokenizer) {
        int leftRange = Integer.parseInt(tokenizer.nextToken());
        int rightRange = Integer.parseInt(tokenizer.nextToken());
        Node maxNode = queryMaxVal(1, 1, arr.length - 1, leftRange, rightRange);
        int firstMaxpos = maxNode.pos;
        int secondMax1 = Integer.MIN_VALUE;
        int secondMax2 = Integer.MIN_VALUE;
        if (firstMaxpos != leftRange) {
            secondMax1 = queryMaxVal(1, 1, arr.length - 1, leftRange, firstMaxpos - 1).maxVal;
        }
        if (firstMaxpos != rightRange) {
            secondMax2 = queryMaxVal(1, 1, arr.length - 1, firstMaxpos + 1, rightRange).maxVal;
        }

        System.out.println(arr[firstMaxpos] + Math.max(secondMax1, secondMax2));
    }

    public static void pUpd(StringTokenizer tokenizer) {
        int indexToUpd = Integer.parseInt(tokenizer.nextToken());
        int valueToUpd = Integer.parseInt(tokenizer.nextToken());
        updVal(1, 1, arr.length - 1, indexToUpd, valueToUpd);
    }

    public static Node queryMaxVal(int node, int start, int end, int left, int right) {
        int mid = (start + end) / 2;
        if (start > right || end < left) {
            return new Node(Integer.MIN_VALUE, Integer.MIN_VALUE);
        }

        if (start >= left && end <= right) {
            return sTreeNodes[node];
        }

        Node leftNode = queryMaxVal(2 * node, start, mid, left, right);
        Node rightNode = queryMaxVal(2 * node + 1, mid + 1, end, left, right);
        return findMax(leftNode, rightNode);
    }

    public static void updVal(int node, int start, int end, int index, int value) {
        int mid = (start + end) / 2;
        if (start == end && start == index) {
            arr[index] = value;
            sTreeNodes[node].maxVal = value;
            return;
        }

        if (mid < index) {
            updVal(2 * node + 1, mid + 1, end, index, value);
        } else {
            updVal(2 * node, start, mid, index, value);
        }
        sTreeNodes[node] = findMax(sTreeNodes[2 * node], sTreeNodes[2 * node + 1]);
    }

    public static Node findMax(Node a, Node b) {
        if (a.maxVal > b.maxVal) {
            return a;
        } else if (a.maxVal < b.maxVal) {
            return b;
        } else {
            return a.pos < b.pos ? a : b;
        }
    }

