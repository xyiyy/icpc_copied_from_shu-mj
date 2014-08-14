package main;

import com.shu_mj.math.Num;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskD {
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
            out.print("Case " + i + ": ");
            solve();
        }
    }

    private void solve() {
        long n = in.nextLong();
        Map<Long, Integer> map = Num.factorize(n);
        for (int i : map.values()) {
            if (i >= 2) {
                out.println("No");
                return ;
            }
        }
        out.println("Yes");
    }
}
