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
        int m = in.nextInt();
        int n = in.nextInt();
        boolean[][] maps = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                maps[i][j] = in.next().charAt(0) == '1';
            }
        }
        int ans = -1;
        boolean[][] ansMat = null;
        for (int i = 0; i < (1 << n); i++) {
            boolean[][] mat = new boolean[m][n];
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) mat[0][j] = true;
            }
            int res = calc(maps, m, n, mat);
            if (res >= 0 && (ans == -1 || ans > res)) {
                ans = res;
                ansMat = mat;
            }
        }
        if (ans == -1) {
            out.println("IMPOSSIBLE");
            return ;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (ansMat[i][j]) out.print("1 ");
                else out.print("0 ");
            }
            out.println(ansMat[i][n - 1] ? "1" : "0");
        }
    }

    private int calc(boolean[][] maps, int m, int n, boolean[][] mat) {
        boolean[][] maps2 = new boolean[m][];
        for (int i = 0; i < m; i++) {
            maps2[i] = maps[i].clone();
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (mat[0][i]) {
                fill(maps2, 0, i);
                res++;
            }
        }
        for (int i = 0; i < m; i++) {
            if (i == m - 1) {
                for (int j = 0; j < n; j++) {
                    if (maps2[i][j]) return -1;
                }
                return res;
            }
            for (int j = 0; j < n; j++) {
                if (maps2[i][j]) {
                    mat[i + 1][j] = true;
                    fill(maps2, i + 1, j);
                    res++;
                }
            }
        }
        return -1;
    }

    private void fill(boolean[][] maps, int i, int j) {
        maps[i][j] ^= true;
        if (i > 0) maps[i - 1][j] ^= true;
        if (j > 0) maps[i][j - 1] ^= true;
        if (i < maps.length - 1) maps[i + 1][j] ^= true;
        if (j < maps[0].length - 1) maps[i][j + 1] ^= true;
    }
}
