package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskF {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    final int M = (int) (1e8);
    void run() {
        int n = in.nextInt();
        int m = in.nextInt();
        boolean[][] maps = new boolean[n][m];
        int[] mask = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                maps[i][j] = in.nextInt() == 1;
                if (!maps[i][j]) mask[i] |= 1 << j;
            }
        }
        int[][] dp = new int[2][1 << m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i & 1], 0);
            for (int j = 0; j < (1 << m); j++) if ((mask[i] & j) == 0 && fit(j)) {
                if (i == 0) {
                    dp[i & 1][j] = 1;
                } else {
                    for (int k = 0; k < (1 << m); k++) if (fit(k) && fit(j, k)) {
                        dp[i & 1][j] += dp[(i - 1) & 1][k];
                        dp[i & 1][j] %= M;
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < (1 << m); i++) {
            ans += dp[(n - 1) & 1][i];
            ans %= M;
        }
        out.println(ans);
    }

    private boolean fit(int x, int y) {
        return (x & y) == 0;
    }

    private boolean fit(int x) {
        return (x & (x << 1)) == 0;
    }

}
