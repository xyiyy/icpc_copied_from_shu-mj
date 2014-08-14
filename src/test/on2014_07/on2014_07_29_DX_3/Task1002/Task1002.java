package test.on2014_07.on2014_07_29_DX_3.Task1002;



import com.shu_mj.graph.Dij;
import com.shu_mj.graph.Dinic;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task1002 {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        while (in.hasNext()) solve();
    }

    private void solve() {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int[] rows = in.nextIntArray(n);
        int[] cols = in.nextIntArray(m);
        if (Algo.sum(rows) != Algo.sum(cols) || edge(rows, cols, n, m, k)) {
            out.println("Impossible");
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
                vs[i].add(vs[j + n], k);
            }
        }
        int ans = Dinic.dinic(s, t);
        if (ans != Algo.sum(rows)) out.println("Impossible");
        else {
            Map<Dinic.V, Integer> map = new HashMap<Dinic.V, Integer>();
            for (int i = 0; i < vs.length; i++) map.put(vs[i], i);
            if (unique(vs, n, m, s, t)) {
                out.println("Unique");
                int[][] maps = new int[n][m];
                for (int i = 0; i < n; i++) {
                    for (Dinic.E e : vs[i].es) {
                        int v = map.get(e.to);
                        if (v >= n && v < n + m) {
                            maps[i][v - n] = k - e.cap;
                        }
                    }
                }
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        out.print(maps[i][j]);
                        if (j != m - 1) out.print(' ');
                        else out.println();
                    }
                }
            } else out.println("Not Unique");
        }
//        Algo.debug(ans, Algo.sum(rows), Algo.sum(cols));
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
