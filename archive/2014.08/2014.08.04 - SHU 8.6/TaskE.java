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
        while (in.hasNext())
            solve();
    }

    private void solve() {
        int n = in.nextInt();
        int m = in.nextInt();
        char[][] maps = new char[n][];
        for (int i = 0; i < n; i++) {
            maps[i] = in.next().toCharArray();
        }
        int[] xs = new int[m];
        int[] ys = new int[m];
        int[] xe = new int[m];
        int[] ye = new int[m];
        for (int i = 0; i < m; i++) {
            xs[i] = in.nextInt() - 1;
            ys[i] = in.nextInt() - 1;
            xe[i] = in.nextInt() - 1;
            ye[i] = in.nextInt() - 1;
        }
        int[][] dis = new int[m][m];
        int INF = Integer.MAX_VALUE / 4;
        Algo.fill(dis, INF);
        for (int i = 0; i < m; i++) {
            bfs(maps, dis[i], xs, ys, xe[i], ye[i]);
        }
        int[][] dp = new int[m][1 << m];
        Algo.fill(dp, INF);
        for (int i = 1; i < (1 << m); i++) {
            for (int j = 0; j < m; j++) if ((i & (1 << j)) != 0) {
                if (Integer.bitCount(i) == 1) {
                    dp[j][i] = 0;
                } else {
                    for (int k = 0; k < m; k++) if ((i & (1 << k)) != 0 && k != j && dis[k][j] != INF && dp[k][i ^ (1 << j)] != INF) {
                        dp[j][i] = Math.min(dp[j][i], dp[k][i ^ (1 << j)] + dis[k][j]);
                    }
                }
            }
        }
        int ans = dp[0][(1 << m) - 1];
        for (int i = 0; i < m; i++) ans = Math.min(ans, dp[i][(1 << m) - 1]);
        if (ans >= INF) out.println(-1);
        else out.println(ans);
    }

    final int[] dx = { 0, 0, 1, -1 };
    final int[] dy = { 1, -1, 0, 0 };
    private void bfs(char[][] maps, int[] d, int[] xs, int[] ys, int x, int y) {
        Queue<Integer> que = new LinkedList<Integer>();
        que.add(x * 100 + y);
        int n = maps.length;
        int[][] vis = new int[n][n];
        Algo.fill(vis, -1);
        vis[x][y] = 0;
        while (!que.isEmpty()) {
            int crt = que.poll();
            x = crt / 100;
            y = crt % 100;
            for (int i = 0; i < xs.length; i++) if (xs[i] == x && ys[i] == y) d[i] = vis[x][y];
            for (int i = 0; i < 4; i++) {
                int tx = x + dx[i];
                int ty = y + dy[i];
                if (tx >= 0 && ty >= 0 && tx < n && ty < n && vis[tx][ty] == -1 && maps[tx][ty] != '#') {
                    vis[tx][ty] = vis[x][y] + 1;
                    que.add(tx * 100 + ty);
                }
            }
        }
    }
}
