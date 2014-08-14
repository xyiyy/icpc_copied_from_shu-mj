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
        for (int i = 1; i <= t; i++) {
            out.printf("Case %d:%n", i);
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
        double delta = b * 1.0 * b - 4.0 * a * c;
        if (a == 0 || delta <= 0) out.println("0.00");
        else {
            double x1 = (-b - Math.sqrt(delta)) / 2.0 / a;
            double x2 = (-b + Math.sqrt(delta)) / 2.0 / a;
            out.printf("%.2f%n", Math.abs(f(a, b, c, x1) - f(a, b, c, x2)));
        }
    }

    private double f(int a, int b, int c, double x) {
        return a * x * x * x / 3 + b * x * x / 2 + c * x;
    }

}
