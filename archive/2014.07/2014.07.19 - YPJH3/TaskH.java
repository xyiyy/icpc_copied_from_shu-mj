package main;

import com.shu_mj.graph.BipartiteMatching;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskH {
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
            out.printf("Case %d: ", i);
            solve();
        }
    }

    private void solve() {
        int m = in.nextInt();
        int n = in.nextInt();
        char[][] maps = new char[m][];
        for (int i = 0; i < m; i++) {
            maps[i] = in.next().toCharArray();
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) if (maps[i][j] == '1') {
                ans = Math.max(ans, calc(maps, m, n, i, j, ans));
                System.gc();
            }
        }
        out.println(ans);
    }
    int calc(char[][] maps, int m, int n, int u, int v, int ans) {
        int left = 0, right = 0;
        for (int i = 0; i < m; i++) if (maps[i][v] == '1') left++;
        for (int j = 0; j < n; j++) if (maps[u][j] == '1') right++;
        if (left + right <= ans) return ans;
        BipartiteMatching.V[] vs = new BipartiteMatching.V[m + n];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new BipartiteMatching.V();
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) if (maps[i][v] == '1' && maps[u][j] == '1') {
                if (maps[i][j] == '0') {
                    vs[i].connect(vs[j + m]);
                }
            }
        }
        return left + right - BipartiteMatching.bipartiteMatching(vs);
    }
}
