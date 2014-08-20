package main;

import com.shu_mj.math.Matrix;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task1006 {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        while (in.hasNext()) {
            int n = in.nextInt();
            int k = in.nextInt();
            if (n == 0 && k == 0) break;
            solve(n, k);
        }
    }

    private void solve(int n, int k) {
        int[][] A = new int[n][k];
        int[][] B = new int[k][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                A[i][j] = in.nextInt();
            }
        }
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < n; j++) {
                B[i][j] = in.nextInt();
            }
        }
        int[][] D = new int[k][k];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                for (int u = 0; u < n; u++) {
                    D[i][j] += B[i][u] * A[u][j];
                    D[i][j] %= 6;
                }
            }
        }
        D = Matrix.pow(D, n * n - 1, 6);
        int[][] E = new int[n][k];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                for (int u = 0; u < k; u++) {
                    E[i][j] += A[i][u] * D[u][j];
                    E[i][j] %= 6;
                }
            }
        }
        int[][] F = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int u = 0; u < k; u++) {
                    F[i][j] += E[i][u] * B[u][j];
                    F[i][j] %= 6;
                }
            }
        }
        long sum = 0;
        for (int[] is : F) sum += Algo.sum(is);

        out.println(sum);
    }
}
