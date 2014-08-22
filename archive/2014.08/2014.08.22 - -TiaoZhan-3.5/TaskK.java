package main;

import com.shu_mj.graph.MinCut;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskK {
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
        int[][] c = new int[n][n];
        for (int i = 0; i < m; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();
            c[u][v] += w;
            c[v][u] += w;
        }
        out.println(MinCut.minCut(c));
    }
}
