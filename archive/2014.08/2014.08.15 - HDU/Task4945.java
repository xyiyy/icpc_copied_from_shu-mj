package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task4945 {
    Scanner in;
    PrintWriter out;
    private long[] two;
    private long[] p;
    private long[] rP;
    private int[] logTwo;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int cs = 1;
        two = new long[100010];
        for (int i = 0; i < two.length; i++) {
            if (i == 0) two[i] = 1;
            else two[i] = two[i - 1] * 2 % M;
        }
        p = new long[100010];
        for (int i = 0; i < p.length; i++) {
            if (i == 0) p[i] = 1;
            else p[i] = p[i - 1] * i % M;
        }
        rP = new long[100010];
        for (int i = 100009; i >= 0; i--) {
            if (i == 100009) rP[i] = BigInteger.valueOf(p[i]).modInverse(BigInteger.valueOf(M)).longValue();
            else rP[i] = rP[i + 1] * (i + 1) % M;
        }
        logTwo = new int[2049];
        Arrays.fill(logTwo, -1);
        for (int i = 1, j = 0; i <= 2048; i <<= 1, j++) {
            logTwo[i] = j;
        }
        while (in.hasNext()) {
            int n = in.nextInt();
            if (n == 0) break;
            out.printf("Case #%d: ", cs++);
            solve(n);
        }
    }
    final long M = 998244353;
    private void solve(int n) {
        int[] cs = new int[12];
        int other = 0;
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            if (logTwo[x] != -1) cs[logTwo[x]]++;
            else other++;
        }
        long[][] dp = new long[13][2049];
        dp[12][0] = 1;
        for (int i = 11; i >= 0; i--) {
            int l = (int) Math.min(cs[i], 2048 / two[i]);
            long[] C = new long[l + 1];
            for (int j = 0; j <= l; j++) C[j] = C(cs[i], j);
            for (int j = 0; j <= 2048; j++) if (dp[i + 1][j] != 0) {
                long sum = 0;
                long d = dp[i + 1][j];
                for (int k = 0; k <= cs[i]; k++) {
                    int crt = j + k * (int) two[i];
                    long c = C[k];
                    if (crt >= 2048) {
                        dp[i][2048] += d * (two[cs[i]] - sum) % M;
                        dp[i][2048] %= M;
                        break;
                    } else {
                        dp[i][crt] += d * c % M;
                        dp[i][crt] %= M;
                    }
                    sum += c;
                    sum %= M;
                }
            }
        }
        long res = dp[0][2048] * two[other] % M;
        res %= M;
        res += M;
        res %= M;
        out.println(res);
    }

    private long C(int m, int n) {
        return p[m] * rP[n] % M * rP[m - n] % M;
    }
}