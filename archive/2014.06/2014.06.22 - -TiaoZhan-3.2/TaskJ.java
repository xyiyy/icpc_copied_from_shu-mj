package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskJ {
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
            out.println("PUZZLE #" + i);
            solve();
        }
    }

    private void solve() {
        int[][] iss = in.nextIntMatrix(5, 6);
        for (int i = 0; i < (1 << 6); i++) {
            int[][] mat = new int[5][6];
            for (int j = 0; j < 6; j++) if ((i & (1 << j)) != 0) mat[0][j] = 1;
            if (fit(iss, mat)) {
                for (int u = 0; u < 5; u++) {
                    for (int v = 0; v < 5; v++) {
                        out.print(mat[u][v] + " ");
                    }
                    out.println(mat[u][5]);
                }
                return ;
            }
        }
    }

    private boolean fit(int[][] iss, int[][] mat) {
        int[][] st = new int[5][];
        for (int i = 0; i < 5; i++) st[i] = iss[i].clone();
        for (int i = 0; i < 6; i++) if (mat[0][i] == 1) fill(st, 0, i);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                if (st[i][j] == 1) {
                    mat[i + 1][j] = 1;
                    fill(st, i + 1, j);
                }
            }
        }
        for (int i = 0; i < 6; i++) if (st[4][i] == 1) return false;
        return true;
    }

    private void fill(int[][] st, int i, int j) {
        if (i > 0) st[i - 1][j] ^= 1;
        if (j > 0) st[i][j - 1] ^= 1;
        if (i < 4) st[i + 1][j] ^= 1;
        if (j < 5) st[i][j + 1] ^= 1;
        st[i][j] ^= 1;
    }
}
