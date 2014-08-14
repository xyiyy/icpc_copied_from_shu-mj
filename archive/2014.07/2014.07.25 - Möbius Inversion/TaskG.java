package main;

import com.shu_mj.math.Num;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskG {
    Scanner in;
    PrintWriter out;
    private int[] sum;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int[] mu = Num.moebiusTable(50000);
        sum = new int[mu.length];
        for (int i = 0; i < sum.length; i++) {
            sum[i] = (i == 0 ? 0 : sum[i - 1]) + mu[i];
        }
        while (n-- != 0) {
            solve();
        }
    }

    private void solve() {
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();
        int d = in.nextInt();
        int k = in.nextInt();
        long ans = calc(b / k, d / k);
        ans -= calc((a - 1) / k, d / k);
        ans -= calc(b / k, (c - 1) / k);
        ans += calc((a - 1) / k, (c - 1) / k);
        out.println(ans);
    }

    private long calc(int a, int b) {
        if (a > b) return calc(b, a);
        long res = 0;
        for (int i = 1, last; i <= a; i = last + 1) {
            last = Math.min(a / (a / i), b / (b / i));
            res += (sum[last] - sum[i - 1]) * (long) (a / i) * (b / i);
        }
        return res;
    }
}
