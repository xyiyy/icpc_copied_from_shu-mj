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
        int[] is = in.nextIntArray(n);
        int maxV = Algo.max(is);
        int minV = Algo.min(is);
        int maxC = Algo.count(is, maxV);
        int minC = Algo.count(is, minV);
        out.println((maxV - minV) + " " + (maxV != minV ? ((long) maxC * minC) : (long) n * (n - 1) / 2));
    }
}
