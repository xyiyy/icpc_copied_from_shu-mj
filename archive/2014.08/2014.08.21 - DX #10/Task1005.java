package main;

import com.shu_mj.graph.Dinic;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task1005 {
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
            out.printf("Case #%d: ", i);
            solve();
        }
    }

    String imp = "So naive!";
    String more = "So young!";
    String uni = "So simple!";
    private void solve() {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] rows = in.nextIntArray(n);
        int[] cols = in.nextIntArray(m);
        if (Algo.sum(rows) != Algo.sum(cols) || edge(rows, cols, n, m, 9)) {
            out.println(imp);
            return ;
        }
        Dinic.V[] vs = new Dinic.V[n + m + 2];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new Dinic.V();
        }
        Dinic.V s = vs[n + m];
        Dinic.V t = vs[n + m + 1];
        for (int i = 0; i < n; i++) {
            s.add(vs[i], rows[i]);
            for (int j = 0; j < m; j++) {
                if (i == 0) vs[j + n].add(t, cols[j]);
                vs[i].add(vs[j + n], 9);
            }
        }
        int ans = Dinic.dinic(s, t);
        if (ans != Algo.sum(rows)) out.println(imp);
        else if (unique(vs, n, m, s, t)) out.println(uni);
        else out.println(more);
    }
    private boolean edge(int[] rows, int[] cols, int n, int m, int k) {
        for (int r : rows) if (r > k * m) return true;
        for (int c : cols) if (c > k * n) return true;
        return false;
    }

    private boolean unique(Dinic.V[] vs, int n, int m, Dinic.V s, Dinic.V t) {
        for (int i = 0; i < n; i++) {
            for (Dinic.V v : vs) v.level = 0;
            if (!dfs(vs[i], null, s, t)) return false;
        }
        return true;
    }

    private boolean dfs(Dinic.V v, Dinic.V f, Dinic.V s, Dinic.V t) {
        v.level = 1;
        for (Dinic.E e : v.es) {
            if (e.to != f && e.to != s && e.to != t && e.cap != 0) {
                if (e.to.level == 1) return false;
                if (!dfs(e.to, v, s, t)) return false;
            }
        }
        v.level = 0;
        return true;
    }
}