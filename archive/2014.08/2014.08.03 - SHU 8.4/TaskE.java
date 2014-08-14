package main;

import com.shu_mj.graph.Dinic;
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
        while (in.hasNext()) {
            solve();
        }
    }

    private void solve() {
        int m = in.nextInt();
        int n = in.nextInt();
        int[][] mat = in.nextIntMatrix(m, n);
        Dinic.V[] vs = new Dinic.V[n * m + 2];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new Dinic.V();
        }
        Dinic.V s = vs[n * m];
        Dinic.V t = vs[n * m + 1];
        int[] dx = { 0, 0, 1, -1 };
        int[] dy = { 1, -1, 0, 0 };
        int sum = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum += mat[i][j];
                if ((i & 1) == (j & 1)) s.add(vs[i * n + j], mat[i][j]);
                else vs[i * n + j].add(t, mat[i][j]);
                for (int d = 0; d < 4; d++) {
                    int x = i + dx[d];
                    int y = j + dy[d];
                    if (x >= 0 && y >= 0 && x < m && y < n) {
                        if ((i & 1) == (j & 1)) vs[i * n + j].add(vs[x * n + y], Dinic.INF);
                        else vs[x * n + y].add(vs[i * n + j], Dinic.INF);
                    }
                }
            }
        }
        out.println(sum - Dinic.dinic(s, t));
    }

}
