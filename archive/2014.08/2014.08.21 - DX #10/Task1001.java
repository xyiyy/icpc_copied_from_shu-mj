package main;

import com.shu_mj.graph.Dinic;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task1001 {
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

    private void solve() {
        int n = in.nextInt();
        int m = in.nextInt();
        Dinic.V[] vs = new Dinic.V[n + m + 2];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new Dinic.V();
        }
        Dinic.V s = vs[n + m];
        Dinic.V t = vs[n + m + 1];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            sum += x;
            s.add(vs[i], x);
        }
        for (int j = 0; j < m; j++) {
            vs[j + n].add(t, in.nextInt());
        }
        for (int i = 0; i < n; i++) {
            int l = in.nextInt();
            for (int j : in.nextIntArray(l)) {
                vs[i].add(vs[j + n], Dinic.INF);
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (in.nextInt() == 1) {
                    vs[i + n].add(vs[j + n], Dinic.INF);
                }
            }
        }
        out.println(sum - Dinic.dinic(s, t));
    }
 }
