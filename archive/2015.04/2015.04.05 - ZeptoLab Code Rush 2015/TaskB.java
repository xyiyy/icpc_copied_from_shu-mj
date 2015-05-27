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
        int n = in.nextInt();
        int N = (1 << n);
        int[] is = new int[N * 2];
        for (int i = 2; i < N * 2; i++) {
            is[i] = in.nextInt();
        }
        long ini = Algo.sum(is);
        for (int i = N - 1; i > 0; i--) {
            is[i * 2] = is[i * 2 + 1] = Math.max(is[i * 2], is[i * 2 + 1]);
            is[i] += is[i * 2];
        }
        for (int i = 1; i < N; i++) {
            is[i] -= is[i * 2];
        }
        out.println(Algo.sum(is) - ini);
    }
}
