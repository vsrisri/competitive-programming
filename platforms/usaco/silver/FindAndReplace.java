import java.io.*;
import java.util.*;

public class FindAndReplace {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        for (int testCount = Integer.parseInt(reader.readLine()); testCount > 0; testCount--) {
            String before = reader.readLine();
            String after = reader.readLine();
            int[] becomes = new int[52];
            Arrays.fill(becomes, -1);
            boolean possible = true;
            Set<Character> set = new HashSet<>();

            for (int j = 0; j < before.length(); j++) {
                int b = letterToNode(before.charAt(j));
                int a = letterToNode(after.charAt(j));
                set.add(after.charAt(j));
                if (becomes[b] != -1 && becomes[b] != a) {
                    possible = false;
                }
                becomes[b] = a;
            }

            if (set.size() == 52) {
                possible = false;
            }

            if (before.equals(after)) {
                possible = true;
            }

            int answer = 0;
            if (possible) {
                int[] inDegree = new int[52];
                for (int a = 0; a < 52; a++) {
                    if (becomes[a] != -1 && becomes[a] != a) {
                        inDegree[becomes[a]]++;
                    }
                }

                for (int a = 0; a < 52; a++) {
                    if (becomes[a] != -1 && becomes[a] != a) {
                        answer++;
                    }
                }

                int[] seen = new int[52];
                for (int r = 0; r < 52; r++) {
                    if (seen[r] == 0) {
                        int a = r;
                        while (a != -1 && seen[a] == 0) {
                            seen[a] = r + 1;
                            a = becomes[a];
                        }
                        if (a != -1 && a != becomes[a] && seen[a] == r + 1) {
                            int s = a;
                            boolean freePass = false;
                            do {
                                seen[a] = 2;
                                if (inDegree[a] > 1) {
                                    freePass = true;
                                }
                                a = becomes[a];
                            } while (a != s);
                            if (!freePass) {
                                answer++;
                            }
                        }
                    }
                }
            } else {
                answer = -1;
            }

            output.append(answer).append('\n');
        }
        System.out.print(output);
    }

    static int letterToNode(char letter) {
        if ('a' <= letter && letter <= 'z') {
            return letter - 'a';
        } else {
            return 26 + (letter - 'A');
        }
    }
}

