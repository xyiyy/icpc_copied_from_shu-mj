package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskC {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int m = in.nextInt();
        int n = in.nextInt();
        final int M = (int) 1e8;
        int[] maps = new int[m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                maps[i] |= (in.nextInt() ^ 1) << j;
            }
        }
        int[] st = new int[1 << n];
        int[] rst = new int[1 << n];
        int s = 0;
        for (int i = 0; i < (1 << n); i++) {
            if ((i & (i >> 1)) == 0) {
                st[s++] = i;
            }
            rst[i] = s;
        }
        int[][] dp = new int[2][s];
        dp[0][0] = 1;
        for (int i = 1; i <= m; i++) {
            Arrays.fill(dp[i & 1], 0);
            for (int j = 0; j < s; j++) if ((st[j] & maps[i - 1]) == 0) {
                for (int k = 0; k < rst[j ^ ((1 << n) - 1)]; k++) if ((st[k] & st[j]) == 0) dp[i & 1][j] = (dp[i & 1][j] + dp[(i - 1) & 1][k]) % M;
            }
        }
        int res = 0;
        for (int i = 0; i < s; i++) res = (res + dp[m & 1][i]) % M;
        out.println(res);
    }
}
