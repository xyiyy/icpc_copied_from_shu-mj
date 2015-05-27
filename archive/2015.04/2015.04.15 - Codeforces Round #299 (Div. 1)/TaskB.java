package main;

import com.shu_mj.ds.Hash2;
import com.shu_mj.ds.Intervals;
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
        int m = in.nextInt();
        char[] p = in.next().toCharArray();
        int pn = p.length;
        int[] y = in.nextIntArray(m);
        for (int i = 0; i < m; i++) y[i]--;
        if (m == 0) {
            out.println(Num.pow(26, n, (long) (1e9 + 7)));
            return ;
        }
        Hash2 h = new Hash2(pn);
        long[] hs = h.build(p);
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            if (i > 0 && y[i - 1] > y[i] - pn) {
                int l = y[i - 1] + pn - y[i];
                if (h.get(hs, 0, l) != h.get(hs, pn - l, pn)) {
                    out.println(0);
                    return ;
                }
            }
            if (i > 0) {
                cnt += Math.max(0, y[i] - (y[i - 1] + pn));
            }
        }
        cnt += y[0];
        cnt += Math.max(0, n - (y[m - 1] + pn));
        out.println(Num.pow(26, cnt, (long) (1e9 + 7)));
    }

}
