package main;

import com.shu_mj.math.Num;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskG {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        while (in.hasNext()) {
            long p = in.nextLong();
            long a = in.nextLong();
            if (p == 0 && a == 0) break;
            solve(p, a);
        }
    }

    private void solve(long p, long a) {
        Algo.M = p;
        if (!Num.isPrime(p) && Algo.powMod(a, p) == a)
            out.println("yes");
        else out.println("no");
    }
}
