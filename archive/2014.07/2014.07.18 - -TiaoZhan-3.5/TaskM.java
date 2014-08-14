package main;

import com.shu_mj.graph.BipartiteMatching;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskM {
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

    void solve() {
        int n = in.nextInt();
        int m = in.nextInt();
        BipartiteMatching.V[] vs = new BipartiteMatching.V[n + m];
        for (int i = 0; i < n + m; i++) {
            vs[i] = new BipartiteMatching.V();
        }
        for (int i = 0; i < n; i++) {
            for (int j : in.nextIntArray(in.nextInt())) {
                vs[i].connect(vs[j - 1 + n]);
            }
        }
        out.println(BipartiteMatching.bipartiteMatching(vs));
    }
}
