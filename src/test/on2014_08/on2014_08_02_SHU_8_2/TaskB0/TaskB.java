package test.on2014_08.on2014_08_02_SHU_8_2.TaskB0;



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
        int m = in.nextInt();
        int k = in.nextInt();
        int s = in.nextInt();
        int[] vs = new int[n];
        int[] ws = new int[n];
        for (int i = 0; i < k; i++) {
            vs[i] = in.nextInt();
            ws[i] = in.nextInt();
        }
        /*int[][] dp = new int[2][m + 1];
        Algo.fill(dp, -1);
        dp[1][0] = 0;
        for (int i = 0; i < s; i++) {
            for (int j = 0; j < k; j++) {
                int v = vs[j];
                int w = ws[j];
                for (int u = w; u <= m; u++) if (dp[(i - 1) & 1][u - w] != -1) {
                    if (dp[(i & 1)][u] == -1 || dp[(i & 1)][u] < dp[(i - 1) & 1][u - w] + v) {
                        dp[i & 1][u] = dp[(i - 1) & 1][u - w] + v;
                    }
                }
            }
            for (int u = 0; u <= m; u++) dp[(i + 1) & 1][u] = dp[(i & 1)][u];
        }
        int res = -1;
        for (int i = m; i >= 0; i--) if (dp[(s - 1) & 1][i] >= n) res = i;
        if (res == -1) out.println(-1);
        else out.println(m - res);*/
        int[] dp = new int[m + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int i = 0; i < s; i++) {
            for (int j = m; j >= 0; j--) {
                for (int u = 0; u < k; u++) if (j - ws[u] >= 0 && dp[j - ws[u]] != -1) {
                    if (dp[j] == -1 || dp[j] < dp[j - ws[u]] + vs[u]) {
                        dp[j] = dp[j - ws[u]] + vs[u];
                    }
                }
            }
        }
        for (int i = 0; i <= m; i++) {
            if (dp[i] >= n) {
                out.println(m - i);
                return ;
            }
        }
        out.println(-1);
    }
}
