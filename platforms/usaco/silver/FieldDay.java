import java.io.*;
import java.util.*;

public class FieldDay {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int cowCount = Integer.parseInt(tokenizer.nextToken());
        int teamCount = Integer.parseInt(tokenizer.nextToken());

        String[] tArr = new String[teamCount];
        for (int i = 0; i < teamCount; i++) {
            tArr[i] = br.readLine().trim();
        }

        int[] dist = bfs(cowCount, tArr);

        for (int i = 0; i < teamCount; i++) {
            int maxDist = getMaxDist(tArr[i], dist, cowCount);
            System.out.println(maxDist);
        }
    }

    public static int[] bfs(int cowCount, String[] tArr) {
        int total = Math.pow(2, cowCount);
        Map<String, Integer> dist = new HashMap<>();
        Queue<String> bfsQueue = new ArrayDeque<>();

        for (String team : tArr) {
            bfsQueue.add(team);
            dist.put(team, 0);
        }

        while (!bfsQueue.isEmpty()) {
            String currentTeam = bfsQueue.poll();
            for (int i = 0; i < cowCount; i++) {
                char[] flippedTeam = currentTeam.toCharArray();
                flippedTeam[i] = (flippedTeam[i] == 'G') ? 'H' : 'G';
                String newTeam = new String(flippedTeam);

                if (!dist.containsKey(newTeam)) {
                    dist.put(newTeam, dist.get(currentTeam) + 1);
                    bfsQueue.add(newTeam);
                }
            }
        }

        int[] distArray = new int[dist.size()];
        int idx = 0;
        for (String team : dist.keySet()) {
            distArray[idx++] = dist.get(team);
        }
        return distArray;
    }

    public static int getMaxDist(String team, int[] dist, int cowCount) {
        int maxDist = 0;

        for (int i = 0; i < dist.length; i++) {
            String otherTeam = dist[i];
            int currDist = findBreedDiff(team, otherTeam);
            maxDist = Math.max(maxDist, currDist);
        }
        return maxDist;
    }

    public static int findBreedDiff(String team1, String team2) {
        int diff = 0;
        for (int i = 0; i < team1.length(); i++) {
            if (team1.charAt(i) != team2.charAt(i)) {
                diff++;
            }
        }
        return diff;
    }
}

