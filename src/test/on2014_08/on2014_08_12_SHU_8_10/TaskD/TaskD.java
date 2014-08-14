package test.on2014_08.on2014_08_12_SHU_8_10.TaskD;



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

    final long M = (long) (1e9 + 7);
    void run() {
        int n = in.nextInt();
        int k = in.nextInt() - 1;
        long[][] dp = new long[2][(1 << k) + 1];
        dp[1][0] = 1;
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            Arrays.fill(dp[i & 1], 0);
            for (int j = 0; j <= (1 << k); j++) {
                long d = dp[(i - 1) & 1][j];
                if (x == 0 || x == 2) {
                    dp[i & 1][Math.min(1 << k, j + 1)] += d;
                    dp[i & 1][Math.min(1 << k, j + 1)] %= M;
                }
                if (x == 0 || x == 4) {
                    int e = j % 2;
                    if (e == 1) {
                        dp[i & 1][2] += d;
                        dp[i & 1][2] %= M;
                    } else {
                        dp[i & 1][Math.min(1 << k, j + 2)] += d;
                        dp[i & 1][Math.min(1 << k, j + 2)] %= M;
                    }
                }
            }
        }
        out.println(dp[(n - 1) & 1][1 << k]);
    }
}
