package main;

import com.shu_mj.graph.Dinic;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskC {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int f = in.nextInt();
        int d = in.nextInt();
        V[] vs = new V[n];
        V[] us = new V[n];
        V[] fs = new V[f];
        V[] ds = new V[d];
        V s = new V();
        V t = new V();
        for (int i = 0; i < us.length; i++) {
            us[i] = new V();
        }
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new V();
        }
        for (int i = 0; i < ds.length; i++) {
            ds[i] = new V();
        }
        for (int i = 0; i < fs.length; i++) {
            fs[i] = new V();
        }
        for (int i = 0; i < n; i++) {
            vs[i].add(us[i], 1);
        }
        for (int i = 0; i < f; i++) {
            s.add(fs[i], 1);
        }
        for (int i = 0; i < d; i++) {
            ds[i].add(t, 1);
        }
        for (int i = 0; i < n; i++) {
            int a = in.nextInt(), b = in.nextInt();
            for (int j = 0; j < a; j++) {
                int x = in.nextInt() - 1;
                fs[x].add(vs[i], 1);
            }
            for (int j = 0; j < b; j++) {
                int x = in.nextInt() - 1;
                us[i].add(ds[x], 1);
            }
        }
        out.println(Dinic.dinic(s, t));
    }
    class V extends Dinic.V {

    }
}
