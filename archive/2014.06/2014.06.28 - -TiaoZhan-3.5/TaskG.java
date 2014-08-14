package main;

import com.shu_mj.graph.MinCostFlow;
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
        int m = in.nextInt();
        int[][] mat = in.nextIntMatrix(n, m);
        V[] vs = new V[n + n * m + 2];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new V();
        }
        // 0 ~ n := toy
        // n + (0 ~ n) := shop 0
        // n + (n ~ 2n) := shop 1
        // ...
        // n + ((m - 1)n ~ mn) := shop m - 1
        V s = vs[n + n * m];
        V t = vs[n + n * m + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < n; k++) {
                    vs[i].add(vs[n + j * n + k], 1, mat[i][j] * (k + 1));
                }
            }
        }
        for (int i = 0; i < n; i++) {
            s.add(vs[i], 1, 0);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                vs[n + i * n + j].add(t, 1, 0);
            }
        }
        int ans = MinCostFlow.minCostFlow(vs, s, t, n);
        out.printf("%.6f%n", ans * 1.0 / n);
    }
    class V extends MinCostFlow.V {

    }
}
