package main;

import com.shu_mj.graph.Dinic;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskD {
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
        V s = new V();
        V t = new V();
        V[] vs = new V[n];
        for (int i = 0; i < n; i++) {
            vs[i] = new V();
            int a = in.nextInt();
            int b = in.nextInt();
            s.add(vs[i], a);
            vs[i].add(t, b);
        }
        for (int i = 0; i < m; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            int w = in.nextInt();
            vs[a].add(vs[b], w);
            vs[b].add(vs[a], w);
        }
        out.println(Dinic.dinic(s, t));
    }
    class V extends Dinic.V {

    }
}
