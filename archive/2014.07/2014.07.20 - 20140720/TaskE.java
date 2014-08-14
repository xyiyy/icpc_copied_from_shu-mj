package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskE {
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
            out.printf("Case %d: ", i);
            solve();
        }
    }

    private void solve() {
        int a = in.nextInt();
        int b = in.nextInt();
        out.printf("%.6f %.6f%n", dfs(a, b, 0), a * 1.0 / (a + b));
    }
    double dfs(int a, int b, int res) {
        if (a == 0 || b == 0 || res > 25) return res;
        int m = Math.min(a, b);
        return dfs(a - m, b + m, res + 1) * 0.5 + dfs(a + m, b - m, res + 1) * 0.5;
    }
}
