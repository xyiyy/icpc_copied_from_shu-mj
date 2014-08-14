package main;

import com.shu_mj.ds.Hash;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task3942 {
    Scanner in;
    PrintWriter out;
    private int M;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int cs = 1;
        while (in.hasNext()) {
            out.print("Case " + cs + ": ");
            solve();
            cs++;
        }
    }

    private void solve() {
        char[] cs = in.next().toCharArray();
        int n = in.nextInt();
        Set<Long> set = new HashSet<Long>();
        int len = 0;
        for (int i = 0; i < n; i++) {
            String s = in.next();
            len = Math.max(len, s.length());
            set.add(Hash.getHash(s.toCharArray()));
        }
        n = cs.length;
        Hash H = new Hash(n);
        long[] hs = H.build(cs);
        int[] dp = new int[n + 1];
        dp[n] = 1;
        M = 20071027;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 1; j <= len; j++) {
                if (i + j > n) break;
                if (set.contains(H.get(hs, i, i + j))) {
                    dp[i] += dp[i + j];
                    if (dp[i] >= M) dp[i] -= M;
                }
            }
        }
        out.println(dp[0]);
    }
}
