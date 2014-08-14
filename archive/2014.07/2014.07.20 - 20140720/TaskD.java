package main;

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
        int cs = 1;
        while (in.hasNext()) {
            int n = in.nextInt();
            int q = in.nextInt();
            if (n == 0 && q == 0) break;
            out.printf("Case %d:%n", cs++);
            solve(n, q);
        }
    }

    private void solve(int n, int q) {
        double v = in.nextDouble();
        Laptop[] ls = new Laptop[n];
        for (int i = 0; i < n; i++) {
            ls[i] = new Laptop(in.nextDouble(), in.nextDouble(), in.nextDouble());
        }
        double ans = 0;
        for (Laptop l : ls) {
            ans += l.c / l.t / v;
        }
        out.printf("%.0f%n", Math.ceil(ans));
        while (q-- != 0) {
            int m = in.nextInt();
            solve(ls, n, v, m);
        }
    }

    private void solve(Laptop[] ls, int n, double v, int m) {
        double l = 0, r = 100000 + 10;
        for (int i = 0; i < 50; i++) {
            if (r - l < 1e-5) break;
            double mid = (l + r) / 2;
            if (calc(ls, v, mid) > m) r = mid;
            else l = mid;
        }
        if (l > 100000) out.println("-1.000");
        else out.printf("%.3f%n", l);
    }

    double calc(Laptop[] ls, double v, double mid) {
        double res = 0;
        for (Laptop l : ls) {
            if (l.r - mid * l.c / l.t >= 0) continue;
            double t = mid - l.r / (l.c / l.t);
            res += t * l.c / l.t;
        }
        return res / mid / v;
    }

    class Laptop {
        double c;
        double t;
        double r;

        Laptop(double c, double t, double r) {
            this.c = c;
            this.t = t;
            this.r = r;
        }
    }
}
