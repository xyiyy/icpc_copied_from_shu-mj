package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskE {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int mod = (int) (1e9 + 7);
        int n = in.nextInt();
        int m = in.nextInt();
        int g = in.nextInt();
        if (g == 1) {
            if (n == 0) {
                out.println(m == 1 ? 1 : 0);
                return ;
            } else {
                n--;
            }
        }
        int[][] dp = new int[n + 1][m + 1];
        dp[1][0] = 1;
        for (int all = 1; all < n + m; all++) {
            for (int j = Math.max(0, all - n); j <= m && j <= all; j++) {
                int i = all - j;
                if (dp[i][j] != 0) {
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j - 1] + dp[i - 2][j];
                }
            }
        }
        out.println(dp[n][m]);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                out.printf("%4d", dp[i][j]);
            }
            out.println();
        }
    }
}
