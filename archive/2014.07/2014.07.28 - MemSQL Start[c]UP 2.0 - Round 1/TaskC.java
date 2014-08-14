package main;

import com.shu_mj.math.Num;
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
        int n = in.nextInt();
        int m = in.nextInt();
        double res = 0;
        double[] Cm = Num.doubleCombinationRowTable(m);
        double[] Cmn = Num.doubleCombinationRowTable(m * n);
        double[] Cmnsm = Num.doubleCombinationRowTable(m * n - m);
        for (int i = 1; i <= Math.min(n, m); i++) {
            res += Cm[i] * Cmnsm[n - i] / Cmn[n] * i / n * i / n;
        }
        res *= n;
        out.printf("%.16f%n", res);
    }

}
