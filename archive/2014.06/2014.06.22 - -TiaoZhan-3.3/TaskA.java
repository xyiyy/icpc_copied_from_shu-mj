package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskA {
    Scanner in;
    PrintWriter out;
    private int[] L;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        boolean f = true;
        while (in.hasNext()) {
            if (f) f = false; else out.println();
            solve();
        }
    }

    final int ST_SIZE = 1 << 15;
    double[] vx = new double[ST_SIZE];
    double[] vy = new double[ST_SIZE];
    double[] ang = new double[ST_SIZE];


    private void solve() {
        int n = in.nextInt();
        int c = in.nextInt();
        L = in.nextIntArray(n);
        double[] prv = new double[n];
        Arrays.fill(prv, Math.PI);
        init(1, 0, n);
        while (c-- != 0) {
            int s = in.nextInt();
            double a = Math.toRadians(in.nextDouble());

            change(s, a - prv[s], 1, 0, n);
            prv[s] = a;

            out.printf("%.2f %.2f%n", vx[1], vy[1]);
        }
    }

    private void change(int s, double a, int v, int l, int r) {
        if (s <= l) return ;
        if (s < r) {
            int chl = v * 2, chr = v * 2 + 1;
            int m = (l + r) / 2;
            change(s, a, chl, l, m);
            change(s, a, chr, m, r);
            if (s <= m) ang[v] += a;
            double sin = Math.sin(ang[v]), cos = Math.cos(ang[v]);
            vx[v] = vx[chl] + (cos * vx[chr] - sin * vy[chr]);
            vy[v] = vy[chl] + (sin * vx[chr] + cos * vy[chr]);
        }
    }

    private void init(int k, int l, int r) {
        ang[k] = vx[k] = 0.0;
        if (r - l == 1) {
            vy[k] = L[l];
        } else {
            int chl = k * 2, chr = k * 2 + 1;
            int m = (l + r) / 2;
            init(chl, l, m);
            init(chr, m, r);
            vy[k] = vy[chl] + vy[chr];
        }
    }
}
