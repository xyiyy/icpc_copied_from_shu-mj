package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class USACO {
    /*
    LANG: JAVA
    TASK: game1
     */
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int[] is = in.nextIntArray(n);
        int[][] dp = new int[n][n];
        int[] ss = new int[n + 1];
        for (int i = 1; i <= n; i++) ss[i] = ss[i - 1] + is[i - 1];
        for (int len = 1; len <= n; len++) {
            for (int b = 0; b < n; b++) {
                int e = b + len - 1;
                if (e >= n) break;
                if (b == e) dp[b][e] = is[b];
                else dp[b][e] = ss[e + 1] - ss[b] - Math.min(dp[b + 1][e], dp[b][e - 1]);
            }
        }
        out.println(dp[0][n - 1] + " " + (ss[n] - dp[0][n - 1]));
    }
}
