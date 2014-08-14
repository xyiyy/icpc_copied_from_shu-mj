package test.on2014_08.on2014_08_03_SHU_8_4.TaskD;



import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskD {
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
        int[][] iss = in.nextIntMatrix(n, n);
        int[][] dp = new int[2][1 << n];
        int[] sum = new int[1 << n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < (1 << n); j++) {
                if (fit(j)) {
                    if (j == 0) sum[j] = 0;
                    else sum[j] = sum[j ^ (j & -j)] + iss[i][Integer.numberOfTrailingZeros(j)];
                    dp[i & 1][j] = sum[j] + dp[(i - 1) & 1][j ^ ((1 << n) - 1)];
                }
                if (j != 0) {
                    int left = j ^ (j & -j), middle = j & -j, right = 0;
                    do {
                        dp[i & 1][j] = Math.max(dp[i & 1][j], dp[i & 1][left | right]);
                        right = right ^ middle;
                        middle = left & -left;
                        left = left ^ middle;
                    } while (right != j);
                }
            }
        }
        out.println(dp[(n - 1) & 1][(1 << n) - 1]);
    }

    private boolean fit(int s) {
        return (s & (s >> 1)) == 0;
    }
}
