import java.util.*;
import java.io.*;

public class GAME {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) {
                continue;
            }
            int n = Integer.parseInt(line);
            if (n == 0) {
                break;
            }

            List<String> teamList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                teamList.add(br.readLine().trim());
            }

            int numGames = n - 1;
            Map<String, Set<String>> adj = new LinkedHashMap<>();
            for (String t : teamList) {
                adj.put(t, new LinkedHashSet<>());
            }

            for (int i = 0; i < numGames; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String a = st.nextToken();
                String b = st.nextToken();
                adj.get(a).add(b);
                adj.get(b).add(a);
            }

            Set<String> active = new LinkedHashSet<>(teamList);
            int roundNum = 0;
            while (active.size() > 1) {
                roundNum++;
                sb.append("Round #").append(roundNum).append("\n");
                Map<String, Integer> deg = new LinkedHashMap<>();
                for (String t : active) {
                    int d = 0;
                    for (String nb : adj.get(t)) {
                        if (active.contains(nb)) {
                            d++;
                        }
                    }
                    deg.put(t, d);
                }

                Set<String> matched = new HashSet<>();
                List<String[]> roundGames = new ArrayList<>();
                String wildcard = null;
                Queue<String> queue = new LinkedList<>();
                for (String t : active) {
                    if (deg.get(t) == 1) {
                        queue.add(t);
                    }
                }

                while (!queue.isEmpty()) {
                    String leaf = queue.poll();
                    if (matched.contains(leaf)) {
                        continue;
                    }

                    String parent = null;
                    for (String nb : adj.get(leaf)) {
                        if (active.contains(nb) && !matched.contains(nb)) {
                            parent = nb;
                            break;
                        }
                    }
                    if (parent == null) {
                        continue;
                    }

                    matched.add(leaf);
                    matched.add(parent);
                    int leafOthers = 0;
                    for (String nb : adj.get(leaf)) {
                        if (active.contains(nb) && !nb.equals(parent)) {
                            leafOthers++;
                        }
                    }
                    int parentOthers = 0;
                    for (String nb : adj.get(parent)) {
                        if (active.contains(nb) && !nb.equals(leaf)) {
                            parentOthers++;
                        }
                    }

                    String winner = (leafOthers >= parentOthers) ? leaf : parent;
                    String loser = winner.equals(leaf) ? parent : leaf;
                    roundGames.add(new String[]{winner, loser});
                    for (String nb : adj.get(parent)) {
                        if (active.contains(nb) && !nb.equals(leaf) && !matched.contains(nb)) {
                            deg.put(nb, deg.get(nb) - 1);
                            if (deg.get(nb) == 1) {
                                queue.add(nb);
                            }
                        }
                    }
                    for (String nb : adj.get(leaf)) {
                        if (active.contains(nb) && !nb.equals(parent) && !matched.contains(nb)) {
                            deg.put(nb, deg.get(nb) - 1);
                            if (deg.get(nb) == 1) {
                                queue.add(nb);
                            }
                        }
                    }
                }

                for (String t : active) {
                    if (!matched.contains(t)) {
                        wildcard = t;
                        break;
                    }
                }

                for (String[] g : roundGames) {
                    sb.append(g[0]).append(" defeats ").append(g[1]).append("\n");
                }
                if (wildcard != null) {
                    sb.append(wildcard).append(" advances with wildcard\n");
                }

                for (String[] g : roundGames) {
                    String winner = g[0];
                    String loser = g[1];

                    for (String nb : adj.get(loser)) {
                        if (!nb.equals(winner)) {
                            adj.get(nb).remove(loser);
                            adj.get(nb).add(winner);
                            adj.get(winner).add(nb);
                        }
                    }
                    adj.get(winner).remove(loser);
                    adj.put(loser, new LinkedHashSet<>());
                    active.remove(loser);
                }
            }

            String ans = active.iterator().next();
            sb.append("Winner: ").append(ans).append("\n\n");
        }
        System.out.print(sb);
    }
}
