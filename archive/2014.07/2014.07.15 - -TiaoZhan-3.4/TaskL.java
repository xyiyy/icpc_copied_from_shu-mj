package main;

import com.shu_mj.graph.Dij;
import com.shu_mj.graph.Spfa;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskL {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int m = in.nextInt();
        int e = in.nextInt() + 1;
        int[] as = new int[n];
        int[] bs = new int[n];
        int[] xs = new int[n * 2 + 2];
        int[] ws = new int[n];
        for (int i = 0; i < n; i++) {
            as[i] = in.nextInt();
            bs[i] = in.nextInt() + 1;
            ws[i] = in.nextInt();
            xs[i * 2] = as[i];
            xs[i * 2 + 1] = bs[i];
        }
        xs[n * 2] = m;
        xs[n * 2 + 1] = e;
        Arrays.sort(xs);
        xs = Algo.unique(xs);
        Spfa.V[] vs = new Spfa.V[xs.length];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new Spfa.V();
            if (i != 0) vs[i].add(vs[i - 1], 0);
        }
        for (int i = 0; i < n; i++) {
            int a = Algo.lowerBound(xs, as[i]);
            int b = Algo.lowerBound(xs, bs[i]);
            vs[a].add(vs[b], ws[i]);
        }
        Spfa.spfa(vs[0]);
        if (vs[xs.length - 1].min == Spfa.INF) out.println(-1);
        else out.println(vs[xs.length - 1].min);
    }
}
