package main;

import com.shu_mj.math.Num;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskB {
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
        long a = in.nextLong();
        long b = in.nextLong();
        long c = b / a;
        Map<BigInteger, Integer> factors = new HashMap<BigInteger, Integer>();
        Num.primeTable(100000, new ArrayList<Integer>());
        Num.factorize(BigInteger.valueOf(c), factors);
        long[] fs = new long[factors.size()];
        int n = 0;
        for (Map.Entry<BigInteger, Integer> e : factors.entrySet()) {
            fs[n++] = e.getKey().pow(e.getValue()).longValue();
        }
        long x = 1, y = c;
        for (int i = 0; i < (1 << n); i++) {
            long xx = 1, yy = 1;
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) yy *= fs[j];
                else xx *= fs[j];
            }
            if ((xx + yy) < x + y) {
                x = xx;
                y = yy;
            }
        }
        if (x > y) { long t = x; x = y; y = t; }
        out.println((a * x) + " " + (a * y));
    }
}
