package main;

import com.shu_mj.graph.MinCostFlow;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskV {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int k = in.nextInt();
        MinCostFlow.V[] vs = new MinCostFlow.V[n * n * 2];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new MinCostFlow.V();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int x = in.nextInt();
                vs[i * n + j].add(vs[i * n + j + n * n], 1, -x);
                vs[i * n + j].add(vs[i * n + j + n * n], MinCostFlow.INF, 0);
                if (i != n - 1) vs[i * n + j + n * n].add(vs[(i + 1) * n + j], MinCostFlow.INF, 0);
                if (j != n - 1) vs[i * n + j + n * n].add(vs[i * n + j + 1], MinCostFlow.INF, 0);
            }
        }
        out.println(-MinCostFlow.minCostFlow(vs, vs[0], vs[n * n * 2 - 1], k));
    }
}
