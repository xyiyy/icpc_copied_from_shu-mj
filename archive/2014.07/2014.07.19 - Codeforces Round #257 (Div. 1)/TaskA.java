package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskA {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        if (n + m - 2 < k) {
            out.println(-1);
            return ;
        } else if (n + m - 2 == k) {
            out.println(1);
            return ;
        }
        long l = Math.max(0, k - (m - 1)), r = Math.min(n - 1, k);
        int times = 0;
        while (l <= r) {
            long lm = l + (r - l) / 2;
            long rm = lm + (r - lm) / 2;
//            Algo.debug(l, r, lm, rm);
            if (times++ > 1000) break;
            if (calc(n, m, lm, k - lm) > calc(n, m, rm, k - rm)) {
                r = rm;
            } else {
                l = lm;
            }
        }
        long ans = 0;
        for (int i = -5000000; i <= 5000000; i++) {
            long t = Math.max(0, l + i);
            t = Math.max(t, k - m + 1);
            t = Math.min(t, k);
            ans = Math.max(ans, calc(n, m, t, k - t));
        }
        out.println(ans);
    }
    long calc(long n, long m, long l, long r) {
//        Algo.debug("calc", n, m, l, r);
        return n / (l + 1) * (m / (r + 1));
    }
}
