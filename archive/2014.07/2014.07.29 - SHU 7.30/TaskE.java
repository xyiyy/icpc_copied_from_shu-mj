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
        while (in.hasNext()) {
            int n = in.nextInt();
            int m = in.nextInt();
            if (n == 0 && m == 0) break;
            solve(n, m);
        }
    }

    private void solve(int n, int m) {
        int[] vs = in.nextIntArray(n);
        int[] cs = in.nextIntArray(n);
        int[] dp = new int[m + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= m; j++) {
                if (dp[j] >= 0) dp[j] = cs[i];
                else if (j - vs[i] >= 0) dp[j] = dp[j - vs[i]] - 1;
            }
        }
        int cnt = 0;
        for (int i : dp) if (i >= 0) cnt++;
        out.println(cnt - 1);
    }
}
