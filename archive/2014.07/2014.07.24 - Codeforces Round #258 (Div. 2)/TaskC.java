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
        int t = in.nextInt();
        while (t-- != 0)
            solve();
    }

    private void solve() {
        long n = in.nextLong();
        long k = in.nextLong();
        long d1 = in.nextLong();
        long d2 = in.nextLong();
        if (n % 3 != 0) {
            out.println("no");
            return ;
        }
        for (int i = -1; i <= 1; i += 2) {
            for (int j = -1; j <= 1; j += 2) {
                if (fit(n, k, i * d1, j * d2)) {
//                    Algo.debug(n, k, d1, d2, i * d1, j * d2);
                    out.println("yes");
                    return ;
                }
            }
        }
        out.println("no");
    }

    private boolean fit(long n, long k, long d1, long d2) {
        if (k < d1 + d2) return false;
        if ((k - d1 - d2) % 3 != 0) return false;
        long x = (k - d1 - d2) / 3;
        long a = x + d1;
        long b = x;
        long c = x + d2;
        x = n / 3;
        if (a < 0 || b < 0 || c < 0 || a > x || b > x || c > x) return false;
        return true;
    }
}
