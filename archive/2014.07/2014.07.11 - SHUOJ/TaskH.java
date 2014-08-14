package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskH {
    Scanner in;
    PrintWriter out;
    private long[][] dpn;
    private long[][] dps;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        init();
        for (int i = 1; i <= t; i++) {
            out.printf("Case %d:%n", i);
            solve();
        }
    }

    long[] ten = new long[20];
    private void init() {
        dpn = new long[20][1000];
        for (int i = 0; i < 20; i++) ten[i] = (i == 0 ? 1 : ten[i - 1] * 10 % M);
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                int oj = oes[j];
                if (i == 0) dpn[i][oj + 500] = 1;
                else {
                    for (int k = -200; k < 200; k++) {
                        dpn[i][oj + k + 500] += dpn[i - 1][k + 500];
                    }
                }
            }
        }
        dps = new long[20][1000];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                int oj = oes[j];
                if (i == 0) dps[i][oj + 500] = j;
                else {
                    for (int k = -200; k < 200; k++) {
                        dps[i][oj + k + 500] += dps[i - 1][k + 500] + j * ten[i] % M * (dpn[i - 1][k + 500] % M) % M;
                        dps[i][oj + k + 500] %= M;
                    }
                }
            }
        }
    }

    int[] oes = {0, -1, 2, -3, 4, -5, 6, -7, 8, -9};
    void solve() {
        long l = in.nextLong();
        long r = in.nextLong();
        int m = in.nextInt();
        P ans = calc(r, m);
        if (l != 0) {
            P ansL = calc(l - 1, m);
            ans = ans.sub(ansL);
        }
        out.println(ans.num + " " + ans.sum);
    }


    private P calc(long x, int m) {
        char[] csc = Long.toString(x).toCharArray();
        Algo.reverse(csc);
        int n = csc.length;
        int[] cs = new int[n];
        for (int i = 0; i < n; i++) cs[i] = csc[i] - '0';
        long cnt = 0;
        int crt = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < cs[i]; j++) {
                if (i != 0) cnt +=  dpn[i - 1][m - crt - oes[j] + 500];
                else cnt += (oes[j] == m - crt ? 1 : 0);
            }
            crt += oes[cs[i]];
        }
        cnt += (crt == m ? 1 : 0);
        long sum = 0;
        crt = 0;
        long crtSum = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < cs[i]; j++) {
                if (i != 0) {
                    sum += dps[i - 1][m - crt - oes[j] + 500] + (crtSum + j * ten[i] % M) % M * (dpn[i - 1][m - crt - oes[j] + 500] % M) % M;
                    sum %= M;
                } else {
                    sum += (oes[j] == m - crt ? crtSum + j : 0);
                }
            }
            crt += oes[cs[i]];
            crtSum += cs[i] * ten[i] % M;
            crtSum %= M;
        }
        sum += (crt == m ? crtSum : 0);
        return new P(cnt, sum % M);
    }

    final long M = (long) (1e8 + 7);

    class P {
        final long num;
        final long sum;

        P(long num, long sum) {
            this.num = num;
            this.sum = sum;
        }

        P sub(P p) {
            return new P(num - p.num, ((sum - p.sum) % M + M) % M);
        }

        P add(P p) {
            return new P(num + p.num, (sum + p.sum) % M);
        }
    }
}
