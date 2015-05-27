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
        int n = in.nextInt();
        int[] is = in.nextIntArray(n);
        Algo.sort(is);
        long sum = 0;
        long pre = 0;
        for (int i = 0; i < n; i++) {
            sum += (long) i * is[i] - pre;
            pre += is[i];
        }
        long a = pre + 2 * sum;
        long b = n;
//        Algo.debug(a, b, pre, sum);
        long g = Num.gcd(a, b);
        out.println((a / g) + " " + (b / g));
    }
}
