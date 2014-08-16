package main;

import com.shu_mj.geo.P;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskL {
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
            if (n == -1) break;
            solve(n);
        }
    }

    private void solve(int n) {
        P[] ps = new P[n];
        for (int i = 0; i < n; i++) {
            ps[i] = new P(in.nextInt(), in.nextInt());
        }
        ps = P.convexHull(ps);
        n = ps.length;
        if (n <= 2) {
            out.println("0.00");
            return ;
        }
        double ans = 0;
        for (int i = 0; i < n; i++) {
            int j = (i + 1) % n;
            int k = (j + 1) % n;
            while (j != i) {
                while (P.area(ps[i], ps[j], ps[(k + 1) % n]) > P.area(ps[i], ps[j], ps[k])) k = (k + 1) % n;
                ans = Math.max(ans, P.area(ps[i], ps[j], ps[k]));
                j = (j + 1) % n;
            }
        }
        out.printf("%.2f%n", ans);
    }

}
