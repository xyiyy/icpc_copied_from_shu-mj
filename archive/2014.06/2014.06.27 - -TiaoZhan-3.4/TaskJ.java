package main;

import com.shu_mj.math.Matrix;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskJ {
    Scanner in;
    PrintWriter out;
    private int[][] mat;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        mat = new int[16][16];
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (adj(i, j)) mat[i][j] = 1;
            }
        }
        Algo.debugTable(mat);
        while (in.hasNext()) {
            int n = in.nextInt();
            int m = in.nextInt();
            if (n == 0 && m == 0) break;
            solve(n, m);
        }
    }

    private boolean adj(int a, int b) {
        if ((a & b) != 0) return false;
        int ab = a | b;
        for (int i = 0; i < 4; ) {
            if ((ab & (1 << i)) != 0) i++;
            else {
                if (i == 4 - 1 || (ab & (1 << i + 1)) != 0) return false;
                else i += 2;
            }
        }
        return true;
    }

    private void solve(int n, int m) {
        out.println(Matrix.pow(mat, n, m)[0][0]);
    }
}
