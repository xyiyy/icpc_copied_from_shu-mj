package main;

import com.shu_mj.ds.SegMinC;
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
        int n = in.nextInt();
        int m = in.nextInt();
        int[] s = new int[m];
        int[] t = new int[m];
        for (int i = 0; i < m; i++) {
            s[i] = in.nextInt();
            t[i] = in.nextInt();
        }
        SegMinC seg = new SegMinC(n + 1);
        final int INF = 12341234;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, INF);
        dp[1] = 0;
        seg.update(1, 0, 0);
        for (int i = 0; i < m; i++) {
            int v = seg.query(s[i], t[i] + 1);
            if (v == Integer.MAX_VALUE) continue;
            v = Math.min(dp[t[i]], v + 1);
            dp[t[i]] = v;
            seg.update(t[i], 0, v);
        }
        out.println(dp[n]);
    }


}
