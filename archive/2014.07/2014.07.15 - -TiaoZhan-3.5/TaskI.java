package main;

import com.shu_mj.graph.Bridge;
import com.shu_mj.graph.Dij;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskI {
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
        if (n == 1) {
            out.println("YES");
            return ;
        }
        Bridge.V[] vs = new Bridge.V[n];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new Bridge.V();
        }
        for (int i = 0; i < m; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            vs[u].connect(vs[v]);
        }
        if (fit(vs)) out.println("YES");
        else out.println("NO");
    }

    private boolean fit(Bridge.V[] vs) {
        Bridge.bridge = new ArrayList<Bridge.E>();
        for (Bridge.V v : vs) {
            for (Bridge.V u : vs) {
                u.num = -1;
                u.count = 0;
            }
            v.num = vs.length;
            Bridge.V u = vs[0].num == -1 ? vs[0] : vs[1];
            Bridge.dfs(u, 0);
            if (u.count > 0) u.count--;
            for (Bridge.V v1 : vs) {
                if (v1.count > 0 || v1.num == -1) return false;
            }
        }
        return true;
    }
}
