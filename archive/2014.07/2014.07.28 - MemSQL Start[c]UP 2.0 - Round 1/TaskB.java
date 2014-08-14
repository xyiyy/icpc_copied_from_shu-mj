package main;

import com.shu_mj.geo.P;
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
        int m = in.nextInt();
        double dis = 0;
        P[] ps = null;
        if (n != 0) {
            ps = max(ps, new P[] {new P(n - 1, m), new P(0, 0), new P(n, m), new P(1, 0)});
        }
        if (m != 0) {
            ps = max(ps, new P[] {new P(n, m - 1), new P(0, 0), new P(n, m), new P(0, 1)});
        }
        if (n != 0 && m != 0) {
            ps = max(ps, new P[] {new P(n - 1, m), new P(0, 0), new P(n, m), new P(0, 1)});
            ps = max(ps, new P[] {new P(n, m - 1), new P(0, 0), new P(n, m), new P(1, 0)});
            ps = max(ps, new P[] {new P(0, 0), new P(n, m), new P(0, m), new P(n, 0)});
            ps = max(ps, new P[] {new P(0, 0), new P(n, m), new P(n, 0), new P(0, m)});
        }
        for (P p : ps) {
            out.printf("%.0f %.0f%n", p.x, p.y);
        }
    }

    private P[] max(P[] ps, P[] qs) {
        if (ps == null) return qs;
        if (qs == null) return ps;
        if (!fit(ps)) return qs;
        if (!fit(qs)) return ps;
        if (dis(ps) < dis(qs)) return qs;
        return ps;
    }

    private double dis(P[] ps) {
        return ps[1].sub(ps[0]).abs() + ps[2].sub(ps[1]).abs() + ps[3].sub(ps[2]).abs();
    }

    private boolean fit(P[] ps) {
        for (int i = 0; i < ps.length; i++) {
            for (int j = i + 1; j < ps.length; j++) {
                if (ps[i].compareTo(ps[j]) == 0) return false;
            }
        }
        return true;
    }

}
