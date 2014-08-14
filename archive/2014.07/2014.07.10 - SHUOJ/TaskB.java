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
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            out.printf("Case %d:%n", i);
            solve();
        }
    }

    String[] ss = {
            "1-2-1 1-2-1 1 1 1-2-1",
            "1 2 3 4 1-2-3 4"
    };
    private void solve() {
        int n = in.nextInt();
        int m = in.nextInt() - 1;
        for (int i = 0; i < n; i++) {
            out.println(ss[m]);
            m ^= 1;
        }
    }
}
