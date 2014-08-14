package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.math.BigInteger;
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
        while (in.hasNext()) {
            int n = in.nextInt();
            int k = in.nextInt();
            if (n == 0 && k == 0)
                break;
            solve(n, k);
        }
    }

    private void solve(int n, int k) {
        int[] a = in.nextIntArray(n);
        int[] b = in.nextIntArray(n);
        k = n - k;
        double l = 0, r = 1.0;
        for (int i = 0; i < 20; i++) {
            if (r - l < 1e-4) break;
            double m = (l + r) / 2;
            if (fit(a, b, n, m, k)) l = m;
            else r = m;
        }
        out.printf("%.0f%n", l * 100);
    }

    private boolean fit(int[] a, int[] b, int n, double m, int k) {
        double[] ds = new double[n];
        for (int i = 0; i < n; i++) {
            ds[i] = a[i] - m * b[i];
        }
        Arrays.sort(ds);
        double sum = 0;
        while (k > 0) {
            sum += ds[n - k];
            k--;
        }
        return sum >= 0;
    }
}
