package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskB {
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
        int[] mask = new int[n];
        for (int i = 0; i < n; i++) {
            char[] cs = in.next().toCharArray();
            for (int j = 0; j < m; j++) {
                mask[i] ^= ((cs[j] == 'P' ? 0 : 1) << j);
            }
        }
        int[] st = new int[1 << m];
        int[] num = new int[1 << m];
        int s = 0;
        for (int i = 0; i < (1 << m); i++) {
            if ((i & (i >> 1)) == 0 && (i & (i >> 2)) == 0) {
                num[s] = Integer.bitCount(i);
                st[s++] = i;
            }
        }
        int[][][] dp = new int[2][s][s];
        Algo.fill(dp[0], -1);
        Algo.fill(dp[1], -1);
        dp[1][0][0] = 0;
        for (int i = 0; i < n; i++) {
            Algo.fill(dp[i & 1], -1);
            for (int j = 0; j < s; j++) if ((st[j] & mask[i]) == 0) {
                for (int k = 0; k < s; k++) if ((st[j] & st[k]) == 0) {
                    for (int l = 0; l < s; l++) if (dp[(i - 1) & 1][k][l] != -1 && (st[j] & st[l]) == 0) {
                        dp[i & 1][j][k] = Math.max(dp[i & 1][j][k], dp[(i - 1) & 1][k][l] + num[j]);
                    }
                }
            }
        }
        int max = 0;
        for (int[] is : dp[(n - 1) & 1]) max = Math.max(max, Algo.max(is));
        out.println(max);
    }
}
