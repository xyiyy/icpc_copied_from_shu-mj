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
        long n = in.nextLong();
        for (long d = 1; ; d *= 3) {
            if (n % d != 0) {
                out.println((n + d - 1) / d);
                return ;
            }
        }
    }
}
