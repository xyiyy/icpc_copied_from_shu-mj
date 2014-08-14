package test.on2014_08.on2014_08_04_SHU_8_6.TaskC;



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
        while (t-- != 0)
            solve();
    }

    private void solve() {
        char[] cs = in.next().toCharArray();
        int n = cs.length;
        int[] st = new int[1 << n];
        int s = 0;
        char[] ps = new char[n];
        for (int i = 1; i < (1 << n); i++) {
            int p = 0;
            for (int j = 0; j < n; j++) if ((i & (1 << j)) != 0) {
                ps[p++] = cs[j];
            }
            char[] ps2 = ps.clone();
            Algo.reverse(ps2, 0, p);
            if (Algo.equalsOfRange(ps, 0, p, ps2, 0, p)) st[s++] = i;
        }
        int[] dp = new int[1 << n];
        Arrays.fill(dp, n);
        dp[0] = 0;
        for (int i = 1; i < (1 << n); i++) {
            for (int j = 0; j < s && st[j] <= i; j++) if ((st[j] & ~i) == 0) {
                dp[i] = Math.min(dp[i], dp[i & ~st[j]] + 1);
            }
        }
        out.println(dp[(1 << n) - 1]);
    }
}
