package test.on2014_07.on2014_07_31_DX_4.Task1009;



import com.shu_mj.math.Num;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task1009 {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        while (t-- != 0) {
            solve();
        }
    }

    private void solve() {
        int n = in.nextInt();
        int[] is = in.nextIntArray(n);
        if (allZero(is)) {
            out.println(0);
            return ;
        }
        List<Integer> ls = new ArrayList<Integer>();
        long res = Algo.sum(is);
        for (int i = 0; i < n; i++) {
            if (is[i] == 0) {
                res += Math.max(goLeft(is, i), goRight(is, i));
            } else {
                ls.add(is[i]);
            }
        }
        is = Algo.unBox(ls.toArray(new Integer[0]));
        res += calcForce(is);
        out.println(res);
    }

    private long calcForce(int[] is) {
        int n = is.length;
        long[][] dp = new long[n][n];
        long[][] gs = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (i == j) gs[i][j] = is[i];
                else gs[i][j] = Num.gcd(gs[i][j - 1], is[j]);
            }
        }
        int[][] sl = new int[n][n];
        int[][] sr = new int[n][n];
        for (int l = 1; l <= n; l++) {
            for (int i = 0; i < n; i++) {
                int j = i + l - 1;
                if (j == n) break;
                if (l == 1) {
                    dp[i][j] = 0;
                    sl[i][j] = i;
                    sr[i][j] = i;
                } else {

                    for (int k = i; k + 1 <= j; k++) {
                        dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k + 1][j] + gs[i][j]);
                    }
                }
            }
        }
        return dp[0][n - 1];
    }

    private long calc(int[] is) {
        int n = is.length;
        List<Integer> ls = new ArrayList<Integer>();
        for (int i : is) ls.add(i);
        long res = 0;
        int[] gs = new int[n];
        for (int i = 0; i < n - 1; i++) {
            int m = -1;
            for (int j = 1; j < ls.size(); j++) {
                gs[j] = Num.gcd(ls.get(j - 1), ls.get(j));
            }
            for (int j = 1; j < ls.size(); j++) {
                if (j == 1) m = j;
                else {
                    if (j == ls.size() - 1) {
                        if (gs[j] > gs[m]) m = j;
                    } else {
                        if (gs[j] > gs[m] && gs[j - 1] + gs[j + 1] <= gs[j] + Math.max(Num.gcd(gs[j - 1], gs[j]), Num.gcd(gs[j], gs[j + 1])))
                            m = j;
                    }
                }
            }
            res += gs[m];
            ls.remove(m - 1);
            ls.set(m - 1, gs[m]);
        }
        return res;
    }

    private int goRight(int[] is, int i) {
        int res = 0;
        while (i < is.length) {
            res = Math.max(is[i], res);
            if (is[i] != 0) return res;
            i++;
        }
        return res;
    }

    private int goLeft(int[] is, int i) {
        int res = 0;
        while (i >= 0) {
            res = Math.max(is[i], res);
            if (is[i] != 0) return res;
            i--;
        }
        return res;
    }

    private boolean allZero(int[] is) {
        for (int i : is) if (i != 0) return false;
        return true;
    }

    private long dfs(int[] is, int b, int e) {
        if (b >= e) {
            return 0;
        }
        int[] gs = new int[e - b + 1];
        gs[b - b] = is[b];
        for (int i = b + 1; i <= e; i++) gs[i - b] = Num.gcd(gs[i - 1 - b], is[i]);
        for (int i = b; i <= e; i++) if (i == e - 1 || gs[i - b] == gs[e - b]) {
            long res1 = dfs(is, b, i);
            long res2 = dfs(is, i + 1, e);
            long res =  res1 + res2 + gs[e - b];
            return res;
        }
        return 0;
    }
}
