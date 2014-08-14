package main;

import com.shu_mj.ds.SegMaxC;
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
        int cs = 1;
        while (in.hasNext()) {
            out.printf("Case %d:%n", cs++);
            solve();
        }
    }

    private void solve() {
        int n = in.nextInt();
        P[] ps = new P[n];
        for (int i = 0; i < n; i++) {
            ps[i] = new P(in.nextInt(), in.nextInt());
        }
        Arrays.sort(ps);
        int[] is = new int[n];
        for (int i = 0; i < n; i++) is[i] = ps[i].y;
        Arrays.sort(is);
        is = Algo.unique(is);
        for (P p : ps) p.y = Algo.lowerBound(is, p.y);
        SegMaxC seg = new SegMaxC(n);
        for (P p : ps) {
            if (p.y == 0) seg.update(p.y, 0, 1);
            else seg.update(p.y, 0, seg.query(0, p.y) + 1);
        }
        int ans = seg.query(0, n);
        out.printf("My king, at most %d road%s can be built.%n%n", ans, ans == 1 ? "" : "s");
    }
    class P implements Comparable<P> {
        int x;
        int y;

        P(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(P o) {
            return x - o.x;
        }
    }
}
