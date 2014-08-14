package test.on2014_08.on2014_08_03_SHU_8_4.TaskB;



import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskB {
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
        double[] ps = in.nextDoubleArray(n);
        double[] dp = new double[1 << n];
        dp[(1 << n) - 1] = 0;
        for (int i = (1 << n) - 2; i >= 0; i--) {
            double p = 0;
            double t = 1;
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {

                } else {
                    p += ps[j];
                    t += ps[j] * dp[i ^ (1 << j)];
                }
            }
            dp[i] = t / p;
        }
        out.println(dp[0]);
    }
}
