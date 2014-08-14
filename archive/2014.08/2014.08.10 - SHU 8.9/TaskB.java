package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskB {
    Scanner in;
    PrintWriter out;
    private int[][][] dp;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        init();
        while (in.hasNext()) {
            int n = in.nextInt();
            int m = in.nextInt();
            if (n == 0 && m == 0) break;
            solve(n, m);
        }
    }

    private void init() {
        // dp[长度][最高位数][是否含 4 或 62]
        dp = new int[10][10][2];
        for (int i = 0; i < 10; i++) dp[1][i][i == 4 ? 1 : 0] = 1;
        for (int i = 1; i < 9; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 2; k++) {
                    for (int d = 0; d < 10; d++) {
                        dp[i + 1][d][Math.max(k, d == 4 || d == 6 && j == 2 ? 1 : 0)] += dp[i][j][k];
                    }
                }
            }
        }
    }

    private void solve(int n, int m) {
        out.println(calc(m + 1) - calc(n));
    }

    private int calc(int n) {
        char[] cs = Integer.toString(n).toCharArray();
        int len = cs.length;
        boolean f = false; // 是否已经含有 4 或 62
        int res = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < cs[i] - '0'; j++) {
                if (f || j == 4 || i > 0 && cs[i - 1] == '6' && j == 2) continue;
                res += dp[len - i][j][0];
            }
            if (cs[i] == '4' || i > 0 && cs[i - 1] == '6' && cs[i] == '2') f = true;
        }
        return res;
    }
}
