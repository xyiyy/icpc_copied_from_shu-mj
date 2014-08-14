package test.on2014_08.on2014_08_04_SHU_8_6.TaskA;



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
        while (in.hasNext()) {
            solve();
        }
    }

    private void solve() {
        int r = in.nextInt();
        int c = in.nextInt();
        double[][] mat = in.nextDoubleMatrix(r, c * 3);
        double[][] dp = new double[r][c];
        dp[r - 1][c - 1] = 0;
        for (int i = r - 1; i >= 0; i--) {
            for (int j = c - 1; j >= 0; j--) {
                if (i == r - 1 && j == c - 1) continue;
                double p = 1;
                if (i < r - 1) p += mat[i][j * 3 + 2] * dp[i + 1][j];
                if (j < c - 1) p += mat[i][j * 3 + 1] * dp[i][j + 1];
                if (mat[i][j * 3] == 1) continue;
                dp[i][j] = p / (1 - mat[i][j * 3]);
            }
        }
        out.printf("%.3f%n", dp[0][0] * 2);
    }
}
