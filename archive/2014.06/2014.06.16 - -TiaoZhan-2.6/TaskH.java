package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskH {
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
        long m = in.nextLong();
        Algo.M = m;
        int h = in.nextInt();
        long res = 0;
        for (int i = 0; i < h; i++) {
            long a = in.nextLong();
            long b = in.nextLong();
            res = (res + Algo.powMod(a, b)) % m;
        }
        out.println(res);
    }
}
