package main;

import com.shu_mj.graph.Spfa;
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
        int n = in.nextInt();
        int[] ls = new int[n];
        int[] rs = new int[n];
        int[] ws = new int[n];
        for (int i = 0; i < n; i++) {
            ls[i] = in.nextInt() - 1;
            rs[i] = in.nextInt();
            ws[i] = in.nextInt();
        }
        int lMin = Algo.min(ls);
        int rMax = Algo.max(rs);
        Spfa.V[] vs = new Spfa.V[rMax - lMin + 1];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new Spfa.V();
            if (i != 0) {
                vs[i - 1].add(vs[i], 1);
                vs[i].add(vs[i - 1], 0);
            }
        }
        for (int i = 0; i < n; i++) {
            int l = ls[i] - lMin;
            int r = rs[i] - lMin;
            vs[r].add(vs[l], -ws[i]);
        }
        Spfa.spfa(vs[vs.length - 1]);
        out.println(-vs[0].min);
    }
}
