package main;

import com.shu_mj.math.Num;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskA {
    Scanner in;
    PrintWriter out;
    private int[] mu;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        init();
        for (int i = 0; i < t; i++) {
            solve();
        }
    }

    void init() {
        mu = Num.moebiusTable(1000000);
    }
    private void solve() {
        int n = in.nextInt();
        long ans = 3;
        for (int i = 1; i <= n; i++) {
            ans += mu[i] * ((long) (n / i) * (n / i) * (n / i + 3));
        }
        out.println(ans);
    }
}
