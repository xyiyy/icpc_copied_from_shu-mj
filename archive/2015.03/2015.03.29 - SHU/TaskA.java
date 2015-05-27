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
        int r = in.nextInt();
        int h = in.nextInt();
        double H = (int) (h + r - Math.sqrt(3) * r / 2);
        int a = 1 + (int) (H / r) * 2;
        int b = (h * 2 / r + 1) / 2 * 2;
//        Algo.debug(a, b);
        out.println(Math.max(a, b));
    }
}
