package main;

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
            int n = in.nextInt();
            int m = in.nextInt();
            int p = in.nextInt();
            int a = in.nextInt();
            int b = in.nextInt();
            if (n == 0 && m == 0 && p == 0 && a == 0 && b == 0)
                break;
            solve(n, m, p, a, b);
        }
    }

    private void solve(int n, int m, int p, int a, int b) {
        int[] ts = in.nextIntArray(n);
        int[][] maps = new int[m][m];
        Algo.fill(maps, -1);
        for (int i = 0; i < p; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            int c = in.nextInt();
            if (maps[u][v] == -1 || maps[u][v] > c) {
                maps[u][v] = maps[v][u] = c;
            }
        }
        double[][] dp = new double[1 << n][m];
        final double INF = 1e100;
        Algo.fill(dp, INF);
        dp[(1 << n) - 1][a - 1] = 0;
        for (int S = (1 << n) - 1; S >= 0; S--) {
            for (int v = 0; v < m; v++) {
                for (int i = 0; i < n; i++) {
                    if ((S & (1 << i)) != 0) {
                        for (int u = 0; u < m; u++) {
                            if (maps[u][v] >= 0) {
                                dp[S & ~(1 << i)][u] = Math.min(dp[S & ~(1 << i)][u], dp[S][v] +
                                        (double) maps[u][v] / ts[i]);
                            }
                        }
                    }
                }
            }
        }
        double res = INF;
        for (int i = 0; i < (1 << n) - 1; i++) {
            res = Math.min(res, dp[i][b - 1]);
        }
        if (res == INF) {
            out.println("Impossible");
        } else {
            out.printf("%.3f%n", res);
        }
    }
}
