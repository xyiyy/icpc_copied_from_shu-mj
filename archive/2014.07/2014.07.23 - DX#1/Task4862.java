package main;

import com.shu_mj.graph.MinCostFlow;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task4862 {
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
            out.printf("Case %d : ", i);
            solve();
        }
    }

    private void solve() {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        char[][] maps = new char[n][];
        for (int i = 0; i < n; i++) {
            maps[i] = in.next().toCharArray();
        }
        MinCostFlow.V[] vs = new MinCostFlow.V[n * m * 2+ 3];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new MinCostFlow.V();
        }
        MinCostFlow.V s = vs[n * m * 2];
        MinCostFlow.V t = vs[n * m * 2 + 1];
        MinCostFlow.V sk = vs[n * m * 2 + 2];
        s.add(sk, k, 0);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                MinCostFlow.V x = vs[i * m + j];
                MinCostFlow.V y = vs[m * n + i * m + j];
                s.add(x, 1, 0);
                y.add(t, 1, 0);
                sk.add(y, 1, 0);
                for (int u = i + 1; u < n; u++) {
                    x.add(vs[m * n + u * m + j], 1, u - i - 1 - (maps[i][j] == maps[u][j] ? maps[i][j] - '0' : 0));
                }
                for (int v = j + 1; v < m; v++) {
                    x.add(vs[m * n + i * m + v], 1, v - j - 1 - (maps[i][j] == maps[i][v] ? maps[i][j] - '0' : 0));
                }
            }
        }
        int cost = MinCostFlow.minCostFlow(vs, s, t, n * m);
        if (cost == MinCostFlow.INF) out.println(-1);
        else out.println(-cost);
    }
}
