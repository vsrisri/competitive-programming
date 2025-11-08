import java.util.*;
import java.io.*;

public class PaintingFencePosts {
    static class Pair {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Pair pair = (Pair) obj;
            return x == pair.x && y == pair.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        List<Pair> posts = new ArrayList<>();
        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            posts.add(new Pair(x, y));
        }

        List<Pair[]> startEnds = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            startEnds.add(new Pair[]{new Pair(x1, y1), new Pair(x2, y2)});
        }

        List<Pair> queryPoses = new ArrayList<>();
        for (Pair[] se : startEnds) {
            queryPoses.add(se[0]);
            queryPoses.add(se[1]);
        }

        List<Pair> origPosts = new ArrayList<>(posts);
        posts.sort(Comparator.comparingInt(a -> a.x));
        queryPoses.sort(Comparator.comparingInt(a -> a.x));

        Map<Integer, List<Pair>> postsAtX = new HashMap<>();
        Map<Integer, List<Pair>> postsAtY = new HashMap<>();
        Map<Integer, List<Pair>> queriesAtX = new HashMap<>();
        Map<Integer, List<Pair>> queriesAtY = new HashMap<>();

        for (Pair post : posts) {
            postsAtX.computeIfAbsent(post.x, k -> new ArrayList<>()).add(post);
            postsAtY.computeIfAbsent(post.y, k -> new ArrayList<>()).add(post);
        }

        for (Pair query : queryPoses) {
            queriesAtX.computeIfAbsent(query.x, k -> new ArrayList<>()).add(query);
            queriesAtY.computeIfAbsent(query.y, k -> new ArrayList<>()).add(query);
        }

        Map<Pair, Pair[]> vertFence = new HashMap<>();
        Map<Pair, Pair[]> horzFence = new HashMap<>();
        Map<Pair, Pair[]> onFence = new HashMap<>();

        for (Map.Entry<Integer, List<Pair>> entry : postsAtX.entrySet()) {
            List<Pair> xPosts = entry.getValue();
            for (int i = 0; i < xPosts.size(); i += 2) {
                Pair a = xPosts.get(i);
                Pair b = xPosts.get(i + 1);
                vertFence.put(a, new Pair[]{a, b});
                vertFence.put(b, new Pair[]{a, b});
            }
            for (Pair query : queriesAtX.getOrDefault(entry.getKey(), Collections.emptyList())) {
                for (int i = 0; i < xPosts.size() - 1; i += 2) {
                    Pair a = xPosts.get(i);
                    Pair b = xPosts.get(i + 1);
                    if (a.y <= query.y && query.y <= b.y) {
                        onFence.put(query, new Pair[]{a, b});
                        break;
                    }
                }
            }
        }

        for (Map.Entry<Integer, List<Pair>> entry : postsAtY.entrySet()) {
            List<Pair> yPosts = entry.getValue();
            for (int i = 0; i < yPosts.size(); i += 2) {
                Pair a = yPosts.get(i);
                Pair b = yPosts.get(i + 1);
                horzFence.put(a, new Pair[]{a, b});
                horzFence.put(b, new Pair[]{a, b});
            }
            for (Pair query : queriesAtY.getOrDefault(entry.getKey(), Collections.emptyList())) {
                for (int i = 0; i < yPosts.size() - 1; i += 2) {
                    Pair a = yPosts.get(i);
                    Pair b = yPosts.get(i + 1);
                    if (a.x <= query.x && query.x <= b.x) {
                        onFence.put(query, new Pair[]{a, b});
                        break;
                    }
                }
            }
        }

        List<Pair> fencePosts = new ArrayList<>();
        fencePosts.add(posts.get(0));
        for (int i = 1; i < P; i++) {
            Pair lastPost = fencePosts.get(fencePosts.size() - 1);
            Pair[] fence = (i % 2 == 1) ? horzFence.get(lastPost) : vertFence.get(lastPost);
            Pair otherPost = fence[0].equals(lastPost) ? fence[1] : fence[0];
            fencePosts.add(otherPost);
        }

        Map<Pair, Integer> postToInd = new HashMap<>();
        for (int i = 0; i < fencePosts.size(); i++) {
            postToInd.put(fencePosts.get(i), i);
        }

        int[] ans = new int[P];
        int[] distTo = new int[P];
        int totalDist = 0;

        for (int i = 0; i < P; i++) {
            distTo[i] = totalDist;
            totalDist += getDist(fencePosts.get(i), fencePosts.get((i + 1) % P));
        }

        for (Pair[] se : startEnds) {
            int startDist = getDistAlong(se[0], distTo, onFence, postToInd);
            int endDist = getDistAlong(se[1], distTo, onFence, postToInd);

            if (startDist > endDist) {
                int temp = startDist;
                startDist = endDist;
                endDist = temp;
            }

            ans[startDist]++;
            ans[endDist]--;
        }

        for (int i = 1; i < P; i++) {
            ans[i] += ans[i - 1];
        }

        for (Pair post : origPosts) {
            System.out.println(ans[postToInd.get(post)]);
        }
    }

    public static int getDist(Pair a, Pair b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

    public static int getDistAlong(Pair query, int[] distTo, Map<Pair, Pair[]> onFence, Map<Pair, Integer> postToInd) {
        Pair[] fence = onFence.get(query);
        int ind = postToInd.get(fence[0]);
        return distTo[ind] + getDist(fence[0], query);
    }
}

