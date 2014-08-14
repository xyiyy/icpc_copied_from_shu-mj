package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskC {
    Scanner in;
    PrintWriter out;
    private int[][][][] dp;
    private int[] ten;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        init();
        while (in.hasNext()) {
            solve();
        }
    }

    private void init() {
        // dp[长度][最高位数][模 13 的余数][是否含 13]
        ten = new int[10];
        for (int i = 0; i < 10; i++) ten[i] = (i == 0 ? 1 : ten[i - 1] * 10 % 13);
        dp = new int[11][10][13][2];
        for (int i = 0; i < 10; i++) dp[1][i][i][0] = 1;
        for (int i = 1; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 13; k++) {
                    for (int l = 0; l < 2; l++) {
                        for (int d = 0; d < 10; d++) {
                            dp[i + 1][d][(k + d * ten[i]) % 13][Math.max(l, d == 1 && j == 3 ? 1 : 0)] += dp[i][j][k][l];
//                            Algo.debug(i + 1, d, (k + d * ten[i]) % 13, Math.max(l, d == 1 && j == 3 ? 1 : 0), i, j, k, l, dp[i][j][k][l], dp[i + 1][d][(k + d * ten[i]) % 13][Math.max(l, d == 1 && j == 3 ? 1 : 0)]);
                        }
                    }
                }
            }
        }
//        int[][][][] dp2 = new int[4][10][13][2];
//        for (int i = 0; i < 1000; i++) {
//            char[] cs = Integer.toString(i).toCharArray();
//            int f = 0;
//            for (int j = 0; j < cs.length - 1; j++) if (cs[j] == '1' && cs[j + 1] == '3') f = 1;
//            dp2[cs.length][cs[0] - '0'][i % 13][f]++;
//            for (int j = cs.length + 1; j < 4; j++) dp2[j][0][i % 13][f]++;
//        }
//        for (int i = 1; i <= 3; i++) {
//            for (int j = 0; j < 10; j++) {
//                for (int k = 0; k < 13; k++) {
//                    for (int l = 0; l < 2; l++) {
//                        if (dp[i][j][k][l] != dp2[i][j][k][l]) {
//                            Algo.debug(i, j, k, l, dp[i][j][k][l], dp2[i][j][k][l]);
//                        }
//                    }
//                }
//            }
//        }
    }

    private void solve() {
        int n = in.nextInt() + 1;
        char[] cs = Integer.toString(n).toCharArray();
        int len = cs.length;
        int m = 0; // 之前模 13 的余数
        boolean f = false; // 是否已经含有 13
        int res = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < cs[i] - '0'; j++) {
                if (f || i > 0 && cs[i - 1] == '1' && j == 3) {
                    res += dp[len - i][j][(13 - m) % 13][0];
                    res += dp[len - i][j][(13 - m) % 13][1];
                } else {
                    res += dp[len - i][j][(13 - m) % 13][1];
                }
            }
            m = (m + (cs[i] - '0') * ten[len - i - 1]) % 13;
            f |= i > 0 && cs[i - 1] == '1' && cs[i] == '3';
        }
        out.println(res);
    }
}
