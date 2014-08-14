package main;

import com.shu_mj.graph.Dij;
import com.shu_mj.graph.Dinic;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskK {
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
        int[][] map = new int[n][n];
        while (m-- != 0) {
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.nextInt();
            map[a][b] += c;
            map[b][a] += c;
        }
        out.println(stoerWagner(map));
    }

    final int INF = Integer.MAX_VALUE / 4;
    int stoerWagner(int[][] map) {
        int n = map.length;
        boolean[] combine = new boolean[n];
        int ans = INF;
        for (int i = 0; i < n - 1; i++) {
            ans = Math.min(ans, search(map, combine));
            if (ans == 0) return 0;
            combine[t] = true;
            for (int j = 0; j < n; j++) {
                if (!combine[j]) {
                    map[s][j] += map[t][j];
                    map[j][s] += map[j][t];
                }
            }
        }
        return ans;
    }
    int search(int[][] map, boolean[] combine) {
        int n = map.length;
        boolean[] vis = new boolean[n];
        int[] we = new int[n];
        s = t = -1;
        int minCut = INF;
        for (int i = 0; i < n; i++) {
            int maxId = -1;
            for (int j = 0; j < n; j++) {
                if (!combine[j] && !vis[j] && (maxId == -1 || we[j] > we[maxId])) {
                    maxId = j;
                }
            }
            if (maxId == -1) return minCut;
            s = t; t = maxId;
            minCut = we[maxId];
            vis[maxId] = true;
            for (int j = 0; j < n; j++) {
                if (!combine[j] && !vis[j]) {
                    we[j] += map[maxId][j];
                }
            }
        }
        return minCut;
    }
    int s;
    int t;
}
