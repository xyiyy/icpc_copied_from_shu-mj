package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task1009 {
    Scanner in;
    PrintWriter out;
    private double[] G;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        G = new double[101];
        for (int i = 60; i <= 69; i++) G[i] = 2;
        for (int i = 70; i <= 74; i++) G[i] = 2.5;
        for (int i = 75; i <= 79; i++) G[i] = 3;
        for (int i = 80; i <= 84; i++) G[i] = 3.5;
        for (int i = 85; i <= 100; i++) G[i] = 4;
        while (t-- != 0) {
            solve();
        }
    }

    private void solve() {
        int s = in.nextInt();
        int n = in.nextInt();
        s *= n;
        double[][] dp = new double[n][s + 1];
        double[][] dp2 = new double[n][s + 1];
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                for (int k = 60; k <= 100 && k <= s; k++) {
                    dp[i][k] = G[k];
                    dp2[i][k] = G[k];
                }
                continue;
            }
            for (int j = 0; j <= s; j++) if (dp[i - 1][j] != 0) {
                for (int k = 60; k <= 100 && j + k <= s; k++) {
                    dp[i][j + k] = Math.max(dp[i][j + k], dp[i - 1][j] + G[k]);
                    if (dp2[i][j + k] == 0 || dp2[i][j + k] > dp2[i - 1][j] + G[k]) {
                        dp2[i][j + k] = dp2[i - 1][j] + G[k];
                    }
                }
            }
        }
        out.printf("%.4f %.4f%n", dp2[n - 1][s] / n, dp[n - 1][s] / n);
    }
}
