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
        int t = in.nextInt();
        while (t-- != 0)
            solve();
    }

    private void solve() {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] is = in.nextIntArray(n);
        for (int i = 0; i < k; i++) {
            Algo.nextPermutation(is);
        }
        for (int i = 0; i < n; i++) {
            out.print(is[i]);
            if (i == n - 1) out.println();
            else out.print(' ');
        }
    }
}
