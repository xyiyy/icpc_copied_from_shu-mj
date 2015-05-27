package main;

import com.shu_mj.geo.P;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskC {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            out.printf("Case #%d:%n", i);
            solve();
        }
    }

    private void solve() {
        int n = in.nextInt();
        P[] ps = new P[n];
        for (int i = 0; i < n; i++) {
            ps[i] = new P(in.nextInt(), in.nextInt());
        }
    }

    private void solve2() {
        int n = in.nextInt();
        P[] ps = new P[n];
        for (int i = 0; i < n; i++) {
            ps[i] = new P(in.nextInt(), in.nextInt());
        }
        P[] cs = P.convexHull(ps.clone());
        int[] res = new int[n];
        Set<P> set = new TreeSet<>();
        for (P p : cs) set.add(p);
        for (int i = 0; i < n; i++) {
            if (!set.contains(ps[i])) {
                int d = n - 1;
                for (int s = 0; s < (1 << n); s++) if ((s & (1 << i)) == 0) {
                    int c = n - Integer.bitCount(s);
                    P[] qs = new P[c];
                    for (int t = 0; t < n; t++) {
                        if ((s & (1 << t)) == 0) {
                            qs[--c] = ps[t];
                        }
                    }
                    boolean f = false;
                    for (P p : P.convexHull(qs)) {
                        if (p.equals(ps[i])) {
                            f = true;
                        }
                    }
                    if (f) d = Math.min(d, Integer.bitCount(s));
                }
                res[i] = d;
            }
        }
        for (int i : res) out.println(i);
    }

}
