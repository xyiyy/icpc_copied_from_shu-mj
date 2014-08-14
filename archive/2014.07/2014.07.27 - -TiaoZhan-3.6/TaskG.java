package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskG {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        while (t-- != 0)
            solve();
    }

    private void solve() {
        int n = in.nextInt();
        P[] ps = new P[n];
        for (int i = 0; i < n; i++) {
            ps[i] = new P(in.nextInt(), in.nextInt());
        }
        int sum = 0;
        Algo.sort(ps, new Comparator<P>() {
            @Override
            public int compare(P o1, P o2) {
                if (o1.x != o2.x) return o1.x - o2.x;
                return o1.y - o2.y;
            }
        });
        TreeSet<L> set = new TreeSet<L>();
        for (int i = 0; i < n; ) {
            int x = ps[i].x;
            P lastP = null;
            while (i < n && ps[i].x == x) {
                if (lastP == null) lastP = ps[i];
                else {
                    ps[i].ls[1] = lastP;
                    lastP.ls[1] = ps[i];
                    if (ps[i].y == lastP.y) {im(); return ;}
                    set.add(new L(x, lastP.y, ps[i].y));
                    sum += ps[i].y - lastP.y;
                    lastP = null;
                }
                i++;
            }
            if (lastP != null) {im(); return ;}
        }
        Algo.sort(ps, new Comparator<P>() {
            @Override
            public int compare(P o1, P o2) {
                if (o1.y != o2.y) return o1.y - o2.y;
                return o1.x - o2.x;
            }
        });
        for (int i = 0; i < n; ) {
            int y = ps[i].y;
            P lastP = null;
            while (i < n && ps[i].y == y) {
                if (lastP == null) lastP = ps[i];
                else {
                    ps[i].ls[0] = lastP;
                    lastP.ls[0] = ps[i];
                    if (ps[i].x == lastP.x) {im(); return ;}
                    sum += ps[i].x - lastP.x;
                    if (crash(set, ps[i], lastP)) {im(); return ;}
                    lastP = null;
                }
                i++;
            }
            if (lastP != null) {im(); return ;}
        }
        P crt = ps[0];
        int cnt = 0;
        do {
            cnt++;
            crt = crt.ls[cnt % 2];
        } while (crt != ps[0]);
        if (cnt != n) {im(); return ;}
        out.println(sum);
    }
    class L implements Comparable<L> {
        int x;
        int y0;
        int y1;

        L(int x, int y0, int y1) {
            this.x = x;
            this.y0 = y0;
            this.y1 = y1;
        }

        @Override
        public int compareTo(L o) {
            return x - o.x;
        }
    }

    private boolean crash(TreeSet<L> ps, P p, P q) {
        for (L l : ps.subSet(new L(q.x, 0, 0), new L(p.x, 0, 0))) {
            if (l.y0 < p.y && l.y1 > p.y) return true;
        }
        return false;
    }

    void im() {
        out.println("-1");
    }
    class P {
        int x;
        int y;
        P[] ls;

        P(int x, int y) {
            this.x = x;
            this.y = y;
            ls = new P[2];
        }
    }
}
