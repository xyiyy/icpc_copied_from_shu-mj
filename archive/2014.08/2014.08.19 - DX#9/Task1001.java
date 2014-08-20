package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task1001 {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        while (in.hasNext()) {
            int n = in.nextInt();
            if (n == 0) break;
            solve(n);
        }
    }

    private void solve(int n) {
        long[] is = in.nextLongArray(n);
        int[] cs = new int[n + 1];
        for (int i = 1; i <= n; i++) cs[i] = in.nextInt();
        if (n == 1) {
            out.println(0);
            return ;
        }
        if (n == 2) {
            if (is[0] == is[1]) out.println(0);
            else out.println(cs[1]);
            return ;
        }

        long[] ps = new long[n];
        for (int i = 0; i < n; i++) ps[i] = (i == 0 ? is[i] : ps[i - 1] + is[i]);

        int res = cs[n - 1];
        int[][] dp = new int[]
        for (int i = 0, j = n - 1; i - 1 <= j; ) {
            long si = sum(ps, 0, i - 1);
            long sj = sum(ps, j + 1, n - 1);
            if (si == sj) {
                if (i != 0 || j != n - 1) {
                    lastCost = Math.min(cs[i] + cs[n - j], lastCost + cs[i - lastI - 1])
                }
                if (is[i] < is[j]) i++;
                else j++;
            } else if (si < sj) i++;
            else j++;
        }

        out.println(res);
    }

    private long sum(long[] ps, int b, int e) {
        if (b > e) return 0;
        return ps[e] - (b == 0 ? 0 : ps[b - 1]);
    }

}
