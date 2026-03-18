import java.util.*;
import java.io.*;

public class Clash {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        long[] a = new long[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            a[i] = Long.parseLong(st.nextToken());
        }
        int k = Integer.parseInt(br.readLine().trim());
        boolean[] isWin = new boolean[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            isWin[Integer.parseInt(st.nextToken())] = true;
        }
        PriorityQueue<int[]> hand = new PriorityQueue<>((x, y) -> {
            int wx = isWin[x[0]] ? 0 : 1;
            int wy = isWin[y[0]] ? 0 : 1;
            if (wx != wy) {
                return wx - wy;
            }
            if (a[x[0]] != a[y[0]]) {
                return Long.compare(a[x[0]], a[y[0]]);
            }
            return x[0] - y[0];
        });
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= H; i++) {
            hand.offer(new int[]{i});
        }
        for (int i = H + 1; i <= N; i++) {
            queue.addLast(i);
        }
        int cycleLen = N - H + 1;
        int[] cycleCards = new int[cycleLen];
        long curTime = 0, curMoo = 0;
        List<Long> preWinTimes = new ArrayList<>();
        for (int draw = 0; draw < cycleLen; draw++) {
            int[] top = hand.poll();
            int cardId = top[0];
            long cost = a[cardId];
            if (curMoo < cost) {
                long d = cost - curMoo;
                curMoo += d;
                curTime += d;
            }
            if (curTime < 1) {
                long d = 1 - curTime;
                curTime += d;
                curMoo += d;
            }
            if (curMoo < cost) {
                long d = cost - curMoo;
                curMoo += d;
                curTime += d;
            }
            curMoo -= cost;
            if (isWin[cardId]) {
                preWinTimes.add(curTime);
            }
            cycleCards[draw] = cardId;
            if (!queue.isEmpty()) {
                int drawn = queue.pollFirst();
                hand.offer(new int[]{drawn});
            }
            queue.addLast(cardId);
        }
        long[] cycleCosts = new long[cycleLen];
        boolean[] cycleWin = new boolean[cycleLen];
        for (int i = 0; i < cycleLen; i++) {
            cycleCosts[i] = a[cycleCards[i]];
            cycleWin[i] = isWin[cycleCards[i]];
        }
        long[] prefixCost = new long[cycleLen + 1];
        long[] prefixWin = new long[cycleLen + 1];
        for (int i = 0; i < cycleLen; i++) {
            prefixCost[i + 1] = prefixCost[i] + cycleCosts[i];
            prefixWin[i + 1] = prefixWin[i] + (cycleWin[i] ? 1 : 0);
        }
        long sumCycle = prefixCost[cycleLen];
        long winsPerCycle = prefixWin[cycleLen];
        long[] playTime = new long[cycleLen];
        {
            long rem = curMoo;
            long t = curTime;
            for (int i = 0; i < cycleLen; i++) {
                long w = Math.max(1L, cycleCosts[i] - rem);
                t += w;
                rem = rem + w - cycleCosts[i];
                playTime[i] = t;
            }
        }
        long cycleDur = playTime[cycleLen - 1] - curTime;
        long mooAfterCycle = curMoo + cycleDur - sumCycle;
        long fastT = 0;
        for (int i = 0; i < cycleLen; i++) {
            fastT = Math.max(fastT, cycleCosts[i] + prefixCost[i] - i);
        }
        int kc = (int) winsPerCycle;
        List<long[]> blockMeta = new ArrayList<>();
        List<long[]> blockWinOffsets = new ArrayList<>();
        long cMoo = curMoo;
        long cTime = curTime;
        int maxIter = 3 * cycleLen + 20;
        for (int iter = 0; iter < maxIter; iter++) {
            if (cMoo >= fastT) {
                long[] offs = new long[kc];
                int wi = 0;
                for (int i = 0; i < cycleLen; i++) {
                    if (cycleWin[i]) {
                        offs[wi++] = i + 1;
                    }
                }
                blockMeta.add(new long[]{cTime, cycleLen, Long.MAX_VALUE / 2});
                blockWinOffsets.add(offs);
                break;
            }
            long[] offs = new long[kc];
            long rem = cMoo, t = 0;
            int wi = 0;
            long T = 0;
            for (int i = 0; i < cycleLen; i++) {
                long w = Math.max(1L, cycleCosts[i] - rem);
                t += w;
                rem = rem + w - cycleCosts[i];
                T = t;
                if (cycleWin[i]) {
                    offs[wi++] = t;
                }
            }
            long nextMoo = cMoo + T - sumCycle;
            if (nextMoo == cMoo) {
                blockMeta.add(new long[]{cTime, T, Long.MAX_VALUE / 2});
                blockWinOffsets.add(offs);
                break;
            }
            long T1 = getT(cycleCosts, cycleLen, nextMoo);
            if (T1 == T) {
                long delta = T - sumCycle;
                if (delta > 0) {
                    long R = (fastT - cMoo + delta - 1) / delta;
                    if (R <= 0) {
                        R = 1;
                    }
                    blockMeta.add(new long[]{cTime, T, R});
                    blockWinOffsets.add(offs);
                    cTime += R * T;
                    cMoo += R * delta;
                } else {
                    long lo = 0, hi = cMoo;
                    while (lo < hi) {
                        long mid = lo + (hi - lo) / 2;
                        if (getT(cycleCosts, cycleLen, mid) == T) {
                            hi = mid;
                        } else {
                            lo = mid + 1;
                        }
                    }
                    long boundMoo = lo;
                    long R = (cMoo - boundMoo) / (-delta);
                    if (R <= 0) {
                        R = 1;
                    }
                    blockMeta.add(new long[]{cTime, T, R});
                    blockWinOffsets.add(offs);
                    cTime += R * T;
                    cMoo += R * delta;
                }
                continue;
            }
            long nextNextMoo = nextMoo + T1 - sumCycle;
            if (nextNextMoo == cMoo) {
                long[] offs2 = new long[kc];
                long rem2 = nextMoo;
                long t2 = 0;
                int wi2 = 0;
                for (int i = 0; i < cycleLen; i++) {
                    long w = Math.max(1L, cycleCosts[i] - rem2);
                    t2 += w;
                    rem2 = rem2 + w - cycleCosts[i];
                    if (cycleWin[i]) {
                        offs2[wi2++] = t2;
                    }
                }
                long[] merged = new long[kc * 2];
                for (int i = 0; i < kc; i++) {
                    merged[i] = offs[i];
                    merged[kc + i] = T + offs2[i];
                }
                blockMeta.add(new long[]{cTime, T + T1, Long.MAX_VALUE / 2});
                blockWinOffsets.add(merged);
                break;
            }
            blockMeta.add(new long[]{cTime, T, 1});
            blockWinOffsets.add(offs);
            cTime += T;
            cMoo = nextMoo;
        }
        long[] preWins = new long[preWinTimes.size()];
        for (int i = 0; i < preWinTimes.size(); i++) {
            preWins[i] = preWinTimes.get(i);
        }
        int Q = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();
        for (int q = 0; q < Q; q++) {
            long t = Long.parseLong(br.readLine().trim());
            long ans = upperBound(preWins, t);
            for (int b = 0; b < blockMeta.size(); b++) {
                long[] bl = blockMeta.get(b);
                if (bl[0] > t) {
                    break;
                }
                long[] bOffs = blockWinOffsets.get(b);
                int bKc = bOffs.length;
                long relT = t - bl[0];
                long fullCycles = Math.min(relT / bl[1], bl[2]);
                long remT = (fullCycles < bl[2]) ? relT - fullCycles * bl[1] : 0;
                ans += fullCycles * bKc + (fullCycles < bl[2] ? upperBound(bOffs, remT) : 0);
            }
            sb.append(ans).append('\n');
        }
        System.out.print(sb);
    }

    public static long getT(long[] costs, int M, long moo) {
        long rem = moo, T = 0;
        for (int i = 0; i < M; i++) {
            long w = Math.max(1L, costs[i] - rem);
            T += w;
            rem = rem + w - costs[i];
        }
        return T;
    }

    public static int upperBound(long[] arr, long val) {
        int lo = 0, hi = arr.length;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (arr[mid] <= val) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }
}
