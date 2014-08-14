package main;

import com.shu_mj.collections.Pair;
import com.shu_mj.ds.Intervals;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskJ {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] walls = in.nextIntMatrix(n, 4);
        int[][] birds = in.nextIntMatrix(m, 2);
        List<Item> is = new ArrayList<Item>();
        Bird[] bs = new Bird[m];
        for (int i = 0; i < m; i++) {
            bs[i] = new Bird(birds[i][0], birds[i][1]);
        }
        for (int i = 0; i < n + m; i++) {
            if (i < n) {
                if (walls[i][0] == walls[i][2])
                    is.add(new Wall(walls[i][0], walls[i][1], walls[i][3], i));
                else {
                    is.add(new Wall(walls[i][0], walls[i][1], walls[i][1], i));
                    is.add(new Wall(walls[i][2], walls[i][1], walls[i][1], i));
                }
            } else {
                is.add(bs[i - n]);
            }
        }
        Collections.sort(is);
        work(is);
        Collections.reverse(is);
        work(is);
        is.clear();
        for (int i = 0; i < n + m; i++) {
            if (i < n) {
                if (walls[i][1] == walls[i][3])
                    is.add(new Wall(walls[i][1], walls[i][0], walls[i][2], i));
                else {
                    is.add(new Wall(walls[i][1], walls[i][0], walls[i][0], i));
                    is.add(new Wall(walls[i][3], walls[i][0], walls[i][0], i));
                }
            } else {
                Bird b = bs[i - n];
                int t = b.x; b.x = b.y; b.y = t;
                is.add(b);
            }
        }
        Collections.sort(is);
        work(is);
        Collections.reverse(is);
        work(is);
        int[] cnt = new int[n];
        for (Bird b : bs) {
            if (b.id != -1) cnt[b.id]++;
        }
        for (int i : cnt) {
            out.println(i);
        }
    }

    final int INF = Integer.MAX_VALUE;

    private void work(List<Item> is) {
        Intervals<Integer, P> Int = new Intervals<Integer, P>(-INF, INF, new P(-INF, -1));
        for (Item i : is) {
            if (i instanceof Bird) {
                Bird b = (Bird) i;
                P p = Int.get(b.y);
                if (p.id == -1) continue;
                int d = Math.abs(b.x - p.val);
                if (b.id == -1 || d < b.min) {
                    b.min = d;
                    b.id = p.id;
                }
            } else {
                Wall w = (Wall) i;
                int y = Math.min(w.y, w.y2), y2 = Math.max(w.y, w.y2);
                Int.paint(y, y2 + 1, new P(w.x, w.id));
            }
        }
    }
    class P {
        int val;
        int id;

        P(int val, int id) {
            this.val = val;
            this.id = id;
        }
    }

    abstract class Item implements Comparable<Item> {
        abstract int getX();

        @Override
        public int compareTo(Item o) {
            if (getX() != o.getX()) return getX() - o.getX();
            int a = this instanceof Bird ? 1 : 0;
            int b = o instanceof Bird ? 1 : 0;
            return a - b;
        }
    }

    class Bird extends Item {
        int x;
        int y;
        int min = -1;
        int id = -1;

        Bird(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        int getX() {
            return x;
        }
    }
    class Wall extends Item {
        int x;
        int y;
        int y2;
        int id;

        Wall(int x, int y, int y2, int id) {
            this.x = x;
            this.y = y;
            this.y2 = y2;
            this.id = id;
        }

        @Override
        int getX() {
            return x;
        }
    }
}
