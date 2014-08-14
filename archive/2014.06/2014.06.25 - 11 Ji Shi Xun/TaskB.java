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
        while (in.hasNext())
            solve();
    }
    void solve() {
        int n = in.nextInt();
        int[] is = in.nextIntArray(n);
        Algo.nextPermutation(is);
        for (int i = 0; i < n - 1; i++) {
            out.print(is[i] + " ");

        }
        out.println(is[n - 1]);
    }
}
