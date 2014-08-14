package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskE {
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
        boolean[][] like = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            int k = in.nextInt();
            for (int j = 0; j < k; j++) {
                like[i][in.nextInt() - 1] = true;
            }
        }
        if (n > m) {
            out.println(0);
            return ;
        }
        int[][] dp = new int[2][1 << m];
        for (int i = 0; i < n; i++) {
            int comb = (1 << (n - i)) - 1;
            Arrays.fill(dp[i & 1], 0);
            while (comb < (1 << m)) {
                for (int j = 0; j < m; j++) if (like[i][j] && ((comb & (1 << j)) != 0)) {
                    dp[i & 1][comb & ~(1 << j)] += (i == 0 ? 1 : dp[(i - 1) & 1][comb]);
                }
                int x = comb & -comb, y = comb + x;
                comb = ((comb & ~y) / x >> 1) | y;
            }
        }
        out.println(dp[(n - 1) & 1][0]);
    }
}
