package main;

import com.shu_mj.graph.Dinic;
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
        if (m == 0) {
            out.println(1);
            out.println(1);
            return ;
        }
        int[][] map = in.nextIntMatrix(m, 2);
        int[] d = new int[n];
        for (int[] is : map) {
            d[is[0] - 1]++;
            d[is[1] - 1]++;
        }
        int l = 0, r = m * n * n + 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (calc(n, m, map, d, mid) > 0) l = mid + 1;
            else r = mid;
        }
        calc(n, m, map, d, l - 1);
        for (Dinic.V v : vs) v.level = 0;
        dfs(s);
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            cnt += vs[i].level;
        }
        out.println(cnt);
        for (int i = 0; i < n; i++) {
            if (vs[i].level == 1) out.println(i + 1);
        }
    }

    Dinic.V s, t;
    Dinic.V[] vs;
    int calc(int n, int m, int[][] map, int[] d, int x) {
        vs = new Dinic.V[n + 2];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new Dinic.V();
        }
        s = vs[n];
        t = vs[n + 1];
        for (int i = 0; i < n; i++) {
            s.add(vs[i], m * n * n);
            vs[i].add(t, (m - d[i]) * n * n + 2 * x);
        }
        for (int i = 0; i < m; i++) {
            int u = map[i][0] - 1;
            int v = map[i][1] - 1;
            vs[u].add(vs[v], n * n);
            vs[v].add(vs[u], n * n);
        }
        return n * m * n * n - Dinic.dinic(s, t);
    }
    void dfs(Dinic.V v) {
        v.level = 1;
        for (Dinic.E e : v.es) {
            if (e.cap > 0 && e.to.level == 0) {
                dfs(e.to);
            }
        }
    }
}
