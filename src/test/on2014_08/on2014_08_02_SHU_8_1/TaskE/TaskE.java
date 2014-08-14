package test.on2014_08.on2014_08_02_SHU_8_1.TaskE;



import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskE {
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
        int w = -in.nextInt() + in.nextInt();
        int n = in.nextInt();
        int[] vs = new int[n];
        int[] ws = new int[n];
        for (int i = 0; i < n; i++) {
            vs[i] = in.nextInt();
            ws[i] = in.nextInt();
        }
        if (w < 0) { out.println("This is impossible."); return ; }
        int[] dp = new int[w + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = ws[i]; j <= w; j++) {
                if (dp[j - ws[i]] != -1 && (dp[j] == -1 || dp[j] > dp[j - ws[i]] + vs[i])) {
                    dp[j] = dp[j - ws[i]] + vs[i];
                }
            }
        }
        if (dp[w] == -1) out.println("This is impossible.");
        else out.println("The minimum amount of money in the piggy-bank is " + dp[w] + ".");
    }
}
