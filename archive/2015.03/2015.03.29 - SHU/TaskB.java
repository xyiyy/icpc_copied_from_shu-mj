package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskB {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        char[][] maps = in.nextCharMap(3);
        char[][] maps2 = new char[3][n];
        int ox = 0, oy = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < n; j++) {
                if (maps[i][j] == 'O') {
                    ox = i;
                    oy = j;
                    maps[i][j] = 'X';
                }
                maps2[i][j] = maps[i][j];
            }
        }
        int res = 0;
        for (int i = 1; i < (1 << 4); i++) {
            for (int x = 0; x < 3; x++) {
                for (int y = Math.max(0, oy - 2); y < Math.min(n, oy + 3); y++) {
                    maps[x][y] = maps2[x][y];
                }
            }
            boolean fit = true;
            for (int d = 0; d < 4; d++) if ((i & (1 << d)) != 0) {
                int tx = ox + dx[d] * 2;
                int ty = oy + dy[d] * 2;
                if (tx >= 0 && tx < 3 && ty >= 0 && ty < n && maps[tx][ty] == '.' && maps[ox + dx[d]][oy + dy[d]] == '.') {
                    maps[tx][ty] = 'X';
                    maps[ox + dx[d]][oy + dy[d]] = 'X';
                } else {
                    fit = false;
                    break;
                }
            }
            if (fit) {
                if (Integer.bitCount(i) % 2 == 1) {
                    res += go(maps, n);
                    res %= M;
                } else {
                    res -= go(maps, n);
                    res %= M;
                }
            }
        }
        out.println((res + M) % M);
    }

    private int go(char[][] maps, int n) {
        int[] m = new int[n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                if (maps[j][i] == 'X') {
                    m[i] |= 1 << j;
                }
            }
        }
        int[][] dp = new int[n + 1][8];
        dp[0][0] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 8; j++) {
                if (dp[i][j] != 0) {
                    int old = j | m[i];
                    for (int k = 1; k < 8; k++) {
                        if ((k & old) == 0 && (k & m[i + 1]) == 0 && (k | old) == 7) {
                            dp[i + 1][k] += dp[i][j];
                            dp[i + 1][k] %= M;
                        }
                    }
                    if (old == 1) {
                        dp[i + 1][0] += dp[i][j];
                        dp[i + 1][0] %= M;
                    }
                    if (old == 4) {
                        dp[i + 1][0] += dp[i][j];
                        dp[i + 1][0] %= M;
                    }
                    if (old == 0 && (m[i + 1] & 1) == 0) {
                        dp[i + 1][1] += dp[i][j];
                        dp[i + 1][1] %= M;
                    }
                    if (old == 0 && (m[i + 1] & 4) == 0) {
                        dp[i + 1][4] += dp[i][j];
                        dp[i + 1][4] %= M;
                    }
                    if (old == 7) {
                        dp[i + 1][0] += dp[i][j];
                        dp[i + 1][0] %= M;
                    }
                }
            }
        }
        return dp[n][0];
    }

    int[] dx = { 0, 0, 1, -1 };
    int[] dy = { 1, -1, 0, 0 };
    int M = (int) (1e9 + 7);
}
