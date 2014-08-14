package main;

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
        long[] ans = new long[42];
        ans[1] = 3;
        ans[2] = 8;
        for (int i = 3; i < 42; i++) {
            ans[i] = 3 * ans[i - 1] - ans[i - 2];
        }
        while (in.hasNext()) {
            out.println(ans[in.nextInt()]);
        }
    }

}
