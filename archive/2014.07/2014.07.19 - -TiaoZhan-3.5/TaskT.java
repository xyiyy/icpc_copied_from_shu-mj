package main;

import com.shu_mj.graph.MinCostFlow;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskT {
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
            int n = in.nextInt();
            int m = in.nextInt();
            if (n == 0 && m == 0) break;
            out.printf("Instance #%d: ", cs++);
            solve(n, m);
        }
    }

    private void solve(int n, int m) {
        MinCostFlow.V[] vs = new MinCostFlow.V[n];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new MinCostFlow.V();
        }
        for (int i = 0; i < m; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            int c = in.nextInt();
            vs[u].add(vs[v], 1, c);
        }
        int ans = MinCostFlow.minCostFlow(vs, vs[0], vs[n - 1], 2);
        if (ans == -1) out.println("Not possible");
        else out.println(ans);
    }
}
