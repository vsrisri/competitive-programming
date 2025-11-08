import java.util.*;
import java.io.*;

public class FamilyTree {
    public static int n;
	public static String[][] input;
	public static HashMap<String,Integer> map;
	public static Node[] cows;
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("family.in"));
        PrintWriter pw = new PrintWriter(new File("family.out"));
        int n = sc.nextInt();
        map = new HashMap<String,Integer>();
		map.put(sc.next(), 0);
		map.put(sc.next(), 1);
        int id = 2;
		input = new String[n][2];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 2; j++) {
				input[i][j] = sc.next();
				if (!map.containsKey(input[i][j])) {
					map.put(input[i][j], id++);
                }
            }
        }
        cows = new Node[id];
		Arrays.fill(cows, null);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 2; j++) {
				if (cows[map.get(input[i][j])] == null) {
					cows[map.get(input[i][j])] = new Node(input[i][j]);
                }
            }
        }

        for (int i = 0; i < n; i++) {
			int mom1 = map.get(input[i][0]);
			int kid1 = map.get(input[i][1]);
			cows[mom1].kids.add(cows[kid1]);
			cows[kid1].mom = cows[mom1];
		}

        pw.print(FamilyTree.helper());
        sc.close();
        pw.close();
    }

    public static String helper() {
        ArrayList<Node> bList = new ArrayList<Node>();
		Node bStart = cows[1];
		bList.add(bStart);
		int i = 0;
		while (true) {
            if (bStart == cows[0]) {
				if (i == 1) {
                    return cows[0]+" is the mother of "+ cows[1];
                }
				if (i == 2) {
                    return cows[0]+" is the grand-mother of "+cows[1];
                }
				String ret = "";
				for (int idx = 2; idx < i; idx++) {
                    ret = ret + "great-";
                }
				return cows[0] + " is the " + ret + "grand-mother of " + cows[1];
			}

            Node tmp = bStart.mom;
            if (tmp == null) {
                break;
			} else {
				bStart = tmp;
				bList.add(bStart);
			}
			i++;
        }
        ArrayList<Node> eList = new ArrayList<Node>();
		Node eStart = cows[0];
		eList.add(eStart);
		int i2 = 0;
		while (true) {
			if (eStart == cows[1]) {
				if (i2 == 1) {
                    return cows[1] + " is the mother of " + cows[0];
                }
				if (i2 == 2) {
                    return cows[1] + " is the grand-mother of " + cows[0];
                }

				String ret = "";
				for (int idx = 2; idx < i2; idx++) {
                    ret = ret + "great-";
                }
				return cows[1]+ " is the " + ret + "grand-mother of " + cows[0];
			}

			Node tmp = eStart.mom;
			if (tmp == null) {
                break;
			} else {
				eStart = tmp;
				eList.add(eStart);
			}
			i2++;
		}
		if (eStart != bStart) {
            return "NOT RELATED";
        }
		if (cows[0].mom != null && cows[0].mom == cows[1].mom) {
            return "SIBLINGS";
        }

		Collections.reverse(eList);
		Collections.reverse(bList);
		i = 0;
		while (eList.get(i) == bList.get(i)) {
            i++;
        }
		i--;
		int eDown = eList.size() - i - 1;
		int bDown = bList.size() - i - 1;

		if (eDown == 1) {
			if (bDown == 2) {
                return cows[0] + " is the aunt of "+ cows[1];
            }
			String ret = "";
			for (int j = 2; j < bDown; j++) {
                ret = ret + "great-";
            }
			return cows[0] + " is the " + ret + "aunt of " + cows[1];
		}
		else if (bDown == 1) {
			if (eDown == 2) {
                return cows[1] + " is the aunt of " + cows[0];
            }
			String ret = "";
			for (int j=2; j<eDown; j++)  { 
                ret = ret + "great-";
            }
			return cows[1] + " is the " + ret + "aunt of " + cows[0];
		}
		else {
            return "COUSINS";
        }
	}

    static class Node {
	    public String name;
	    public ArrayList<Node> kids;
	    public Node mom;

	    public Node(String name) {
		    name = name;
		    kids = new ArrayList<Node>();
		    mom = null;
	    }

	    public String toString() {
		    return name;
	    }
    }
}
