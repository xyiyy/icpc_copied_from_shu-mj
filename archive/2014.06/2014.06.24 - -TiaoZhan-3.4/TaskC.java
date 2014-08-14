package main;

import com.shu_mj.tpl.Algo;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;

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
        int k = in.nextInt();
        int m = in.nextInt();
        int[][] A = in.nextIntMatrix(n, n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] %= m;
            }
        }
        int[][][][] mat = {
                {A, A},
                {new int[n][n], one(n)}
        };
        mat = pow(mat, k, m);
        A = mat[0][1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                out.print(A[i][j] + " ");
            }
            out.println(A[i][n - 1]);
        }
    }

    private int[][][][] pow(int[][][][] mat, int k, int mod) {
        int n = mat[0][0].length;
        int[][][][] res = {
                {one(n), new int[n][n]},
                {new int[n][n], one(n)}
        };
        while (k != 0) {
            if ((k & 1) != 0) res = mul(res, mat, mod);
            mat = mul(mat, mat, mod);
            k >>= 1;
        }
        return res;
    }

    private int[][][][] mul(int[][][][] a, int[][][][] b, int mod) {
        int n = a[0][0].length;
        int[][][][] res = new int[2][2][n][n];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    res[i][j] = add(res[i][j], mul(a[i][k], b[k][j], mod), mod);
                }
            }
        }
        return res;
    }

    private int[][] add(int[][] a, int[][] b, int mod) {
        int n = a.length;
        int[][] res = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = a[i][j] + b[i][j];
                res[i][j] %= mod;
            }
        }
        return res;
    }

    private int[][] mul(int[][] a, int[][] b, int mod) {
        int n = a.length;
        int[][] res = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    res[i][j] += a[i][k] * b[k][j];
                    res[i][j] %= mod;
                }
            }
        }
        return res;
    }


    int[][] one(int n) {
        int[][] res = new int[n][n];
        for (int i = 0; i < n; i++) res[i][i] = 1;
        return res;
    }
}
