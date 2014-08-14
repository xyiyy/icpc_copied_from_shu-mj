package main;

import com.shu_mj.geo.P;
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
        while (in.hasNext()) {
            int n = in.nextInt();
            if (n == 0) break;
            solve(n);
        }
    }

    private void solve(int n) {
        P[] ps = new P[n];
        P[] qs = new P[n];
        for (int i = 0; i < n; i++) {
            ps[i] = new P(in.nextDouble(), in.nextDouble());
            qs[i] = new P(in.nextDouble(), in.nextDouble());
        }
        boolean[][] maps = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                maps[i][j] = P.crsSS(ps[i], qs[i], ps[j], qs[j]);
            }
        }
//        Algo.debugTable(maps);
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    maps[i][j] |= maps[i][k] && maps[k][j];
                }
            }
        }
        while (in.hasNext()) {
            int x = in.nextInt();
            int y = in.nextInt();
            if (x == 0 && y == 0) break;
            out.println(maps[x - 1][y - 1] ? "CONNECTED" : "NOT CONNECTED");
        }
    }
}
