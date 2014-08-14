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
        int n = in.nextInt();
        int[] is = in.nextIntArray(n);
        Arrays.sort(is);
        Algo.swap(is, 0, n - 1);
        for (int i = 0; i < n; i++) {
            out.print(is[i] + " ");
        }
        out.println();
    }
}
