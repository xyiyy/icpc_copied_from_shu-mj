package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskF {
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
        if (n > m) {int t = n; n = m; m = t;}
        long[] f = new long[n + 1];
        long ans = 0;
        for (int i = n; i >= 1; i--) {
            f[i] = (long) (n / i) * (m / i);
            for (int j = i + i; j <= n; j += i) {
                f[i] -= f[j];
            }
            ans += f[i] * (2 * i - 1);
        }
        out.println(ans);
    }
}
