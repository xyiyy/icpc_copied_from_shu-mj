package main;

import com.shu_mj.graph.MinCostFlow;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task1006 {
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
            out.printf("Case #%d: ", i);
            solve();
        }
    }

    private void solve() {
        int n = in.nextInt();
        int[] is = in.nextIntArray(n);
        int l = 0, r = n + 1;
        while (l < r) {
            int m = (l + r) / 2;
            if (fit(n, is, m)) l = m + 1;
            else r = m;
        }
        out.println(l - 1);
    }

    private boolean fit(int n, int[] is, int m) {
        int[] cs = new int[1000 + 1];
        for (int i : is) cs[i]++;
        int max = Algo.max(is);
        MinCostFlow.V[] vs = new MinCostFlow.V[max + 2];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new MinCostFlow.V();
        }
        MinCostFlow.V s = vs[max];
        MinCostFlow.V t = vs[max + 1];
        for (int i = 1; i <= max; i++) if (cs[i] != 0) {
            s.add(vs[i - 1], cs[i], 0);
        }
        for (int i = 0; i < max; i++) {
            vs[i].add(t, 1, 0);
        }
        for (int i = 0; i < max; i++) {
            vs[i + 1].add(vs[i], MinCostFlow.INF, 1);
        }
        int hit = MinCostFlow.minCostFlow(vs, s, t, m);
        return hit + m <= max;
    }
}
