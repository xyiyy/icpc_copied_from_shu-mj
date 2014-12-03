package main;

import com.shu_mj.ds.Hash;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class USACO {
    /*
    LANG: JAVA
    TASK: fracdec
     */
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int d = in.nextInt();
        String res = (n / d) + "." + calc(n % d, d);
        for (int i = 0; i < res.length(); i++) {
            out.print(res.charAt(i));
            if (i % 76 == 75) out.println();
        }
        if (res.length() % 76 != 75) out.println();
    }

    int L = 1024000;
    private String calc(int n, int d) {
        int[] is = new int[L];
        for (int i = 0; i < L; i++) {
            n *= 10;
            is[i] = n / d;
            n %= d;
            if (n == 0) {
                StringBuilder sb = new StringBuilder(i + 1);
                for (int j = 0; j <= i; j++) sb.append(is[j]);
                return sb.toString();
            }
        }
        Hash H = new Hash(L);
        long[] hs = H.build(is);
        for (int i = 0; ; i++) {
            for (int j = 1; j < L / 3; j++) {
                if (fit(H, hs, i, j)) {
                    StringBuilder sb = new StringBuilder(i + j + 10);
                    for (int k = 0; k < i; k++) sb.append(is[k]);
                    sb.append('(');
                    for (int k = i; k < i + j; k++) sb.append(is[k]);
                    sb.append(')');
                    return sb.toString();
                }
            }
        }
    }

    private boolean fit(Hash H, long[] hs, int b, int l) {
        for (int i = b; i + l < L - 10; i += l) {
            if (H.get(hs, b, b + l) != H.get(hs, i, i + l)) return false;
        }
        return true;
    }
}
