package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task1863 {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        while (in.hasNext()) run();
    }

    void run() {
        int n = in.nextInt();
        int[] ps = new int[n];
        int[] hs = new int[n];
        in.nextIntArray(n, ps, hs);
        int[] dp = new int[n + 1];
        Arrays.fill(dp, n);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            int r = i;
            for (int j = i; r < n; r++) {
                if (ps[r] >= ps[j] + hs[j]) {
                    break;
                }
                if (ps[r] + hs[r] >= ps[j] + hs[j]) j = r;
            }
            for (int j = i + 1; j <= r; j++) dp[j] = Math.min(dp[j], dp[i] + 1);
//            Algo.debug(dp);
            int l = i;
            for (int j = i; l >= 0; l--) {
                if (ps[l] <= ps[j] - hs[j]) {
                    break;
                }
                if (ps[l] - hs[l] <= ps[j] - hs[j]) j = l;
            }
            dp[i + 1] = Math.min(dp[i + 1], dp[l + 1] + 1);
//            Algo.debug(dp);
        }
        out.println(dp[n]);
    }
}
