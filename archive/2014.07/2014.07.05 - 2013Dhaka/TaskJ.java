package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskJ {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            out.printf("Case %d: ", i);
            solve();
        }
    }

    private void solve() {
        char[] cs = in.next().toCharArray();
        int n = cs.length;
        int[][] dp = new int[n][26];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            dp[i][cs[i] - 'a']++;
            if (i != 0) for (int j = 0; j < 26; j++) dp[i][j] += dp[i - 1][j];
        }
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (fit(dp, i, j)) cnt++;
            }
        }
        out.println(cnt);
    }

    private boolean fit(int[][] dp, int u, int v) {
        int cnt = 0;
        for (int i = 0; i < 26; i++) {
            int c = dp[v][i];
            if (u != 0) c -= dp[u - 1][i];
            if (c % 2 == 1) {
                cnt++;
                if (cnt == 2) return false;
            }
        }
        return true;
    }
}
