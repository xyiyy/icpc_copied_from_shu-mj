package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskB {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        char[] cs = in.next().toCharArray();
        int n = cs.length;
        boolean[] bs = new boolean[n];
        for (int i : in.nextIntArray(in.nextInt())) {
            bs[i - 1] ^= true;
        }
        boolean crt = false;
        for (int i = 0; i < n / 2; i++) {
            crt ^= bs[i];
            if (crt) Algo.swap(cs, i, n - i - 1);
        }
        out.println(cs);
    }
}
