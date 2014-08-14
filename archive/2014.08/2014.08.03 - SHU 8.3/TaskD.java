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
            solve();
        }
    }

    private void solve() {
        int cash = in.nextInt();
        int n = in.nextInt();
        int[] dp = new int[cash + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            int c = in.nextInt();
            int d = in.nextInt();
            for (int j = 0; j <= cash; j++) {
                if (dp[j] >= 0) dp[j] = c;
                else if (j - d >= 0) dp[j] = dp[j - d] - 1;
            }
        }
        for (int i = cash; i >= 0; i--) {
            if (dp[i] >= 0) {
                out.println(i);
                return ;
            }
        }
    }
}
