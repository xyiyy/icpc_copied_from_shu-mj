package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskB {
    Scanner in;
    PrintWriter out;
    private BigInteger[] f;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        f = new BigInteger[101];
        for (int i = 0; i < 101; i++) {
            if (i == 0) f[i] = BigInteger.ONE;
            else f[i] = f[i - 1].multiply(BigInteger.valueOf(i));
        }
        while (in.hasNext()) {
            int n = in.nextInt();
            int m = in.nextInt();
            if (n == 0 && m == 0) break;
            solve(n, m);
        }
    }

    private void solve(int n, int m) {
        out.printf("%d things taken %d at a time is %d exactly.%n", n, m, f[n].divide(f[m]).divide(f[n - m]));
    }
}
