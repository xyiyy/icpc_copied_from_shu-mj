package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
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
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            out.printf("Case #%d: ", i);
            solve();
        }
    }

    private void solve() {
        int d = in.nextInt();
        int[] ps = in.nextIntArray(d);
        int res = Algo.max(ps);
        for (int i = 1; i <= 1000; i++) {
            int tmp = 0;
            for (int p : ps) {
                tmp += (p + i - 1) / i - 1;
            }
            res = Math.min(res, tmp + i);
        }
        out.println(res);
    }
}
