import java.util.*;
import java.io.*;

public class MooinTime {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(tokenizer.nextToken());
        int f = Integer.parseInt(tokenizer.nextToken());
        String str = reader.readLine();
        HashMap<String, Integer> startMoos = new HashMap<String, Integer>();
        HashMap<String, HashSet<Integer>> startMoos2 = new HashMap<String, HashSet<Integer>>();
        HashSet<String> outset = new HashSet<String>();
        for (int idx = 0; idx < n; idx++) {
            if (idx + 3 > n) {
                continue;
            }
            String curr = str.substring(idx, idx + 3);
            //check if its a moo
            if (curr.charAt(1) == curr.charAt(2) && curr.charAt(0) != curr.charAt(1)) {
                // is it already in map?
                if (startMoos.containsKey(curr)) {
                    //System.out.println("putting " + curr + " in map, already there");
                    startMoos.put(curr, startMoos.get(curr) + 1);
                    startMoos2.get(curr).add(idx);
                    if (startMoos.get(curr) == f) {
                        outset.add(curr);
                    }
                } else {
                    //System.out.println("putting " + curr + " in map");
                    HashSet<Integer> hs = new HashSet<Integer>();
                    hs.add(idx);
                    startMoos.put(curr, 1);
                    startMoos2.put(curr, hs);
                }
            }
        }

        //check if we can make moos
        for (int idx = 0; idx < n; idx++) {
            if (idx + 3 > n) {
                continue;
            }
            String curr = str.substring(idx, idx + 3);
            // check all configurations; if it already is a moo, change first letter
            if (curr.charAt(1) == curr.charAt(2)) {
                for (int i = 97; i <= 122; i++) {
                    String poss = Character.toString((char) (i)) + curr.substring(1, 3);
                    if (poss.equals(curr) || poss.charAt(0) == poss.charAt(1)) {
                        continue;
                    }
                    if (f > 1) {
                        if (startMoos.containsKey(poss) && startMoos.get(poss) == (f - 1)) {
                        // have to account for if the moo you change something to find overlaps with a moo thats already there; 
                            if (!startMoos2.get(poss).contains(idx) && !startMoos2.get(poss).contains(idx - 1)
                                && !startMoos2.get(poss).contains(idx - 2) && !startMoos2.get(poss).contains(idx + 1)
                                && !startMoos2.get(poss).contains(idx + 2)) {
                                outset.add(poss);
                            }
                        }
                    } else {
                        outset.add(poss);
                    }
                }
            } else {
                // if it is not already a moo, change third and second letter
                String poss1 = curr.substring(0, 2) + Character.toString(curr.charAt(1));
                String poss2 =  Character.toString(curr.charAt(0)) + Character.toString(curr.charAt(2)) + Character.toString(curr.charAt(2));
                if (f > 1) {
                    if (startMoos.containsKey(poss1) && startMoos.get(poss1) == (f - 1)) {
                        if (!startMoos2.get(poss1).contains(idx) && !startMoos2.get(poss1).contains(idx - 1)
                                && !startMoos2.get(poss1).contains(idx - 2) && !startMoos2.get(poss1).contains(idx + 1)
                                && !startMoos2.get(poss1).contains(idx + 2)) {
                            if (poss1.charAt(0) != poss1.charAt(1)) {
                                outset.add(poss1);
                            }
                        }
                    }
                    if (startMoos.containsKey(poss2) && startMoos.get(poss2) == (f - 1)) {
                        if (!startMoos2.get(poss2).contains(idx) && !startMoos2.get(poss2).contains(idx - 1)
                                && !startMoos2.get(poss2).contains(idx - 2) && !startMoos2.get(poss2).contains(idx + 1)
                                && !startMoos2.get(poss2).contains(idx + 2)) {
                            if (poss2.charAt(0) != poss2.charAt(1)) {
                                outset.add(poss2);
                            }
                        }
                    }
                } else {
                    if (poss1.charAt(0) != poss1.charAt(1)) {
                        outset.add(poss1);
                    }
                    if (poss2.charAt(0) != poss2.charAt(1)) {
                        outset.add(poss2);
                    }

                }
            }
        }

        ArrayList<String> outArr = new ArrayList<String>(outset);
        System.out.println(outArr.size());
        Collections.sort(outArr);
        for (String s : outArr) {
            System.out.println(s);
        }

        reader.close();

    }
}

