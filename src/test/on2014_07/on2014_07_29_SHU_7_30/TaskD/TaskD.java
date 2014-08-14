package test.on2014_07.on2014_07_29_SHU_7_30.TaskD;



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
            int n = in.nextInt();
            if (n == 0) break;
            solve(n);
        }
    }

    private void solve(int n) {
        int[] vs = in.nextIntArray(n);
        Arrays.sort(vs);
        int m = in.nextInt();
        boolean[] dp = new boolean[m + 101];
        dp[m + 100] = true;
        for (int v : vs) {
            for (int i = -50; i <= m; i++) {
                if (i + v >= 5 && i + v <= m && dp[i + v + 100]) {
                    dp[i + 100] = true;
                }
            }
        }
        int ans = m;
        for (int i = m; i >= -50; i--) if (dp[i + 100]) ans = i;
        out.println(ans);
    }
}
