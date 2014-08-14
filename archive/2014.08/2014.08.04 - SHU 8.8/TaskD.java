package main;

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
        while (in.hasNext()) {
            String s = in.next();
            if (s.charAt(0) == 'e') break;
            solve(s.toCharArray());
        }
    }

    private void solve(char[] cs) {
        int n = cs.length;
        int[][] dp = new int[n][n];
        for (int l = 2; l <= n; l++) {
            for (int i = 0; i < n; i++) {
                int j = i + l - 1;
                if (j >= n) break;
                if (l == 2) {
                    dp[i][j] = (fit(cs[i], cs[j]) ? 2 : 0);
                } else {
                    if (fit(cs[i], cs[j])) dp[i][j] = dp[i + 1][j - 1] + 2;
                    else dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                    for (int k = i + 1; k <= j; k++) {
                        dp[i][j] = Math.max(dp[i][j], dp[i][k - 1] + dp[k][j]);
                    }
                }
            }
        }
        out.println(dp[0][n - 1]);
    }

    private boolean fit(char a, char b) {
        return a == '(' && b == ')' || a == '[' && b == ']';
    }

}
