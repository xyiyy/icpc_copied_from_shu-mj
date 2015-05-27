package main;

import com.shu_mj.math.Num;
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
        long M = (long) (1e9 + 7);
        int n = in.nextInt();
        int[] is = in.nextIntArray(n);
        for (int i = 0; i < n; i++) if (is[i] != -1) is[i]--;
        int all = 0;
        int un = 0;
        boolean[] used = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (is[i] == -1) {
                all++;
            } else {
                used[is[i]] = true;
            }
        }
        for (int i = 0; i < n; i++) {
            if (!used[i]) {
                if (is[i] == -1) un++;
            }
        }
        long res = 0;
        long[] Cun = Num.combinationRowTable(un, M);
        long[] f = Num.factorialTable(all, M);
        for (int i = 0; i <= un; i++) {
            res += (i % 2 == 0 ? 1 : -1) * Cun[i] * f[all - i] % M;
            res %= M;
        }
        out.println((res + M) % M);
    }
}
