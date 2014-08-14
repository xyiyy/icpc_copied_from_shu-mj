package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskA {
    Scanner in;
    PrintWriter out;
    private int[][] mat;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        while (t-- != 0)
            solve();
    }

    private void solve() {
        int  n = in.nextInt();
        mat = in.nextIntMatrix(n, n);
        boolean[] dp = new boolean[1 << n];
        dp[(1 << n) - 1] = true;
        for (int i = (1 << n) - 1; i > 0; i--) {
            if (dp[i]) work(dp, i, n);
//            Algo.debug(dp);
        }
        List<Integer> ans = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            if (dp[1 << i]) ans.add(i);
        }
        if (ans.size() == 0) {
            out.println(0);
        } else {
            for (int i = 0; i < ans.size(); i++) {
                if (i != 0) out.print(' ');
                out.print(ans.get(i) + 1);
            }
            out.println();
        }
    }

    private void work(boolean[] dp, int x, int n) {
        for (int i = 0; i < n; i++) if ((x & (1 << i)) != 0) {
            int sum = 0;
            for (int j = 0; j < n; j++) if ((x & (1 << j)) != 0) sum += mat[i][j];
            if (sum > 0) dp[x & ~(1 << i)] = true;
        }
    }
}
