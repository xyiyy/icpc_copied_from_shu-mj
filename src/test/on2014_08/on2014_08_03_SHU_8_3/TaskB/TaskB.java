package test.on2014_08.on2014_08_03_SHU_8_3.TaskB;



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
        int n = in.nextInt();
        boolean[] mul = new boolean[n];
        int[] is = new int[n];
        for (int i = 0; i < n; i++) {
            mul[i] = in.next().charAt(0) == 'x';
            is[i] = in.nextInt();
        }
        P[] ps = new P[n];
        for (int i = 0; i < n; i++) {
            int[] js = Algo.merge(Arrays.copyOfRange(is, i, n), Arrays.copyOfRange(is, 0, i));
            boolean[] ms = Algo.merge(Arrays.copyOfRange(mul, i, n), Arrays.copyOfRange(mul, 0, i));
            ps[i] = new P(work(js, ms), i);
        }
        Arrays.sort(ps);
        out.println(ps[0].ans);
        for (int i = 0; i < n; i++) {
            if (i == 0 || ps[i].ans == ps[0].ans) {
                if (i != 0) out.print(' ');
                out.print(ps[i].id + 1);
            }
        }
        out.println();
    }

    private int work(int[] is, boolean[] ms) {
        int n = is.length;
        int[][] dp = new int[n][n];
        int[][] dp2 = new int[n][n];
        int INF = Integer.MAX_VALUE;
        Algo.fill(dp, -INF);
        Algo.fill(dp2, INF);
        for (int l = 1; l <= n; l++) {
            for (int i = 0; i < n; i++) {
                int j = i + l - 1;
                if (j >= n) break;
                if (i == j) { dp[i][j] = dp2[i][j] = is[i]; continue; }
                for (int k = i; k < j; k++) {
                    int minL = dp2[i][k];
                    int minR = dp2[k + 1][j];
                    int maxL = dp[i][k];
                    int maxR = dp[k + 1][j];
                    int a = calc(minL, minR, ms[k + 1]);
                    int b = calc(minL, maxR, ms[k + 1]);
                    int c = calc(maxL, minR, ms[k + 1]);
                    int d = calc(maxL, maxR, ms[k + 1]);
                    dp[i][j] = max(dp[i][j], a, b, c, d);
                    dp2[i][j] = min(dp2[i][j], a, b, c, d);
                }
            }
        }
        return dp[0][n - 1];
    }

    private int min(int i, int a, int b, int c, int d) {
        return Math.min(i, Math.min(a, Math.min(b, Math.min(c, d))));
    }

    private int max(int i, int a, int b, int c, int d) {
        return Math.max(i, Math.max(a, Math.max(b, Math.max(c, d))));
    }

    private int calc(int a, int b, boolean m) {
        if (m) return a * b;
        else return a + b;
    }

    class P implements Comparable<P> {
        int ans;
        int id;

        P(int ans, int id) {
            this.ans = ans;
            this.id = id;
        }

        @Override
        public int compareTo(P o) {
            if (ans != o.ans) return o.ans - ans;
            return id - o.id;
        }
    }
}
