package main;

import com.shu_mj.graph.Dij;
import com.shu_mj.graph.Dinic;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task3605 {
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
            System.gc();
        }
    }

    private void solve() {
        int n = in.nextInt();
        int m = in.nextInt();
        Dinic.V[] vs = new Dinic.V[n + m + 2];
        for (int i = 0; i < vs.length; i++) vs[i] = new Dinic.V();
        Dinic.V s = vs[n + m];
        Dinic.V t = vs[n + m + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (in.nextInt() == 1) {
                    vs[i].add(vs[j + n], 1);
                }
            }
            s.add(vs[i], 1);
        }
        for (int i = 0; i < m; i++) {
            vs[i + n].add(t, in.nextInt());
        }
        out.println(Dinic.dinic(s, t) == n ? "YES" : "NO");
    }
}
