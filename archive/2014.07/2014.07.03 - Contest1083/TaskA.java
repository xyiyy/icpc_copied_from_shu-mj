package main;

import com.shu_mj.graph.Dij;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskA {
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
        Algo.debug(n, m);
        V[] vs = new V[n];
        for (int i = 0; i < n; i++) vs[i] = new V();
        for (int i = 0; i < m; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            int c = in.nextInt();
            vs[u].add(vs[v], c);
            vs[v].add(vs[u], c);
        }
        int s = in.nextInt();
        int t = in.nextInt();
        Dij.dijkstra(vs[s]);
        if (vs[t].min == Dij.INF) out.println(-1);
        else out.println(vs[t].min);
    }
    class V extends Dij.V {

    }
}
