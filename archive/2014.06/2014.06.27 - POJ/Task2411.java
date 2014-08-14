package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task2411 {
    Scanner in;
    PrintWriter out;
    private int h;
    private int w;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        while (in.hasNext()) {
            int h = in.nextInt(), w = in.nextInt();
            if (h == 0 && w == 0) break;
            solve(h, w);
        }
    }

    private void solve(int h, int w) {
        this.h = h;
        this.w = w;
        boolean[][] adj = new boolean[1 << w][1 << w];
        for (int i = 0; i < (1 << w); i++) {
            for (int j = 0; j < (1 << w); j++) {
                if (adj(i, j)) adj[i][j] = true;
            }
        }
        long[][] dp = new long[h + 1][1 << w];
        dp[0][0] = 1;
        for (int r = 0; r < h; r++) {
            for (int i = 0; i < (1 << w); i++) {
                for (int j = 0; j < (1 << w); j++) {
                    if (adj[i][j]) dp[r + 1][j] += dp[r][i];
                }
            }
        }
        out.println(dp[h][0]);
    }

    private boolean adj(int a, int b) {
        if ((a & b) != 0) return false;
        int ab = a | b;
        for (int i = 0; i < w; ) {
            if ((ab & (1 << i)) != 0) i++;
            else {
                if (i == w - 1 || (ab & (1 << i + 1)) != 0) return false;
                else i += 2;
            }
        }
        return true;
    }
}
