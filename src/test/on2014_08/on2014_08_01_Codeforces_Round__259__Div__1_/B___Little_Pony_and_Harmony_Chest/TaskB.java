package test.on2014_08.on2014_08_01_Codeforces_Round__259__Div__1_.B___Little_Pony_and_Harmony_Chest;



import com.shu_mj.math.Num;
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
        int[] is = in.nextIntArray(n);
        List<Integer> primes = new ArrayList<Integer>();
        boolean[] isPrime = Num.primeTable(60, primes);
        int[] mask = new int[61];
        int ps = primes.size();
        for (int i = 2; i <= 60; i++) {
            for (int j = 0; j < ps; j++) {
                if (i % primes.get(j) == 0) mask[i] |= 1 << j;
            }
        }
        int[][] dp = new int[n + 1][1 << ps];
        int[][] preJ = new int[n + 1][1 << ps];
        int[][] preSt = new int[n + 1][1 << ps];
        Algo.fill(dp, -1);
        Algo.fill(preJ, -1);
        Algo.fill(preSt, -1);
        dp[0][0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < 60; j++) {
                for (int k = 0; k < (1 << ps); k++) if (dp[i][k] != -1 && (k & mask[j]) == 0) {
                    if (dp[i + 1][k | mask[j]] == -1 || dp[i + 1][k | mask[j]] > dp[i][k] + Math.abs(j - is[i])) {
                        dp[i + 1][k | mask[j]] = dp[i][k] + Math.abs(j - is[i]);
                        preJ[i + 1][k | mask[j]] = j;
                        preSt[i + 1][k | mask[j]] = k;
                    }
                }
            }
        }
        int st = -1;
        for (int i = 0; i < (1 << ps); i++) if (dp[n][i] != -1 && (st == -1 || dp[n][i] < dp[n][st])) st = i;
        Algo.debug(dp[n][st]);
        print(dp, preJ, preSt, n, st);
        out.println();
    }

    private void print(int[][] dp, int[][] preJ, int[][] preSt, int i, int st) {
        if (i > 1) print(dp, preJ, preSt, i - 1, preSt[i][st]);
        out.print(preJ[i][st] + " ");
    }
}
