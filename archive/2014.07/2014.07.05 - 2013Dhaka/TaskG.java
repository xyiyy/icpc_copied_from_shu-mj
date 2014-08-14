package main;

import com.shu_mj.ds.Intervals;
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
        for (int i = 1; i <= t; i++) {
            out.printf("Case %d:%n", i);
            solve();
        }
    }

    private void solve() {
        int n = in.nextInt();
        int m = in.nextInt();
        P[] ps = new P[n];
        for (int i = 0; i < n; i++) {
            ps[i] = new P(in.nextInt(), in.nextInt());
        }
        Intervals<Integer, Integer> IntX = new Intervals<Integer, Integer>(Integer.MIN_VALUE, Integer.MAX_VALUE, -1);
        Algo.sort(ps, new Comparator<P>() {
            @Override
            public int compare(P o1, P o2) {
                return o2.x - o1.x;
            }
        });
        for (P p : ps) {
            IntX.paint(p.x, (p.x + p.y) / 2 + 1, p.x);
        }
        Intervals<Integer, Integer> IntY = new Intervals<Integer, Integer>(Integer.MIN_VALUE, Integer.MAX_VALUE, -1);
        Algo.sort(ps, new Comparator<P>() {
            @Override
            public int compare(P o1, P o2) {
                return o1.y - o2.y;
            }
        });
        for (P p : ps) {
            IntY.paint((p.x + p.y) / 2 + 1, p.y + 1, p.y);
        }
        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int left = IntX.get(x);
            int right = IntY.get(x);
            int ans = 0;
            if (left != -1) ans = Math.max(ans, x - left);
            if (right != -1) ans = Math.max(ans, right - x);
            out.println(ans);
        }
    }
    class P {
        int x;
        int y;

        P(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
