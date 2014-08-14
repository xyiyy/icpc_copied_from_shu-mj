package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskA {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int[][] iss = in.nextIntMatrix(n, n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0) iss[i][j] += iss[i - 1][j];
                if (j > 0) iss[i][j] += iss[i][j - 1];
                if (i > 0 && j > 0) iss[i][j] -= iss[i - 1][j - 1];
            }
        }
        int ans = iss[0][0];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = i; k < n; k++) {
                    for (int l = j; l < n; l++) {
                        int sum = iss[k][l];
                        if (i > 0) sum -= iss[i - 1][l];
                        if (j > 0) sum -= iss[k][j - 1];
                        if (i > 0 && j > 0) sum += iss[i - 1][j - 1];
                        ans = Math.max(ans, sum);
                    }
                }
            }
        }
        out.println(ans);
    }
}
