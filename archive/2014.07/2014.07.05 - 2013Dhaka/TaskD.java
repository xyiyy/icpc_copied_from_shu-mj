package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskD {
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
        int l = in.nextInt();
        int m = in.nextInt();
        int n = in.nextInt();
        long M = (long) (1e13 + 7);
        long ans = 0;
        long f = 1;
        for (int i = 1; i <= n; i++) {
            f *= l * l - i + 1;
            f %= M;
            if (i >= m) {
                ans += f;
                ans %= M;
            }
        }
        out.println(ans);
    }
}
