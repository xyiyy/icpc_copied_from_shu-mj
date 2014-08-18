package main;

import com.shu_mj.geo.P;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskM {
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
            if (n == 0) break;
            solve(n);
        }
    }
    private void solve(int n) {
        P[] ps = new P[n];
        for (int i = 0; i < n; i++) {
            ps[i] = new P(in.nextInt(), in.nextInt());
        }
        P[] qs = P.convexHullByAngle(ps);
        Map<P, Integer> index = new HashMap<P, Integer>();
        for (int i = 0; i < n; i++) {
            index.put(ps[i], i);
        }
        double allArea = P.area(qs);
        double res = P.area(P.convexHull(Arrays.copyOfRange(ps, 1, n)));
        P[] st = new P[n];
        for (int i = 1; i < qs.length; i++) {
            res = Math.min(res, allArea - work(ps, qs, index, st, i));
        }
        out.printf("%.2f%n", res);
    }

    private double work(P[] ps, P[] qs, Map<P, Integer> index, P[] st, int del) {
        int n = ps.length;
        int b = index.get(qs[del - 1]);
        int e = del + 1 >= qs.length ? n : index.get(qs[del + 1]);
        del = index.get(qs[del]);
        int k = 0;
        for (int i = b;  i <= e; ) {
            if (i == del) {
                i++;
                continue;
            }
            while (k > 1 && st[k - 1].sub(st[k - 2]).det(ps[i % n].sub(st[k - 1])) < P.EPS) k--;
            st[k++] = ps[(i++) % n];
        }
        st[k++] = ps[del];
        return P.area(Arrays.copyOf(st, k));
    }
}
