package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskD {
    Scanner in;
    PrintWriter out;
    private int h;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        while (t-- != 0)
            solve();
    }

    private void solve() {
        int n = in.nextInt();
        h = in.nextInt();
        int r = in.nextInt();
        int t = in.nextInt();
        double[] y = new double[n];
        for (int i = 0; i < n; i++) {
            y[i] = calc(t - i);
        }
        Arrays.sort(y);
        for (int i = 0; i < n - 1; i++) {
            out.printf("%.2f ", y[i] + 2 * r * i / 100.0);
        }
        out.printf("%.2f%n", y[n - 1] + 2 * r * (n - 1) / 100.0);
    }

    final double g = 10.0;
    private double calc(int T) {
        if (T < 0) return h;
        double t = Math.sqrt(2 * h / g);
        int k = (int) (T / t);
        if (k % 2 == 0) {
            double d = T - k * t;
            return h - g * d * d / 2;
        } else {
            double d = k * t + t - T;
            return h - g * d * d / 2;
        }
    }
}
