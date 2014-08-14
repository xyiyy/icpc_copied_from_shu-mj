package main;

import com.shu_mj.geo.P;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskE {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        while (in.hasNext()) {
            int n = in.nextInt();
            if (n == 0) break;
            solve(n);
        }
    }

    private void solve(int n) {
        C[] cs = new C[n];
        for (int i = 0; i < n; i++) {
            cs[i] = new C(new P(in.nextDouble(), in.nextDouble()), in.nextDouble());
        }


        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; i++) {
            List<C> ps = new ArrayList<C>();
            ps.add(new C(cs[i].c.sub(new P(cs[i].r, 0)), Math.PI));
            ps.add(new C(cs[i].c.sub(new P(cs[i].r, 0)), -Math.PI));
            for (int j = 0; j < n; j++) if (!inner(cs[i], cs[j]) && !inner(cs[j], cs[i])) {
                P[] qs = P.isCC(cs[i].c, cs[i].r, cs[j].c, cs[j].r);
                if (qs.length == 0) continue;
                P p0 = qs[0].sub(cs[i].c);
                P p1 = qs[1].sub(cs[i].c);
                ps.add(new C(qs[0], Math.atan2(p0.y, p0.x)));
                ps.add(new C(qs[1], Math.atan2(p1.y, p1.x)));
            }
            Collections.sort(ps);
//            Algo.debug(ps);
            for (int j = 0; j < ps.size() - 1; j++) {
                for (int d = -1; d <= 1; d += 2) {
                    P cp = ps.get(j).c.sub(cs[i].c).rot((ps.get(j + 1).r - ps.get(j).r) / 2);
                    cp = cp.add(cp.div(cp.abs()).mul(d * P.EPS));
                    P p = cs[i].c.add(cp);
//                    Algo.debug(p);
                    for (int k = n - 1; k >= 0; k--) {
                        if (p.sub(cs[k].c).abs() < cs[k].r) {
                            vis[k] = true;
                            break;
                        }
                    }
//                    Algo.debug(vis);
                }
            }
        }
        int ans = 0;
        for (boolean b : vis) if (b) ans++;
        out.println(ans);
    }

    private boolean cover(C[] cs, int i, P p) {
        for (int j = i + 1; j < cs.length; j++) {
            if (P.add(p.sub(cs[j].c).abs2(), -cs[j].r * cs[j].r) <= 0) return true;
        }
        return false;
    }

    private boolean inner(C[] cs, int i) {
        C c0 = cs[i];
        for (int j = i + 1; j < cs.length; j++) {
            C c1 = cs[j];
            if (inner(c0, c1)) return true;
        }
        return false;
    }

    private boolean inner(C c0, C c1) {
        return P.add(c0.c.sub(c1.c).abs() + c0.r, -c1.r) <= 0;
    }

    class C implements Comparable<C> {
        P c;
        double r;
        C(P c, double r) {
            this.c = c;
            this.r = r;
        }

        @Override
        public int compareTo(C o) {
            if (r != o.r) return r < o.r ? -1 : 1;
            return 0;
        }

        @Override
        public String toString() {
            return "C{" +
                    "c=" + c +
                    ", r=" + r +
                    '}';
        }
    }
}
