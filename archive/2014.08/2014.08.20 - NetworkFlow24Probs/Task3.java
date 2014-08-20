package main;

import com.shu_mj.graph.BipartiteMatching;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task3 {
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
        BipartiteMatching.V[] vs = new BipartiteMatching.V[n * 2];
        Map<BipartiteMatching.V, Integer> index = new HashMap<BipartiteMatching.V, Integer>();
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new BipartiteMatching.V();
            index.put(vs[i], i);
        }
        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            vs[u].connect(vs[v + n]);
        }
        int ans = BipartiteMatching.bipartiteMatching(vs);
        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; i++) if (!vis[i] && vs[i + n].pair == null) {
            int u = i;
            for (;;) {
                out.print((u + 1) + " ");
                vis[u] = true;
                if (vs[u].pair == null) break;
                u = index.get(vs[u].pair) - n;
            }
            out.println();
        }
        out.println(n - ans);
    }
}
