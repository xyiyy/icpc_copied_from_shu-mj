package main;

import com.shu_mj.ds.BIT;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskD {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        char[] cs = in.next().toCharArray();
        int n = cs.length;
        int[][] bs = new int[26][n + 1];
        Algo.fill(bs, -1);
        for (int i = 0; i < n; i++) {
            bs[cs[i] - 'a'][i] = i;
        }
        for (int i = 0; i < 26; i++) {
            for (int j = 1; j <= n; j++) {
                if (bs[i][j] != j) bs[i][j] = bs[i][j - 1];
            }
        }
        int[][] dp = new int[n + 1][51];
        int[][] pre = new int[n + 1][51];
        Algo.fill(dp, -1);
        dp[0][0] = n;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= 50; j++) {
                dp[i + 1][j] = dp[i][j];
                pre[i + 1][j] = 0;
            }
            for (int j = 0; j < 50; j++) {
                if (dp[i][j] > i + 1 && bs[cs[i] - 'a'][dp[i][j] - 1] > i) {
                    int res = bs[cs[i] - 'a'][dp[i][j] - 1];
                    if (dp[i + 1][j + 1] < res) {
                        dp[i + 1][j + 1] = res;
                        pre[i + 1][j + 1] = 1;
                    }
//                    Algo.debug(i, j, dp[i][j], res);
                }
            }
        }
        int ri = 0, rj = 0;
        outer: for (int j = 50; j >= 0; j--) {
            for (int i = 0; i < n; i++) {
                if (dp[i][j] > -1) {
                    ri = i;
                    rj = j;
                    break outer;
                }
            }
        }
        if (rj < 50 && dp[ri][rj] == ri) {
            for (int i = 0; i <= n; i++) {
                if (dp[i][rj] > i) {
                    ri = i;
                    break;
                }
            }
        }
        char[] res = new char[rj];
        for (int i = ri, j = rj; i > 0; i--) {
            if (pre[i][j] == 1) {
                res[j - 1] = cs[i - 1];
                j--;
            }
        }
        out.print(res);
        if (rj < 50 && dp[ri][rj] > ri) {
            out.print(cs[ri]);
        }
        Algo.reverse(res);
        out.println(res);
    }
}
