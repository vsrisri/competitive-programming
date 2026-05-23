import java.util.*;
import java.io.*;

public class PANIC {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            br.readLine();
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int[] adjTo = new int[m * 2];
            long[] fwd = new long[m * 2];
            long[] bwd = new long[m * 2];
            int[] adjNext = new int[m * 2];
            int[] adjHead = new int[n + 1];
            Arrays.fill(adjHead, 0, n + 1, -1);
            int ec = 0;
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                long tuv = Long.parseLong(st.nextToken());
                long tvu = Long.parseLong(st.nextToken());
                adjTo[ec] = v; fwd[ec] = tuv; bwd[ec] = tvu; adjNext[ec] = adjHead[u]; adjHead[u] = ec++;
                adjTo[ec] = u; fwd[ec] = tvu; bwd[ec] = tuv; adjNext[ec] = adjHead[v]; adjHead[v] = ec++;
            }

            long[] d = new long[n + 1];
            Arrays.fill(d, 0, n + 1, Long.MAX_VALUE);
            int[] par = new int[n + 1];
            PriorityQueue<long[]> pq = new PriorityQueue<>(k + 1, Comparator.comparingLong(a -> a[0]));
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < k; i++) {
                int b = Integer.parseInt(st.nextToken());
                d[b] = 0;
                pq.offer(new long[]{0, b});
            }

            while (!pq.isEmpty()) {
                long[] cur = pq.poll();
                long dist = cur[0];
                int u = (int) cur[1];
                if (dist > d[u]) {
                    continue;
                }
                for (int e = adjHead[u]; e != -1; e = adjNext[e]) {
                    int v = adjTo[e];
                    long nd = d[u] + fwd[e];
                    if (nd < d[v]) {
                        d[v] = nd;
                        par[v] = u;
                        pq.offer(new long[]{nd, v});
                    }
                }
            }

            int[] childHead = new int[n + 1];
            int[] childNext = new int[n + 1];
            Arrays.fill(childHead, 0, n + 1, -1);
            int[] childOf = new int[n + 1];
            for (int v = 1; v <= n; v++) {
                if (d[v] != Long.MAX_VALUE && par[v] != 0) {
                    childNext[v] = childHead[par[v]];
                    childHead[par[v]] = v;
                    childOf[v] = par[v];
                }
            }

            int[] topoOrder = new int[n];
            int topoSize = 0;
            int[] childCount = new int[n + 1];
            for (int v = 1; v <= n; v++) {
                if (d[v] != Long.MAX_VALUE && par[v] != 0) {
                    childCount[par[v]]++;
                }
            }
            int[] bfsQ = new int[n];
            int qHead = 0, qTail = 0;
            for (int v = 1; v <= n; v++) {
                if (d[v] != Long.MAX_VALUE && childCount[v] == 0) {
                    bfsQ[qTail++] = v;
                }
            }
            while (qHead < qTail) {
                int v = bfsQ[qHead++];
                topoOrder[topoSize++] = v;
                int p = childOf[v];
                if (p != 0 && --childCount[p] == 0) {
                    bfsQ[qTail++] = p;
                }
            }

            double[] surv = new double[n + 1];
            for (int v = 1; v <= n; v++) {
                surv[v] = d[v] == Long.MAX_VALUE ? Double.POSITIVE_INFINITY : (double) d[v];
            }

            for (int i = 0; i < topoSize; i++) {
                int v = topoOrder[i];
                if (d[v] == Long.MAX_VALUE) {
                    continue;
                }
                for (int e = adjHead[v]; e != -1; e = adjNext[e]) {
                    int z = adjTo[e];
                    if (z == par[v]) {
                        continue;
                    }
                    if (par[z] == v) {
                        continue;
                    }
                    double cand;
                    if (d[z] == Long.MAX_VALUE) {
                        cand = Double.POSITIVE_INFINITY;
                    } else {
                        long tuv = fwd[e], tvu = bwd[e];
                        cand = d[v] + (double) tuv * (d[z] - d[v] + tvu) / (tuv + tvu);
                    }
                    if (cand > surv[v]) {
                        surv[v] = cand;
                    }
                }
                int p = childOf[v];
                if (p != 0 && surv[v] > surv[p]) {
                    surv[p] = surv[v];
                }
            }

            double best = Double.NEGATIVE_INFINITY;
            for (int v = 1; v <= n; v++) {
                if (surv[v] > best) {
                    best = surv[v];
                }
            }

            boolean first = true;
            for (int v = 1; v <= n; v++) {
                boolean eq = Double.isInfinite(best) ? Double.isInfinite(surv[v]) : surv[v] == best;
                if (eq) {
                    if (!first) {
                        sb.append(' ');
                    }
                    sb.append(v);
                    first = false;
                }
            }
            sb.append('\n');
        }
        System.out.print(sb);
        br.close();
    }
}
