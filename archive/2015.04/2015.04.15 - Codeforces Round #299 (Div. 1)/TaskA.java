package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskA {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int a = in.nextInt();
        int b = in.nextInt();
        int n = in.nextInt();
        while (n-- != 0) {
            int l = in.nextInt();
            int t = in.nextInt();
            int m = in.nextInt();
            int L = 0, R = (int) (2e6);
            while (L < R) {
                int M = (L + R) / 2;
                if (fit(a, b, l, M, t, m)) L = M + 1;
                else R = M;
            }
            out.println(L == 0 ? -1 : l + L - 1);
        }
    }

    private boolean fit(int a, int b, int l, int r, int t, int m) {
        long sum = (long) a * (r + 1) + (long) b * (l - 1 + l + r - 1) * (r + 1) / 2;
        long max = a + (long) b * (l + r - 1);
        return max <= t && sum <= (long) m * t;
    }
}
