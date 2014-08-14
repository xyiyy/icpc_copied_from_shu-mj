package main;

import com.shu_mj.ds.Rational;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.math.BigInteger;
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
        int n = in.nextInt();
        int m = in.nextInt();
        boolean[][] maps = new boolean[n][];
        for (int i = 0; i < n; i++) {
            maps[i] = new boolean[i + 1];
            for (int j = 0; j <= i; j++) {
                maps[i][j] = in.next().charAt(0) == '*';
            }
        }
        Rational[][] dp = new Rational[n + 1][];
        dp[0] = new Rational[1];
        dp[0][0] = Rational.ONE;
        for (int i = 1; i <= n; i++) {
            dp[i] = new Rational[i + 1];
            for (int j = 0; j <= i; j++) {
                dp[i][j] = Rational.ZERO;
                if (j > 0 && maps[i - 1][j - 1]) {
                    dp[i][j] = dp[i][j].add(dp[i - 1][j - 1].div(Rational.TWO));
                }
                if (j < i && maps[i - 1][j]) {
                    dp[i][j] = dp[i][j].add(dp[i - 1][j].div(Rational.TWO));
                }
                if (j > 0 && j < i && !maps[i - 2][j - 1]) {
                    dp[i][j] = dp[i][j].add(dp[i - 2][j - 1]);
                }
            }
        }
        out.println(dp[n][m]);
    }
}
