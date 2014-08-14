package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskC {
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
        V[] vs = new V[n + 1];
        for (int i = 0; i < n + 1; i++) {
            vs[i] = new V();
        }
        for (int i = 0; i < n; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            vs[u].add(vs[i + 1]);
            vs[i + 1].val = v;
        }
        out.println(dfs(vs[0], n + 1)[m + 1]);
    }

    private int[] dfs(V v, int n) {
        int[] dp = new int[n + 1];
        for (V u : v) {
            int[] is = dfs(u, n);
            for (int i = n; i >= 0; i--) {
                for (int j = 0; j <= i; j++) {
                    dp[i] = Math.max(dp[i], dp[i - j] + is[j]);
                }
            }
        }
        for (int i = n; i > 0; i--) dp[i] = dp[i - 1] + v.val;
        return dp;
    }

    class V extends ArrayList<V> {
        int val;
    }
}
