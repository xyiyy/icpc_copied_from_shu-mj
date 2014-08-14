package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskA {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int r = in.nextInt();
        int c = in.nextInt();
        int[][] mat = in.nextIntMatrix(r, c);
        int[][] dp = new int[r][c];
        Algo.fill(dp, -1);
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                dfs(mat, dp, i, j);
            }
        }
        int ans = 0;
        for (int[] is : dp) ans = Math.max(ans, Algo.max(is));
        out.println(ans);
    }

    private int dfs(int[][] mat, int[][] dp, int i, int j) {
        if (dp[i][j] != -1) return dp[i][j];
        int res = 0;
        for (int d = 0; d < 4; d++) {
            int x = i + dx[d];
            int y = j + dy[d];
            if (x >= 0 && y >= 0 && x < mat.length && y < mat[0].length && mat[x][y] < mat[i][j]) {
                res = Math.max(res, dfs(mat, dp, x, y));
            }
        }
        return dp[i][j] = res + 1;
    }
    int[] dx = { 0, 0, 1, -1 };
    int[] dy = { 1, -1, 0, 0 };
}
