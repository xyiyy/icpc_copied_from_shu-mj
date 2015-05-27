package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskB {
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
            out.printf("Case #%d: ", i);
            solve();
        }
    }

    private void solve() {
        int b = in.nextInt();
        int n = in.nextInt();
        int[] bs = in.nextIntArray(b);
        long l = 0, r = 100000L * 10000000000L;
        while (l < r) {
            long m = (l + r) / 2;
            long c = 0;
            for (int i : bs) c += (m + i - 1) / i;
            if (c < n) l = m + 1;
            else r = m;
        }
        long dw = 0;
        for (int i : bs) {
            dw += (l + i - 2) / i;
        }
        int c = 0;
        for (int i = 0; i < b; i++) {
            if ((l - 1) % bs[i] == 0) {
                c++;
                if (c == n - dw) {
                    out.println(i + 1);
                    return;
                }
            }
        }
    }
}
