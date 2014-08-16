package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.math.BigInteger;
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
        int k = in.nextInt();
        int d = in.nextInt();
        if (BigInteger.valueOf(k).pow(d).compareTo(BigInteger.valueOf(n)) < 0) out.println(-1);
        else {
            long div = 1;
            for (int i = 0; i < d; i++) {
                for (int j = 0; j < n; j++) {
                    out.print((j / div % k + 1) + " ");
                }
                out.println();
                if (div < 10000) div *= k;
            }
        }
    }
}
