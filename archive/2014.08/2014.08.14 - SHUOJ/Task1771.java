package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task1771 {
    Scanner in;
    PrintWriter out;
    private long[] ten;
    private long[][][] dpCount;
    private long[][][] dpSum;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        init();
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            out.printf("Case %d:%n", i);
            solve();
        }
    }

    private void solve() {
        long l = in.nextLong();
        long r = in.nextLong();
        int m = in.nextInt();
        P ansR = solve(r, m);
        if (l != 0) {
            P ansL = solve(l - 1, m);
            ansR.count -= ansL.count;
            ansR.sum -= ansL.sum;
        }
        ansR.sum %= M;
        ansR.sum += M;
        ansR.sum %= M;
        out.println(ansR.count + " " + ansR.sum);
    }

    private void init() {
        ten = new long[20];
        for (int i = 0; i < 20; i++) ten[i] = (i == 0 ? 1 : ten[i - 1] * 10);
        // dpCount[长度][最高位][奇偶和]
        dpCount = new long[20][10][400];
        dpSum = new long[20][10][400];
        for (int i = 0; i < 10; i++) {
            dpCount[1][i][g(i) * i + 200] = 1;
            dpSum[1][i][g(i) * i + 200] = i;
        }
        for (int i = 1; i < 19; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 400; k++) if (dpCount[i][j][k] != 0) {
                    for (int d = 0; d < 10; d++) {
                        dpCount[i + 1][d][k + g(d) * d] += dpCount[i][j][k];
                        dpSum[i + 1][d][k + g(d) * d] += dpCount[i][j][k] % M * d % M * (ten[i] % M) + dpSum[i][j][k];
                        dpSum[i + 1][d][k + g(d) * d] %= M;
                    }
                }
            }
        }
    }

    private P solve(long n, int m) {
        n++;
        char[] cs = Long.toString(n).toCharArray();
        long count = 0;
        long sum = 0;
        int len = cs.length;
        long oes = 0;
        long crt = 0;
        for (int i = 0; i < len; i++) {
            int d = cs[i] - '0';
            for (int j = 0; j < d; j++) {
                count += dpCount[len - i][j][((int) (m - oes + 200))];
                sum += dpSum[len - i][j][((int) (m - oes + 200))] + dpCount[len - i][j][((int) (m - oes + 200))] % M * (crt % M) % M;
                sum %= M;
            }
            oes += g(d) * d;
            crt += d * ten[len - i - 1];
        }
        return new P(count, sum);
    }

    private int g(int d) {
        return (d & 1) != 0 ? -1 : 1;
    }

    final long M = (long) (1e8 + 7);
    class P {
        long count;
        long sum;

        P(long count, long sum) {
            this.count = count;
            this.sum = sum;
        }
    }
}
