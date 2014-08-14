package main;

import com.shu_mj.ds.Intervals;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskF {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }
    void run() {
        int n = in.nextInt();
        int[] as = new int[n];
        int[] bs = new int[n];
        int[] cs = new int[n];
        int[] ds = new int[n];
        for (int i = 0; i < n; i++) {
            as[i] = in.nextInt();
            bs[i] = in.nextInt();
            cs[i] = in.nextInt();
            ds[i] = in.nextInt();
        }
//        int xMax = hash(as, cs);
//        int yMax = hash(bs, ds);
        boolean[] can = new boolean[n];
        Arrays.fill(can, true);
        work(as, bs, cs, ds, can);
        work(bs, as, ds, cs, can);
        int res = 0;
        for (boolean b : can) if (b) res++;
        out.println(res);
    }

    private void work(int[] as, int[] bs, int[] cs, int[] ds, boolean[] can) {
        int n = as.length;
        Eve[] es = new Eve[n * 2];
        for (int i = 0; i < n; i++) {
            es[i * 2] = new Eve(as[i], cs[i], bs[i], i);
            es[i * 2 + 1] = new Eve(as[i], cs[i], ds[i], i);
        }
        Algo.sort(es);
        for (int i = 0; i < n * 2; ) {
            int y = es[i].y;
            int right = -1;
            while (i < n * 2 && es[i].y == y) {
//                Algo.debug(right, es[i]);
                if (es[i].left <= right) can[es[i].id] = can[es[i - 1].id] = false;
                right = Math.max(right, es[i].right);
                i++;
            }
        }
    }

    class Eve implements Comparable<Eve> {
        int left;
        int right;
        int y;
        int id;

        @Override
        public int compareTo(Eve o) {
            if (y != o.y) return y - o.y;
            return left - o.left;
        }

        Eve(int left, int right, int y, int id) {
            this.left = left;
            this.right = right;
            this.y = y;
            this.id = id;
        }

        @Override
        public String toString() {
            return "Eve{" +
                    "left=" + left +
                    ", right=" + right +
                    ", y=" + y +
                    ", id=" + id +
                    '}';
        }
    }

    void run2() {
        int n = in.nextInt();
        int[] as = new int[n];
        int[] bs = new int[n];
        int[] cs = new int[n];
        int[] ds = new int[n];
        for (int i = 0; i < n; i++) {
            as[i] = in.nextInt();
            bs[i] = in.nextInt();
            cs[i] = in.nextInt();
            ds[i] = in.nextInt();
        }
//        int xMax = hash(as, cs);
//        int yMax = hash(bs, ds);
        int xMax = Math.max(Algo.max(as), Algo.max(cs)) + 1;
        int yMax = Math.max(Algo.max(bs), Algo.max(ds)) + 1;
        boolean[] can = new boolean[n];
        Arrays.fill(can, true);
        work(as, bs, cs, ds, xMax, yMax, can);
        work(bs, as, ds, cs, yMax, xMax, can);
        for (int i = 0; i < n; i++) {
            as[i] = xMax - as[i] - 1;
            bs[i] = yMax - bs[i] - 1;
            cs[i] = xMax - cs[i] - 1;
            ds[i] = yMax - ds[i] - 1;
        }
        work(ds, cs, bs, as, yMax, xMax, can);
        work(cs, ds, as, bs, xMax, yMax, can);
        int res = 0;
        for (boolean b : can) if (b) res++;
        out.println(res);
    }

    private void work(int[] as, int[] bs, int[] cs, int[] ds, int xMax, int yMax, boolean[] can) {
        int n = as.length;
        E[] dw = new E[n];
        for (int i = 0; i < n; i++) {
            dw[i] = new E(as[i], cs[i], ds[i], bs[i], i);
        }
        E[] up = dw.clone();
        Algo.sort(dw, new Comparator<E>() {
            @Override
            public int compare(E o1, E o2) {
                return o1.down - o2.down;
            }
        });
        Algo.sort(up, new Comparator<E>() {
            @Override
            public int compare(E o1, E o2) {
                return o1.up - o2.up;
            }
        });
        for (int i = 0, j = 0, k = 0; i < yMax && j < n; i++) {
            while (k < n && dw[k].down < i) k++;
            if (k == n) break;
            if (dw[k].down != i) continue;
            while (j < n && up[j].up < i) j++;
            if (j == n) break;
            if (up[j].up != i) continue;
            Intervals<Integer, Boolean> Int = new Intervals<Integer, Boolean>(-10, xMax + 10, false);
            while (j < n && up[j].up == i) {
                Int.paint(up[j].left, up[j].right + 1, true);
                j++;
            }
            while (k < n && dw[k].down == i) {
                E e = dw[k];
                if (Int.get(e.left) || !Int.map.subMap(e.left, e.right + 1).isEmpty()) can[e.id] = false;
                k++;
            }
        }
    }

    class E {
        int left;
        int right;
        int up;
        int down;
        int id;

        E(int left, int right, int up, int down, int id) {
            this.left = left;
            this.right = right;
            this.up = up;
            this.down = down;
            this.id = id;
        }
    }

    private int hash(int[] as, int[] cs) {
        int n = as.length;
        int[] xs = new int[n * 2];
        for (int i = 0; i < n; i++) {
            xs[i * 2] = as[i];
            xs[i * 2 + 1] = cs[i];
        }
        Algo.sort(xs);
        xs = Algo.unique(xs);
        for (int i = 0; i < n; i++) {
            as[i] = Algo.lowerBound(xs, as[i]);
            cs[i] = Algo.lowerBound(xs, cs[i]);
        }
        return xs.length;
    }


}
