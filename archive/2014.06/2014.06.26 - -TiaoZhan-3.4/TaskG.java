package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskG {
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
            if (n == 0) break;
            solve(n);
        }
    }

    private void solve(int n) {
        int[] xs = new int[n];
        int[] ys = new int[n];
        for (int i = 0; i < n; i++) {
            xs[i] = in.nextInt();
            ys[i] = in.nextInt();
        }
        int[] dp = new int[1 << n];
        final int INF = 1234123412;
        Arrays.fill(dp, INF);
        dp[0] = 0;
        int[] st = new int[(n * (n - 1) / 2)];
        int[] cs = new int[(n * (n - 1) / 2)];
        for (int i = 0, k = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int x0 = Math.min(xs[i], xs[j]), x1 = Math.max(xs[i], xs[j]);
                int y0 = Math.min(ys[i], ys[j]), y1 = Math.max(ys[i], ys[j]);
                if (x1 == x0) x1++;
                if (y1 == y0) y1++;
                cs[k] =  (x1 - x0) * (y1 - y0);
                int s = 0;
                for (int u = 0; u < n; u++) {
                    int x = xs[u], y = ys[u];
                    if (x >= x0 && x <= x1 && y >= y0 && y <= y1) {
                        s |= 1 << u;
                    }
                }
                st[k] = s;
                k++;
            }
        }
//        Algo.debug(st);
//        Algo.debug(cs);
        for (int i = 0; i < (1 << n); i++) if (dp[i] != INF) {
            for (int j = 0; j < st.length; j++) {
                dp[i | st[j]] = Math.min(dp[i | st[j]], dp[i] + cs[j]);
            }
        }
        /*
        int[] is = new int[n];
        for (int i = 1; i < (1 << n); i++) {
            int m = 0;
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) is[m++] = j;
            }
            for (int u = 0; u < m; u++) {
                for (int v = u; v < m; v++) {
                    int x0 = Math.min(xs[is[u]], xs[is[v]]), x1 = Math.max(xs[is[u]], xs[is[v]]);
                    int y0 = Math.min(ys[is[u]], ys[is[v]]), y1 = Math.max(ys[is[u]], ys[is[v]]);
                    if (x1 == x0) x1++;
                    if (y1 == y0) y1++;
                    int cost = (x1 - x0) * (y1 - y0);
                    int w = 0;
                    for (int j = 0; j < m; j++) {
                        int x = xs[is[j]], y = ys[is[j]];
                        if (!(x >= x0 && x <= x1 && y >= y0 && y <= y1)) w |= 1 << is[j];
                    }
                    dp[i] = Math.min(dp[i], dp[w] + cost);
                }
            }
        }
        */
        out.println(dp[(1 << n) - 1]);
    }
}
