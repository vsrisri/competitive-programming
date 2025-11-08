import java.util.*;
import java.io.*;

public class MooLang {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(reader.readLine());

        for (int tc = 0; tc < t; tc++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            int n = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            ArrayList<String> nouns = new ArrayList<String>();
            ArrayList<String> tranVerbs = new ArrayList<String>();
            ArrayList<String> intranVerbs = new ArrayList<String>();
            ArrayList<String> conjs = new ArrayList<String>();
            for (int idx = 0; idx < n; idx++) {
                st = new StringTokenizer(reader.readLine());
                String word = st.nextToken();
                String type = st.nextToken();
                if (type.charAt(0) == 'n') {
                    nouns.add(word);
                } else if (type.charAt(0) == 't') {
                    tranVerbs.add(word);
                } else if (type.charAt(0) == 'i') {
                    intranVerbs.add(word);
                } else {
                    conjs.add(word);
                }
            }
            helper(nouns, tranVerbs, intranVerbs, conjs, c, p);
        }
        reader.close();
    }

    public static void helper(ArrayList<String> nouns, ArrayList<String> tranVerbs, ArrayList<String> intranVerbs, ArrayList<String> conjs, int c, int p) {
        ArrayList<String> outStr = new ArrayList<String>();
        int ans = 0;
        int i = 0;
        int t = 0;
        int numNounsAtEnd = 0;
        int numToMerge = 0;

        for (int idx = 0; idx <= intranVerbs.size(); idx++) {
            int numP = p;
            int numC = c;
            int numNouns = nouns.size();
            int numConj = conjs.size();
            int addToEnd = 0;
            int count = 0;

            int iCurr = idx;
            count+= 2 * iCurr;
            numNouns-= idx;

            if (numNouns < 0) {
                continue;
            }

            int  tCurr = min(tranVerbs.size(), (numNouns / 2), ((Math.min(numConj, numP) * 2) + Math.max(0, numP - numConj)));
            count+= 3 *  tCurr;
            numNouns-= 2 *  tCurr;

            int numCurrToMerge = iCurr +  tCurr - 1;
            int numCurrMergeConj = Math.min((numCurrToMerge + 1) / 2, numConj);
            count+= numCurrMergeConj;
            numP-= (iCurr +  tCurr) - numCurrMergeConj;

            if (numP < 0) {
                continue;
            }

            if (tCurr > 0) {
                addToEnd = Math.min(numNouns, numC);
                count+= addToEnd;
            }

            if (count > ans) {
                ans = count;
                i = iCurr;
                t =  tCurr;
                numNounsAtEnd = addToEnd;
                numToMerge = numCurrMergeConj;
            }

        }

        if (ans != 0) {
            ArrayList<ArrayList<String>> sents = new ArrayList<ArrayList<String>>();
            for (int idx = 0; idx < i; idx++) {
                ArrayList<String> curr = new ArrayList<String>();
                curr.add(nouns.get(0));
                nouns.remove(0);
                curr.add(intranVerbs.get(0));
                intranVerbs.remove(0);
                sents.add(curr);
            }

            for (int idx = 0; idx < t; idx++) {
                ArrayList<String> curr = new ArrayList<String>();
                curr.add(nouns.get(0));
                nouns.remove(0);
                curr.add(tranVerbs.get(0));
                tranVerbs.remove(0);
                curr.add(nouns.get(0));
                nouns.remove(0);
                sents.add(curr);
            }


            for (int idx = 0; idx < sents.size(); idx++) {
                for (String word : sents.get(idx)) {
                    outStr.add(word);
                    outStr.add(" ");
                }

                if (numToMerge > 0 && idx % 2 == 0) {
                    outStr.add(conjs.get(0));
                    outStr.add(" ");
                    conjs.remove(0);
                    numToMerge--;
                } else {
                    outStr.remove(outStr.size() - 1);
                    outStr.add(".");
                    outStr.add(" ");
                }
            }

            outStr.remove(outStr.size() - 1);
            if (numNounsAtEnd > 0) {
                outStr.remove(outStr.size() - 1);
                for (int idx = 0; idx < numNounsAtEnd; idx++) {
                    outStr.add(", ");
                    outStr.add(nouns.get(0));
                    nouns.remove(0);
                }
                outStr.add(".");
            }
        }

        System.out.println(ans);
        if (ans > 0) {
            for (String s : outStr) {
                System.out.print(s);
            }
        } 

        System.out.println();
    }

    public static int min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }


}
