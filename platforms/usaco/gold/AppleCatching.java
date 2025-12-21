import java.util.*;

public class AppleCatching {
    public static final int MOD = (int) 1e9 + 7;
    public static class Apple implements Comparable<Apple> {
        int q, x, y, w;
        
        Apple(int q, int x, int y, int w) {
            this.q = q;
            this.x = x;
            this.y = y;
            this.w = w;
        }
        
        public int compareTo(Apple b) {
            if (this.y != b.y) {
                return Integer.compare(this.y, b.y);
            }

            return Integer.compare(this.x, b.x);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Set<Apple> apples = new TreeSet<>();
        List<Apple> p = new ArrayList<>();
        
        for (int i = 0; i < N; i++) {
            int q = sc.nextInt();
            int t = sc.nextInt();
            int x = sc.nextInt();
            int n = sc.nextInt();
            int a = x + t;
            int b = t - x;
            p.add(new Apple(q, a, b, n));
        }
        
        p.sort((a, b) -> {
            if (a.x != b.x) {
                return Integer.compare(b.x, a.x);
            }
            return Integer.compare(b.y, a.y);
        });
        
        int ans = 0;
        for (int i = 0; i < N; i++) {
            Apple curr = p.get(i);
            if (curr.q == 2) {
                apples.add(curr);
            } else {
                while (!apples.isEmpty() && curr.w > 0) {
                    Iterator<Apple> it = apples.iterator();
                    if (!it.hasNext()) {
                        break;
                    }

                    Apple apple = it.next();
                    if (apple.compareTo(curr) >= 0) {
                        int d = Math.min(curr.w, apple.w);
                        curr.w -= d;
                        apples.remove(apple);
                        if (apple.w - d > 0) {
                            apples.add(new Apple(apple.q, apple.x, apple.y, apple.w - d));
                        }
                        ans += d;
                    } else {
                        break;
                    }
                }
            }
        }
        
        System.out.println(ans);
    }
}

