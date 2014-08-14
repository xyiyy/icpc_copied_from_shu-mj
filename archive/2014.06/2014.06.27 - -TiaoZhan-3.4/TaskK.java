package main;

import com.shu_mj.math.Matrix;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.Arrays;

import com.shu_mj.tpl.Algo;

public class TaskK {
    Scanner in;
    PrintWriter out;
    private long[][] one;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        while (in.hasNext()) {
            int n = in.nextInt();
            int m = in.nextInt();
            int k = in.nextInt();
            if (n == 0 && m == 0 && k == 0) break;
            solve(n, m, k);
        }
    }


    private void solve3(int n, int m, int k) {
        long[][] mat = one(n + 1);
        one = one(n + 1);
        for (int i = 0; i < k; i++) {
            char c = in.next().charAt(0);
            if (c == 'g') {
                int u = in.nextInt();
                one[0][u] = 1;
                mat = Matrix.mul(mat, one);
                one[0][u] = 0;
            } else if (c == 'e') {
                int u = in.nextInt();
                one[u][u] = 0;
                mat = Matrix.mul(mat, one);
                one[u][u] = 1;
            } else {
                int u = in.nextInt();
                int v = in.nextInt();
                if (u == v) continue;
                one[u][u] = one[v][v] = 0;
                one[u][v] = one[v][u] = 1;
                mat = Matrix.mul(mat, one);
                one[u][u] = one[v][v] = 1;
                one[u][v] = one[v][u] = 0;
            }
//            Algo.debugTable(mat);
        }
//        Algo.debugTable(mat);
        mat = Matrix.pow(mat, m);
        for (int i = 1; i <= n; i++) {
            out.print(mat[0][i]);
            if (i == n) out.println();
            else out.print(' ');
        }
    }
    private void solve(int n, int m, int k) {
        long[][] mat = one(n + 1);
        one = one(n + 1);
        for (int i = 0; i < k; i++) {
            char c = in.next().charAt(0);
            if (c == 'g') {
                int u = in.nextInt() - 1;
                one[n][u] = 1;
                mat = Matrix.mul(mat, one);
                one[n][u] = 0;
            } else if (c == 'e') {
                int u = in.nextInt() - 1;
                one[u][u] = 0;
                mat = Matrix.mul(mat, one);
                one[u][u] = 1;
            } else {
                int u = in.nextInt() - 1;
                int v = in.nextInt() - 1;
                if (u == v) continue;
                one[u][u] = one[v][v] = 0;
                one[u][v] = one[v][u] = 1;
                mat = Matrix.mul(mat, one);
                one[u][u] = one[v][v] = 1;
                one[u][v] = one[v][u] = 0;
            }
//            Algo.debugTable(mat);
        }
//        Algo.debugTable(mat);
        mat = Matrix.pow(mat, m);
        long[][] ans = new long[n + 1][n + 1];
        ans[0][n] = 1;
        ans = Matrix.mul(ans, mat);
//        if (m == 0) Arrays.fill(mat[0], 0);
        for (int i = 0; i < n; i++) {
            out.print(ans[0][i]);
            if (i == n - 1) out.println();
            else out.print(' ');
        }
    }

    private long[][] one(int n) {
        long[][] mat = new long[n][n];
        for (int i = 0; i < n; i++) {
            mat[i][i] = 1;
        }
        return mat;
    }

}
