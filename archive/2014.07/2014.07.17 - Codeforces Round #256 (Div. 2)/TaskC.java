package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskC {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int[] is = in.nextIntArray(n);
        out.println(dfs(is, 0, 0, n));
    }
    int dfs(int[] is, int crt, int l, int r) {
        if (r - l == 0) return 0;
        if (r - l == 1) return 1;
        int res = 0;
        int min = Integer.MAX_VALUE;
        for (int i = l; i < r; i++) {
            min = Math.min(min, is[i]);
        }
        int L = l, R = l;
        for (;;) {
            L = R;
            while (L < r && is[L] == min) L++;
            if (L == r) break;
            R = L;
            while (R < r && is[R] != min) R++;
            res += dfs(is, min, L, R);
        }
        return Math.min(min - crt + res, r - l);
    }
}
