package main;

import com.shu_mj.geo.P;
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
        int n = in.nextInt();
        int l = in.nextInt();
        P[] ps = new P[n];
        for (int i = 0; i < n; i++) {
            ps[i] = new P(in.nextDouble(), in.nextDouble());
        }
        ps = P.convexHull(ps);
        double res = 0;
        n = ps.length;
        for (int i = 0; i < n; i++) {
            res += ps[(i + 1) % n].sub(ps[i]).abs();
        }
        res += Math.PI * 2 * l;
        out.printf("%.0f%n", res);
    }
}
