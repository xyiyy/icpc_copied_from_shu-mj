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
        int k = in.nextInt();
        int d = in.nextInt();
        if (k == 1 && d == 0) {
            out.println(0);
            return ;
        }
        if (d == 0) {
            out.println("No solution");
            return ;
        }
        out.print(d);
        for (int i = 0; i < k - 1; i++) out.print('0');
        out.println();
    }
}
