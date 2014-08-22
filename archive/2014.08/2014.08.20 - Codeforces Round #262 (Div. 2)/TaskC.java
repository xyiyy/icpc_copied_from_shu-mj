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
        int m = in.nextInt();
        int w = in.nextInt();
        int[] is = in.nextIntArray(n);
        int l = Algo.min(is);
        int r = Algo.max(is) + m + 2;
        while (l < r) {
            int mid = (l + r) / 2;
            if (fit(n, m, w, is, mid)) l = mid + 1;
            else r = mid;
        }
        out.println(l - 1);
    }

    private boolean fit(int n, int m, int w, int[] is, int x) {
        int[] f = new int[n];
        int crt = 0;
        for (int i = 0; i < n; i++) {
            if (i - w >= 0) crt -= f[i - w];
            f[i] = Math.max(x - is[i] - crt, 0);
            m -= f[i];
            crt += f[i];
            if (m < 0) return false;
        }
        return true;
    }
}
