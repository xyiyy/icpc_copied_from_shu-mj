package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
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
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            out.printf("Case %d:%n", i);
            solve();
        }
    }

    private void solve() {
        int r = in.nextInt();
        int s = in.nextInt();
        int cap = 150 + 1500 / (s + 2);
        out.println((r - cap) + " " + (r + cap));
    }
}
