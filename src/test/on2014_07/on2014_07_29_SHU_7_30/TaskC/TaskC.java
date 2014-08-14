package test.on2014_07.on2014_07_29_SHU_7_30.TaskC;



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
        int t = in.nextInt();
        while (t-- != 0) solve();
    }
    void solve() {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] dp = new int[m + 1];
        int[] ds = in.nextIntArray(n);
        int[] ws = in.nextIntArray(n);
        for (int i = 0; i < n; i++) {
            int w = ws[i];
            int d = ds[i];
            for (int j = m; j - w >= 0; j--) {
                dp[j] = Math.max(dp[j], dp[j - w] + d);
            }
        }
        out.println(Algo.max(dp));
    }

}
