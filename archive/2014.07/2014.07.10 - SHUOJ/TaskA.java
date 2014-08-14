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
        for (int i = 0; i < t; i++) {
            out.printf("Case %d:%n", i + 1);
            solve();
        }
    }

    private void solve() {
        String s = in.next();
        String[] ss = s.split("_");
        boolean f = true;
        for (String str : ss) {
            if (f) out.print(str);
            else out.print(str.substring(0, 1).toUpperCase() + str.substring(1));
            f = false;
        }
        out.println();
    }
}
