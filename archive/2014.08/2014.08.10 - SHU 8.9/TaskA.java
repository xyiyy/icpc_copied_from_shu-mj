package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskA {
    Scanner in;
    PrintWriter out;
    private long[][][] dp;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        init();
        while (t-- != 0)
            solve();
    }

    private void init() {
        // dp[长度][最高位数][是否含 49]
        dp = new long[20][10][2];
        for (int i = 0; i < 10; i++) dp[1][i][0] = 1;
        for (int i = 1; i < 19; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 2; k++) {
                    for (int d = 0; d < 10; d++) {
                        dp[i + 1][d][Math.max(k, d == 4 && j == 9 ? 1 : 0)] += dp[i][j][k];
                    }
                }
            }
        }

    }

    private void solve() {
        long x = in.nextLong();
        char[] cs = Long.toString(x).toCharArray();
        long res = 0;
        int n = cs.length;
        boolean f = false; // 是否已经含 49

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < cs[i] - '0'; j++) {
                if (f || i > 0 && cs[i - 1] == '4' && j == 9) {
                    res += dp[n - i][j][0] + dp[n - i][j][1];
                } else {
                    res += dp[n - i][j][1];
                }
            }
            if (i > 0 && cs[i - 1] == '4' && cs[i] == '9') f = true;
        }
        if (fit(x)) res++;
        out.println(res);
    }

    private boolean fit(long x) {
        char[] cs = Long.toString(x).toCharArray();
        for (int i = 0; i < cs.length - 1; i++) if (cs[i] == '4' && cs[i + 1] == '9') return true;
        return false;
    }
}
