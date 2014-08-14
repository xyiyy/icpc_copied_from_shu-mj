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
        int[] is = in.nextIntArray(n);
        int g = is[0];
        for (int i = 1; i < n; i++) g = Num.gcd(g, is[i]);
        int max = Algo.max(is);
        int num = max / g - n;
        if (num % 2 == 0) {
            out.println("Bob");
        } else {
            out.println("Alice");
        }
    }
}
