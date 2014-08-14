package main;

import com.shu_mj.math.Matrix;
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
        int t = in.nextInt();
        while (t-- != 0) {
            solve();
        }
    }

    final int[][] mat = {
            {2, 2, 0},
            {1, 2, 1},
            {0, 2, 2}
    };
    private void solve() {
        int n = in.nextInt();
        out.println(Matrix.powMod(mat, n, 10007)[0][0]);
    }
}
