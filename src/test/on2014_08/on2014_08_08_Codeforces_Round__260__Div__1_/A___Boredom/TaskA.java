package test.on2014_08.on2014_08_08_Codeforces_Round__260__Div__1_.A___Boredom;



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
        int N = (int) (1e5);
        long[] ls = new long[N + 1];
        for (int i = 0; i < n; i++) {
            int k = in.nextInt();
            ls[k] += k;
        }
        long[] dp = new long[N + 1];
        dp[0] = 0;
        for (int i = 1; i <= N; i++) {
            dp[i] = Math.max(dp[i - 1], i > 1 ? dp[i - 2] + ls[i] : ls[i]);
        }
        out.println(dp[N]);
    }
}
