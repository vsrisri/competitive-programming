import java.util.*;

public class TargetPractice {
    public static int delta(char a, char b) {
        int aInt = 0;
        int bInt = 0;
        if (a == 'L') {
            aInt = 1;
        } else if (a == 'R') {
            aInt = -1;
        }

        if (b == 'L') {
            bInt = 1;
        } else if (b == 'R') {
            bInt = -1;
        }

        return (aInt - bInt);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int tarCount = scanner.nextInt();
        int cmdCnt = scanner.nextInt();
        int[][] sufArr = new int[5][cmdCnt + 1];
        List<Map<Integer, Integer>> destroyedAt = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            destroyedAt.add(new HashMap<>());
        }

        Set<Integer> targets = new HashSet<>();
        for (int i = 0; i < tarCount; i++) {
            targets.add(scanner.nextInt());
        }

        String commands = scanner.next();
        int end = 0;
        for (int idx = 0; idx < commands.length; idx++) {
            if (commands.charAt(idx) == 'R') {
                end++;
            } else if (commands.charAt(idx) == 'L') {
                end--;
            }
        }

        for (int delta = -2; delta <= 2; delta++) {
            int pos = end + delta;
            Set<Integer> remainingTargets = new HashSet<>(targets);

            for (int i = cmdCnt - 1; i >= 0; i--) {
                int destroyed = 0;
                if (commands.charAt(i) == 'F' && remainingTargets.contains(pos)) {
                    destroyed = 1;
                    remainingTargets.remove(pos);
                    destroyedAt.get(2 + delta).put(pos, i);
                }
                sufArr[2 + delta][i] = sufArr[2 + delta][i + 1] + destroyed;

                if (commands.charAt(i) == 'L') pos++;
                if (commands.charAt(i) == 'R') pos--;
            }
        }

        int maxDest = 0;
        int currTarDest = 0;
        int currentPosition = 0;
        int[] overcount = new int[5];
        int[][] destOverArr = new int[5][cmdCnt + 1];

        for (int i = 0; i < cmdCnt; i++) {
            for (int delta = -2; delta <= 2; delta++) {
                overcount[2 + delta] -= destOverArr[2 + delta][i];
            }

            int deltaL = delta(commands.charAt(i), 'L');
            int deltaR = delta(commands.charAt(i), 'R');
            int deltaF = delta(commands.charAt(i), 'F');
            int currOutL = currTarDest + sufArr[2 + deltaL][i + 1] - overcount[2 + deltaL];
            int currOutR = currTarDest + sufArr[2 + deltaR][i + 1] - overcount[2 + deltaR];
            int currOutF = currTarDest + destroyed + sufArr[2 + deltaF][i + 1] - overcount[2 + deltaF] - destOver;

            int destroyed = 0;
            if (targets.contains(currentPosition)) destroyed = 1;

            int destOver = 0;
            if (destroyed == 1 && destroyedAt.get(2 + deltaF).getOrDefault(currentPosition, -1) > i) {
                destOver = 1;
            }

            maxDest = Math.max(maxDest, currOutR);
            maxDest = Math.max(maxDest, currOutL);
            maxDest = Math.max(maxDest, currOutF);

            if (commands.charAt(i) == 'L') {
                currentPosition--;
            } else if (commands.charAt(i) == 'R') {
                currentPosition++;
            } else if (targets.contains(currentPosition)) {
                targets.remove(currentPosition);
                currTarDest++;
                for (int delta = -2; delta <= 2; delta++) {
                    int destIdx = destroyedAt.get(2 + delta).getOrDefault(currentPosition, -1);
                    if (destIdx > i) {
                        overcount[2 + delta]++;
                        destOverArr[2 + delta][destIdx]++;
                    }
                }
            }
        }

        maxDest = Math.max(maxDest, currTarDest);
        System.out.println(maxDest);
    }
}

