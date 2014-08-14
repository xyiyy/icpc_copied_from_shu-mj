package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskB {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        C[] cs = new C[n];
        for (int i = 0; i < n; i++) {
            cs[i] = new C(in.nextDouble(), in.nextDouble(), in.nextDouble());
        }
        E[] es = new E[n * 2];
        for (int i = 0; i < n; i++) {
            C c = cs[i];
            es[i * 2] = new E(c.x - c.r, i);
            es[i * 2 + 1] = new E(c.x + c.r, i + n);
        }
        Arrays.sort(es);
        TreeSet<E> set = new TreeSet<E>();
        List<Integer> res = new ArrayList<Integer>();
        for (E e : es) {
            int id = e.id % n;
            E crtC = new E(cs[id].y, id);
            if (e.id < n) {
                E up = set.ceiling(crtC);
                E dw = set.lower(crtC);
                if (up != null && inside(cs, id, up.id)) continue;
                if (dw != null && inside(cs, id, dw.id)) continue;
                res.add(id);
                set.add(crtC);
            } else {
                set.remove(crtC);
            }
        }
        Collections.sort(res);
        out.println(res.size());
        boolean f = true;
        if (res.size() != 0) {
            for (int i : res) {
                if (f) f = false; else out.print(' ');
                out.print(i + 1);
            }
            out.println();
        }
    }

    private boolean inside(C[] cs, int a, int b) {
        double dx = cs[a].x - cs[b].x;
        double dy = cs[a].y - cs[b].y;
        return dx * dx + dy * dy <= cs[b].r * cs[b].r;
    }

    class E implements Comparable<E> {
        double x;
        int id;

        E(double x, int id) {
            this.x = x;
            this.id = id;
        }

        @Override
        public int compareTo(E o) {
            if (x != o.x) return x < o.x ? -1 : 1;
            return id - o.id;
        }

        @Override
        public boolean equals(Object o) {
            return compareTo((E)(o)) == 0;
        }

        @Override
        public int hashCode() {
            return 0;
        }
    }
    class C {
        double r;
        double x;
        double y;

        C(double r, double x, double y) {
            this.r = r;
            this.x = x;
            this.y = y;
        }
    }
}
