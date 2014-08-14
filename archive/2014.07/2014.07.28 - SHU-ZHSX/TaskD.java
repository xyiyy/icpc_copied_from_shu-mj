package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskD {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        BigInteger[] f = new BigInteger[101];
        f[0] = B(1);
        f[1] = B(1);
        f[2] = B(2);
        f[3] = B(5);
        for (int i = 4; i <= 100; i++) {
            f[i] = B(0);
            for (int j = 0; j < i; j++) {
                f[i] = f[i].add(f[j].multiply(f[i - j - 1]));
            }
        }
        Algo.debug(f);
        while (in.hasNext()) {
            int n = in.nextInt();
            if (n == -1) break;
            out.println(f[n]);
        }
    }

    private BigInteger B(int i) {
        return BigInteger.valueOf(i);
    }
}
