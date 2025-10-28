import java.io.*;
import java.util.*;

public class AKBAR {
    public static class Pair {
        int city;
        int strength;
        Pair(int city, int strength) {
            this.city = city;
            this.strength = strength;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int[] visited = new int[N + 1];
            List<List<Integer>> graph = new ArrayList<>();
            Queue<Pair> queue = new LinkedList<>();
            boolean isConf = false;
            boolean isDone = true;
            for (int i = 0; i <= N; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < R; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph.get(a).add(b);
                graph.get(b).add(a);
            }


            for (int i = 1; i <= M; i++) {
                st = new StringTokenizer(br.readLine());
                int k = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                if (visited[k] != 0) {
                    isConf = true;
                }
                visited[k] = i;
                queue.add(new Pair(k, s));

                while (!queue.isEmpty()) {
                    Pair curr = queue.poll();
                    if (curr.strength == 0) {
                        continue;
                    }
                    for (int neighb : graph.get(curr.city)) {
                        if (visited[neighb] == 0) {
                            visited[neighb] = i;
                            queue.add(new Pair(neighb, curr.strength - 1));
                        } else if (visited[neighb] != i) {
                            isConf = true;
                        }
                    }
                }
            }

            for (int i = 1; i <= N; i++) {
                if (visited[i] == 0) {
                    isDone = false;
                    break;
                }
            }

            if (!isConf && isDone) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
        br.close();
    }
}

