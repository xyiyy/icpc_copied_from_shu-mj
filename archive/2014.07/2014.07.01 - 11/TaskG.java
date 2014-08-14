package main;

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
            solve();
        }
    }

    private void solve() {
        int n = in.nextInt();
        int ans = 0;
        int[][] mat = in.nextIntMatrix(n, 2);
        for (int i = 0; i < n; i++) {
            if (canSee(mat, i, mat[i][0], mat[i][1])) ans++;
        }
        out.println(ans);
    }

    private boolean canSee(int[][] mat, int i, int x, int y) {
        if (x == 0 && y == 0) return true;
        for (int j = 0; j < mat.length; j++) if (i != j) {
            int u = mat[j][0];
            int v = mat[j][1];
            if (x == 0) {
                if (u == 0) {
                    if (Math.abs(v) < Math.abs(y) && v * y >= 0) {
                        return false;
                    }
                }
            }
            if (y == 0) {
                if (v == 0) {
                    if (Math.abs(u) < Math.abs(x) && u * x >= 0) {
                        return false;
                    }
                }
            }
            if (x * u > 0 && v * y > 0 && (Math.abs(u) < Math.abs(x) || Math.abs(v) < Math.abs(y)) && !(x == u && y == v)) {
                if (u * x == y * v) return false;
            }
        }
        return true;
    }
}
