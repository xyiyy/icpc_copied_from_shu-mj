package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskI {
    Scanner in;
    PrintWriter out;
    private int[][] dp;
    private V[] vs;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int m = in.nextInt();
        vs = new V[n];
        for (int i = 0; i < n; i++) vs[i] = new V(i);
        for (int i = 0; i < m; i++) {
            int f = in.nextInt() - 1;
            int t = in.nextInt() - 1;
            int pre = in.nextInt() - 1;
            int p = in.nextInt();
            int r = in.nextInt();
            vs[f].add(vs[t], pre, p, r);
        }
        dp = new int[1 << n][n];
        Algo.fill(dp, INF);
        dfs(1, 0, 0);
        int res = INF;
        for (int i = 0; i < dp.length; i++)  res = Math.min(res, dp[i][n - 1]);
        out.println(res == INF ? "impossible" : res);
    }

    private void dfs(int st, int crt, int cost) {
//        Algo.debug(Integer.toBinaryString(st), crt, cost);
        if (dp[st][crt] > cost) dp[st][crt] = cost;
        else return ;
        for (E e : vs[crt].es) {
            int c = e.r;
            if ((st & (1 << e.pre)) != 0) c = e.p;
            dfs(st | (1 << e.to.id), e.to.id, cost + c);
        }
    }

    class E {
        V to;
        int pre;
        int p;
        int r;

        E(V to, int pre, int p, int r) {
            this.to = to;
            this.pre = pre;
            this.p = p;
            this.r = r;
        }
    }

    final int INF = 12341234;
    class V {
        List<E> es = new ArrayList<E>();
        int id;

        V(int id) {
            this.id = id;
        }

        void add(V to, int pre, int p, int r) {
            es.add(new E(to, pre, p, r));
        }
    }
}
