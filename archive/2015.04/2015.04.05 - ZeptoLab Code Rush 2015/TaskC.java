package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskC {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        long c = in.nextInt();
        long hr = in.nextInt();
        long hb = in.nextInt();
        long wr = in.nextInt();
        long wb = in.nextInt();
        out.println(Math.max(go(c, hr, hb, wr, wb), go(c, hb, hr, wb, wr)));
    }

    private long go(long c, long hr, long hb, long wr, long wb) {
        long top = c / wr;
        long res = 0;
        for (int i = 0; i < 1000000; i++) {
            long y = top - i;
            long x = (c - y * wr) / wb;
            if (x >= 0 && y >= 0) res = Math.max(res, x * hb + y * hr);
        }
        return res;
    }
}
