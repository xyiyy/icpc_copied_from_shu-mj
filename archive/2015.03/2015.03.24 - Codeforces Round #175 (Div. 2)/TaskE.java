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
        int n = in.nextInt();
        int k = in.nextInt();
        if (n == 1) {
            out.println(k == 0 ? 1 : 0);
            return ;
        }
        long M = (long) (1e9 + 7);
        long[][][] dp = new long[n + 1][n + 1][5];
        dp[2][0][4] = 1;
        dp[2][2][3] = 1;
        for (int i = 2; i < n; i++) {
            for (int j = 0; j <= n; j++) {
                for (int t = 0; t <= 4; t++) {
                    long ways = dp[i][j][t];
                    if (ways != 0) {
                        if (t == 4) {
                            long g = j;
                            long ng = i - 1 - g;
                            dp[i + 1][j + 2][3] += ways;
                            dp[i + 1][j + 2][3] %= M;
                            if (j - 1 >= 0) {
                                dp[i + 1][j - 1][0] += ways * g;
                                dp[i + 1][j - 1][0] %= M;
                            }
                            dp[i + 1][j][0] += ways * ng;
                            dp[i + 1][j][0] %= M;
                            dp[i + 1][j][4] += ways;
                            dp[i + 1][j][4] %= M;
                        } else {
                            dp[i + 1][j][4] += ways;
                            dp[i + 1][j][4] %= M;
                            if ((t & 1) != 0) {
                                dp[i + 1][j][2] += ways;
                                dp[i + 1][j][2] %= M;
                            } else {
                                dp[i + 1][j + 1][2] += ways;
                                dp[i + 1][j + 1][2] %= M;
                                dp[i + 1][j][0] += ways;
                                dp[i + 1][j][0] %= M;
                            }
                            if ((t & 2) != 0) {
                                dp[i + 1][j][1] += ways;
                                dp[i + 1][j][1] %= M;
                            } else {
                                dp[i + 1][j + 1][1] += ways;
                                dp[i + 1][j + 1][1] %= M;
                            }
                            long g = j - ((t & 1) != 0 ? 1 : 0) - ((t & 2) != 0 ? 1 : 0);
                            long ng = i - 2 - g - ((t & 1) != 0 ? 0 : 1);
                            if (j - 1 >= 0) {
                                dp[i + 1][j - 1][0] += ways * g;
                                dp[i + 1][j - 1][0] %= M;
                            }
                            dp[i + 1][j][0] += ways * ng;
                            dp[i + 1][j][0] %= M;
                        }
                    }
                }
            }
        }
        out.println(Algo.sum(dp[n][k]) % M);
    }
}
