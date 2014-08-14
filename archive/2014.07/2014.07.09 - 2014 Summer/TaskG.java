package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskG {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            out.println("Case " + (i + 1) + ":");
            solve();
        }
    }

    private void solve() {
        int a1 = in.nextInt();
        int b1 = in.nextInt();
        int c1 = in.nextInt();
        int a2 = in.nextInt();
        int b2 = in.nextInt();
        int c2 = in.nextInt();
        int a = a1 - a2;
        int b = b1 - b2;
        int c = c1 - c2;
        if (a == 0) {
            out.println("0.00");
            return ;
        }
        double delta = (double) b * b - 4.0 * a * c;
        if (delta <= 0) {
            out.println("0.00");
            return ;
        }
        double x1 = (-b - Math.sqrt(delta)) / 2 / a;
        double x2 = (-b + Math.sqrt(delta)) / 2 / a;
        if (x1 > x2) {
            double t = x1;
            x1 = x2;
            x2 = t;
        }
        out.printf("%.2f%n", Math.abs(f(a, b, c, x2) - f(a, b, c, x1)));
    }

    private double f(int a, int b, int c, double x) {
        return 1.0 / 3 * a * x * x * x + 1.0 / 2 * b * x * x + c * x;
    }
}
