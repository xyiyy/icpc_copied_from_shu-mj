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
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            out.printf("Case #%d: ", i);
            solve();
        }
    }

    private void solve() {
        int n = in.nextInt();
        int[] is = in.nextIntArray(n);
        int y = 0;
        for (int i = 1; i < n; i++) {
            y += Math.max(0, is[i - 1] - is[i]);
        }
        int z = 0;
        int m = 0;
        for (int i = 1; i < n; i++) {
            m = Math.max(m, is[i - 1] - is[i]);
        }
        for (int i = 0; i < n - 1; i++) {
            int e = Math.min(is[i], m);
            z += e;
        }
        out.println(y + " " + z);

    }
}
