package main;

import com.shu_mj.graph.MinCostFlow;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskH {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        while (t-- != 0) {
            solve();
        }
    }

    private void solve() {
        int n = in.nextInt();
        int k = in.nextInt();
        int[][] iss = in.nextIntMatrix(n, 3);
        int[] xs = new int[n * 2];
        for (int i = 0; i < n; i++) {
            xs[i * 2] = iss[i][0];
            xs[i * 2 + 1] = iss[i][1];
        }
        Arrays.sort(xs);
        xs = Algo.unique(xs);
        MinCostFlow.V[] vs = new MinCostFlow.V[xs.length];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new MinCostFlow.V();
            if (i != 0) vs[i - 1].add(vs[i], MinCostFlow.INF, 0);
        }
        for (int i = 0; i < n; i++) {
            int u = Algo.lowerBound(xs, iss[i][0]);
            int v = Algo.lowerBound(xs, iss[i][1]);
            vs[u].add(vs[v], 1, -iss[i][2]);
        }
        out.println(-MinCostFlow.minCostFlow(vs, vs[0], vs[xs.length - 1], k));
    }

}
