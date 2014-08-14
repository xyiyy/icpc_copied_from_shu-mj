package main;

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
            solve();
        }
    }

    private void solve() {
        int n = in.nextInt();
        double A = in.nextDouble();
        double l = 0, r = A;
        for (int i = 0; i < 75; i++) {
            double m = (l + r) / 2;
            if (fit(A, n, m)) r = m;
            else l = m;
        }
        String s = String.format("%.2f", calc(A, n, l));
        if (s.charAt(0) == '-') s = s.substring(1);
        out.println(s);
    }

    private double calc(double a, int n, double l) {
        double b = l;
        double c = 0;
        for (int i = 2; i < n; i++) {
            c = 2 * b + 2 - a;
            a = b;
            b = c;
        }
        return c;
    }


    private boolean fit(double a, int n, double m) {
        double b = m;
        double c;
        for (int i = 2; i < n; i++) {
            c = 2 * b + 2 - a;
            if (c < 0) return false;
            a = b;
            b = c;
        }
        return true;
    }
}
