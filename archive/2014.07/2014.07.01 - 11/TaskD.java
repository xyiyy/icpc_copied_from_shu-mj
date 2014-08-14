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
        int i = 1;
        while (in.hasNext()) {
            out.print("Case " + i + ":");
            solve();
            i++;
        }
    }

    private void solve() {
        int n = in.nextInt();
        int c = in.nextInt();
        int[] vs = in.nextIntArray(n);
        int[] ws = in.nextIntArray(n);
        int[] dp = new int[c + 1];
        for (int i = 0; i < n; i++) {
            for (int j = c; j >= 0; j--) {
                if (j - ws[i] >= 0) dp[j] = Math.max(dp[j], dp[j - ws[i]] + vs[i]);
            }
        }
        out.println(Algo.max(dp));
    }
}
