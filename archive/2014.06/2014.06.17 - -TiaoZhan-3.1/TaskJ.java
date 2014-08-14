package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskJ {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        while (t-- != 0)
            solve();
    }

    private void solve() {
        int n = in.nextInt();
        long m = in.nextLong();
        // i2 + 100000 × i + j2 - 100000 × j + i × j
        long l = -(long) n * n * 100 - 1000000L * n;
        long r = -l;
        // 小于 mid 的数的个数小于 m 的 mid 的 upper
        while (l < r) {
            long mid = l + (r - l) / 2;
            if (calc(n, mid) < m) l = mid + 1;
            else r = mid;
        }
        out.println(l);
    }

    private long calc(int n, long mid) {
        long res = 0;
        for (int i = 1; i <= n; i++) {
            res += calc(i, n, mid);
        }
        return res;
    }

    private int calc(int j, int n, long mid) {
        /*int l = 1, r = n + 1;
        while (l < r) {
            int i = (l + r) / 2;
            long val = (long) i * i + 100000L * i + (long) j * j - 100000L * j + (long) i * j;
            if (val <= mid) l = i + 1;
            else r = i;
        }
        return l - 1;
        */
        // i2 + 100000 × i + j2 - 100000 × j + i × j
        // i2 + (100000 + j) * i + j2 - 100000 * j <= mid
        long b = 100000 + j;
        long c = (j - 100000L) * j - mid;
        long delta = b * b - 4 * c;
        if (delta < 0) return 0;
        long x = (long) ((-b + Math.sqrt(delta)) / 2);
        while (x * x + b * x + c <= 0) x++;
        while (x * x + b * x + c > 0) x--;
        return Math.max(0, Math.min(n, (int) x));
    }
}
