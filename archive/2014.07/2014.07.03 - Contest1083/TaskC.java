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
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            out.printf("Case %d:%n", i);
            solve();
        }
    }

    private void solve() {
        int[][] iss = new int[9][];
        for (int i = 0; i < 9; i++) {
            iss[i] = new int[i + 1];
            for (int j = 0; j <= i; j++) {
                iss[i][j] = in.nextInt() - 1;
            }
        }
        boolean[] vis = new boolean[9];
        if (dfs(vis, iss, 0, 0)) out.println("Possible");
        else out.println("Impossible");
    }

    private boolean dfs(boolean[] vis, int[][] iss, int r, int c) {
        if (r == 8) {
            return true;
        } else {
            vis[iss[r][c]] = true;
            if (!vis[iss[r + 1][c]] && dfs(vis, iss, r + 1, c)) return true;
            if (!vis[iss[r + 1][c + 1]] && dfs(vis, iss, r + 1, c + 1)) return true;
            vis[iss[r][c]] = false;
            return false;
        }
    }
}
