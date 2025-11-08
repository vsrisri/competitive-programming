// USACO 2019 January Contest, Bronze Problem 3. Guess the Animal
import java.util.*;
import java.io.*;
public class GuessTheAnimal {
    static class Animal {
        public String name;
        public HashSet<String> characteristics;

        public Animal(String name) {
            this.name = name;
            characteristics = new HashSet<String>();
        }
        public void addCharac(String c) {
            characteristics.add(c);
        }
        public int numShared(Animal a2) {
            int ans = 0;
            for (String c : a2.characteristics) {
                if (this.characteristics.contains(c)) {
                    ans++;
                }
            }
            return ans;
        }

    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("guess.in"));
        PrintWriter p = new PrintWriter(new File("guess.out"));
        int n = sc.nextInt();
        sc.nextLine();
        Animal[] aList = new Animal[n];
        for (int i = 0; i < n; i++) {
            String name = sc.next();
            aList[i] = new Animal(name);
            int numQ = sc.nextInt();
            for (int j = 0; j < numQ; j++) {
                aList[i].addCharac(sc.next());
            }
        }
        int com = 0;
        for (int idx = 0; idx < n; idx++) {
            for (int idx2 = idx+1; idx2 < n; idx2++) {
                com = Math.max(com, aList[idx].numShared(aList[idx2]));
            }
        }
        p.println(com + 1) ;
        sc.close();
        p.close();
    }
}

