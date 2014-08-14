package main;

import com.shu_mj.graph.BipartiteMatching;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskQ {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int cs = 1;
        while (in.hasNext()) {
            int g = in.nextInt();
            int b = in.nextInt();
            int m = in.nextInt();
            if (g == 0 && b == 0 && m == 0) break;
            out.printf("Case %d: ", cs++);
            solve(g, b, m);
        }
    }

    private void solve(int g, int b, int m) {
        BipartiteMatching.V[] vs = new BipartiteMatching.V[g + b];
        boolean[][] maps = new boolean[g][b];
        for (int i = 0; i < m; i++) {
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;
            maps[x][y] = true;
        }
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new BipartiteMatching.V();
        }
        for (int i = 0; i < g; i++) {
            for (int j = 0; j < b; j++) {
                if (!maps[i][j]) {
                    vs[i].connect(vs[j + g]);
                }
            }
        }
        out.println((g + b - BipartiteMatching.bipartiteMatching(vs)));
    }
}
