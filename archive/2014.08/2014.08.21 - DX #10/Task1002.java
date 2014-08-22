package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task1002 {
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
        int n = in.nextInt();
        int[] is = in.nextIntArray(n);
        BigInteger a = B(1);
        for (int i = 0; i < n; i++) {
            int pre = (i == 0 ? 0 : is[i - 1]);
            BigInteger na = B(0);
            for (int j = -3; j <= 3; j++) if (j != 0) {
                if (Math.abs(pre + j) == is[i]) {
                    na = na.add(a);
                }
            }
            a = na;
        }
        if (a.signum() == 0) out.println(a);
        else if (is[n - 1] == 0) out.println(1);
        else out.println(2);
    }
    BigInteger B(long a) {
        return BigInteger.valueOf(a);
    }
}
