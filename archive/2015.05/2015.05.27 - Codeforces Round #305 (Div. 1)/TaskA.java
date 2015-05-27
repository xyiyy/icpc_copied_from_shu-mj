package main;

import com.shu_mj.math.Num;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskA {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int m = in.nextInt();
        int h1 = in.nextInt();
        int a1 = in.nextInt();
        int x1 = in.nextInt();
        int y1 = in.nextInt();
        int h2 = in.nextInt();
        int a2 = in.nextInt();
        int x2 = in.nextInt();
        int y2 = in.nextInt();
        int s0 = get0(m, h1, a1, x1, y1);
        int s1 = get1(m, a1, x1, y1);
        int t0 = get0(m, h2, a2, x2, y2);
        int t1 = get1(m, a2, x2, y2);
        Algo.debug(s0, s1, t0, t1);
        if (s0 == -1 || t0 == -1) {
            out.println(-1);
            return ;
        }
        if (s1 == -1 && t1 == -1) {
            out.println(s0 == t0 ? s0 : -1);
            return ;
        }
        if (s1 == -1 || t1 == -1) {
            if (s1 == -1) {
                out.println(s0 >= t0 && (s0 - t0) % t1 == 0 ? s0 : -1);
            } else {
                out.println(t0 >= s0 && (t0 - s0) % s1 == 0 ? t0 : -1);
            }
            return ;
        }
//        long[] res = Num.congruence(new long[] { 1, 1 }, new long[] { s0, t0 }, new long[] { s1, t1 });
//        if (res == null) {
//            out.println(-1);
//        } else {
//            out.println(res[0]);
//        }
        if (s1 < t1) {
            { int t = s0; s0 = t0; t0 = t; }
            { int t = s1; s1 = t1; t1 = t; }
        }
        for (long i = 0; i < 5000000; i++) {
            if (i * s1 + s0 >= t0 && (i * s1 + s0 - t0) % t1 == 0) {
                out.println(i * s1 + s0);
                return ;
            }
        }
        out.println(-1);
    }

    private int get1(int m, int a1, int x1, int y1) {
        boolean[] vis = new boolean[m + 1];
        for (int i = 1, a2 = (int) ((long) a1 * x1 % m + y1) % m; ; i++) {
            if (a2 == a1) return i;
            if (vis[a2]) return -1;
            vis[a2] = true;
            a2 = (int) ((long) x1 * a2 % m + y1) % m;
        }
    }

    private int get0(int m, int h1, int a1, int x1, int y1) {
        boolean[] vis = new boolean[m + 1];
        for (int i = 0; ; i++) {
            if (h1 == a1) return i;
            if (vis[h1]) return -1;
            vis[h1] = true;
            h1 = (int) ((long) x1 * h1 % m + y1) % m;
        }
    }
}
