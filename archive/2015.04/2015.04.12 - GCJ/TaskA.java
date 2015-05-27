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
        for (int i = 1; i <= t; i++) {
            out.printf("Case #%d: ", i);
            solve();
        }
    }

    private void solve() {
        int s = in.nextInt();
        int up = 0;
        int res = 0;
        int i = 0;
        for (char c : in.next().toCharArray()) {
            if (c != '0') {
                res += Math.max(0, i - up);
                up += Math.max(0, i - up) + c - '0';
            }
            i++;
        }
        out.println(res);
    }
}
