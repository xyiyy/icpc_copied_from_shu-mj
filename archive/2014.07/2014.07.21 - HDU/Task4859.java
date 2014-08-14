package main;

import com.shu_mj.graph.Dinic;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task4859 {
    Scanner in;
    PrintWriter out;
    private int n;
    private int m;

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
        n = in.nextInt() + 2;
        m = in.nextInt() + 2;
        char[][] maps = new char[n][];
        for (int i = 1; i < n - 1; i++) {
            maps[i] = ("D" + in.next() + "D").toCharArray();
        }
        for (int i = 0; i < n; i += n - 1) {
            maps[i] = new char[m];
            Arrays.fill(maps[i], 'D');
        }
        Dinic.V[] vs = new Dinic.V[n * m + 2];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new Dinic.V();
        }
        Dinic.V s = vs[n * m];
        Dinic.V t = vs[n * m + 1];
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        int total = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                boolean b = ((i ^ j) & 1) != 0;
                Dinic.V vij = vs[i * m + j];
                for (int k = 0; k < 2; k++) {
                    int x = i + dx[k];
                    int y = j + dy[k];
                    if (x >= 0 && y >= 0 && x < n && y < m) {
                        total++;
                        Dinic.V vxy = vs[x * m + y];
                        vij.add(vxy, 1);
                        vxy.add(vij, 1);
                    }
                }
                if (maps[i][j] != 'E') {
                    if (b ^ (maps[i][j] == 'D')) s.add(vij, Dinic.INF);
                    else vij.add(t, Dinic.INF);
                }
            }
        }
//        n -= 2;
//        m -= 2;
//        Algo.debug(n * m * 2 + n + m);
        Algo.debug(total);
        out.println(total - Dinic.dinic(s, t));
    }
}
