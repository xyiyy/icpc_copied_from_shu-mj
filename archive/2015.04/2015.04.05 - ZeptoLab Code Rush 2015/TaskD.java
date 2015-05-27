package main;

import com.shu_mj.ds.Hash2;
import com.shu_mj.ds.KMP;
import com.shu_mj.ds.SuffixArray;
import com.shu_mj.ds.SuffixArrayS;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskD {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int k = in.nextInt();
        char[] cs = in.next().toCharArray();
        Hash2 h = new Hash2(n);
        long[] hs = h.build(cs);
        SuffixArrayS sa = new SuffixArrayS(cs);
        sa.buildHs();
        sa.buildRMQ();
        int[] res = new int[n + 1];
        for (int len = 1; len * k <= n; len++) {
            if (fit(n, k, len, hs, h)) {
                int l = len * k == n ? 0 : sa.getLCP(0, len * k);
                l = Math.min(l, len);
                res[len * k - 1]++;
                res[len * k + l]--;
            }
        }
        for (int i = 0, crt = 0; i < n; i++) {
            crt += res[i];
            if (crt > 0) {
                out.print('1');
            } else {
                out.print('0');
            }
        }
        out.println();
    }

    private boolean fit2(int n, int k, int len, long[] hs, Hash2 h) {
        if (k == 1) return true;
        for (int i = 1; ; i *= 2) {
            if (h.get(hs, 0, len * i) != h.get(hs, len * i, len * i * 2)) return false;
            if (i * 4 > k) return h.get(hs, 0, len * i * 2) == h.get(hs, len * k - len * i * 2, len * k);
        }
    }
    private boolean fit(int n, int k, int len, long[] hs, Hash2 h) {
        long bh = h.get(hs, 0, len);
        for (int i = 1; i < k; i++) {
            if (bh != h.get(hs, i * len, i * len + len)) return false;
        }
        return true;
    }
}
