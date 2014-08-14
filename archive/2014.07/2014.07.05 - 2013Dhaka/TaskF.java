package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskF {
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
            out.printf("Case %d: ", i);
            solve();
        }
    }

    private void solve() {
        int x0 = in.nextInt();
        int y0 = in.nextInt();
        int x1 = in.nextInt();
        int y1 = in.nextInt();
        int x3 = y0 - y1;
        int y3 = x1 - x0;
        int x2 = 0;
        int y2 = 0;
        if (x3 < 0) {x2 = -x3; x3 = 0;}
        if (y3 < 0) {y2 = -y3; y3 = 0;}
        out.println(x2 + " " + y2 + " " + x3 + " " + y3);
    }
}
