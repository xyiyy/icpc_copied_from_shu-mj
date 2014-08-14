package main;

import com.shu_mj.graph.BipartiteMatching;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskP {
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
            int m = in.nextInt();
            if (n == 0 && m == 0) break;
            solve(n, m);
        }
    }

    private void solve(int n, int m) {
        boolean[] vis = new boolean[1 << n];
        for (int i = 0; i < m; i++) {
            String s = in.next();
            int id = s.indexOf('*');
            if (id == -1) vis[Integer.parseInt(s, 2)] = true;
            else {
                String l = s.substring(0, id);
                String r = s.substring(id + 1, n);
                vis[Integer.parseInt(l + "0" + r, 2)] = true;
                vis[Integer.parseInt(l + "1" + r, 2)] = true;
            }
        }
        int cnt = 0;
        BipartiteMatching.V[] vs = new BipartiteMatching.V[1 << n];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new BipartiteMatching.V();
        }
        for (int i = 0; i < (1 << n); i++) if (vis[i]) {
            cnt++;
            for (int j = 0; j < n; j++) if (vis[i ^ (1 << j)]) {
                vs[i].connect(vs[i ^ (1 << j)]);
            }
        }
        out.println(cnt - BipartiteMatching.bipartiteMatching(vs));
    }
}
