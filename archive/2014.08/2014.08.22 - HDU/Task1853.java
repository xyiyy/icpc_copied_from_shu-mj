package main;

import com.shu_mj.graph.MinCostFlow;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task1853 {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        while (in.hasNext()) {
            solve();
        }
    }

    private void solve() {
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] g = new int[n][n];
        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            int c = in.nextInt();
            if (g[u][v] == 0 || g[u][v] > c) {
                g[u][v] = c;
            }
        }
        MinCostFlow.V[] vs = new MinCostFlow.V[n * 2 + 2];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new MinCostFlow.V();
        }
        MinCostFlow.V s = vs[n * 2];
        MinCostFlow.V t = vs[n * 2 + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j] != 0) {
                    vs[i].add(vs[n + j], 1, g[i][j]);
                }
            }
            s.add(vs[i], 1, 0);
            vs[i + n].add(t, 1, 0);
        }
        int cost = MinCostFlow.minCostFlow(vs, s, t, n);
        if (cost == MinCostFlow.INF) out.println(-1);
        else out.println(cost);
    }
}
