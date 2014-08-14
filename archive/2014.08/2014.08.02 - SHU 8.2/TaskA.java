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
        int N = 32768;
        long[] dp = new long[N + 1];
        dp[0] = 1;
        for (int i = 1; i <= 3; i++) {
            for (int j = i; j <= N; j++) {
                dp[j] += dp[j - i];
            }
        }
        while (in.hasNext()) {
            out.println(dp[in.nextInt()]);
        }
    }
}
