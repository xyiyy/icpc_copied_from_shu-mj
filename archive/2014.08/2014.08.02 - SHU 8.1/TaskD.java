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
            int n = in.nextInt();
            if (n < 0) break;
            solve(n);
        }
    }

    private void solve(int n) {
        int[] vs = new int[n];
        int[] cs = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            vs[i] = in.nextInt();
            cs[i] = in.nextInt();
            sum += vs[i] * cs[i];
        }
        int[] dp = new int[sum / 2 + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= sum / 2; j++) {
                if (dp[j] >= 0) dp[j] = cs[i];
                else if (j - vs[i] >= 0) dp[j] = dp[j - vs[i]] - 1;
            }
        }
        int res = 0;
        for (int i = 0; i <= sum / 2; i++) if (dp[i] >= 0) res = i;
        out.println((sum - res) + " " + res);
    }
}
