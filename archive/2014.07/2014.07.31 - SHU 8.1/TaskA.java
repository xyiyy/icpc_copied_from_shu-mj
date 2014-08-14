package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskA {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int b = in.nextInt();
        int[] is = in.nextIntArray(n);
        int[] dp = new int[1 << n];
        dp[0] = 0;
        for (int i = 1; i < (1 << n); i++) {
            if (Integer.bitCount(i) == 1) dp[i] = is[get(i)];
            else dp[i] = dp[i - (i & -i)] + dp[i & -i];
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < dp.length; i++) if (dp[i] >= b) min = Math.min(min, dp[i]);
        out.println(min - b);
    }

    private int get(int x) {
        return Integer.numberOfTrailingZeros(x);
    }
}
