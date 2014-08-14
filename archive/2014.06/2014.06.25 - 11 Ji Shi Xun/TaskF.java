package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskF {
    Scanner in;
    PrintWriter out;
    private BigInteger[] bs;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        final int MAX_N = 500;
        bs = new BigInteger[MAX_N + 1];
        bs[0] = bs[1] = BigInteger.ONE;
        for (int i = 2; i <= MAX_N; i++) {
            bs[i] = bs[i - 1].multiply(BigInteger.valueOf(i));
        }

        while (in.hasNext()) {
            solve();
        }
    }

    private void solve() {
        int n = in.nextInt();
        out.println(n + "!=");
        char[] cs = bs[n].toString().toCharArray();
        for (int i = 0; i < cs.length; i++) {
            out.print(cs[i]);
            if (i % 80 == 79) {
                out.println();
            }
        }
        if (cs.length % 80 != 0) out.println();
    }
}
