package main;

import com.shu_mj.math.Num;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskD {
    Scanner in;
    PrintWriter out;
    private int n;
    private boolean[] vis;
    private int[] ans;
    private boolean[] isPrime;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int cs = 1;
        isPrime = Num.primeTable(100, new ArrayList<Integer>());
        while (in.hasNext()) {
            if (cs != 1) out.println();
            out.println("Case " + (cs++) + ":");
            solve();
        }
    }

    private void solve() {
        n = in.nextInt();
        vis = new boolean[n + 1];
        ans = new int[n];
        dfs(1, 0);
    }

    void dfs(int crt, int dep) {
        ans[dep] = crt;
        if (dep == n - 1) {
            if (isPrime[crt + ans[0]]) {
                for (int i = 0; i < n - 1; i++)
                    out.print(ans[i] + " ");
                out.println(ans[n - 1]);
            }
            return ;
        }
        vis[crt] = true;
        for (int i = 1; i <= n; i++) if (!vis[i] && isPrime[crt + i]) {
            dfs(i, dep + 1);
        }
        vis[crt] = false;
    }
}
