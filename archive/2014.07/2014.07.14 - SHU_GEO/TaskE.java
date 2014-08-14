package main;

import com.shu_mj.ds.LongSegMax;
import com.shu_mj.tpl.Algo;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.Arrays;

public class TaskE {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        while (in.hasNext()) {
            solve();
        }
    }

    private void solve() {
        int n = in.nextInt();
        int w = in.nextInt();
        int h = in.nextInt();
        long[] xs = new long[n * 2];
        P[] ps = new P[n];
        for (int i = 0; i < n; i++) {
            long x = in.nextInt();
            long y = in.nextInt();
            int c = in.nextInt();
            ps[i] = new P(x, y, c);
            xs[i * 2] = x;
            xs[i * 2 + 1] = x + w;
        }
        Arrays.sort(ps);
        Arrays.sort(xs);
        xs = Algo.unique(xs);
        LongSegMax seg = new LongSegMax(xs.length);
        seg.update(0, xs.length, 0, 0);
        long ans = 0;
        for (int i = 0, j = 0, k = 0; i < n; ) {
            long y = ps[i].y;
            while (i < n && ps[i].y == y) i++;
            while (j < n && ps[j].y < y + h) {
                int x0 = Algo.lowerBound(xs, ps[j].x);
                int x1 = Algo.lowerBound(xs, ps[j].x + w);
                seg.update(x0, x1, 1, ps[j].c);
                j++;
            }
            while (k < n && ps[k].y < y) {
                int x0 = Algo.lowerBound(xs, ps[k].x);
                int x1 = Algo.lowerBound(xs, ps[k].x + w);
                seg.update(x0, x1, 1, -ps[k].c);
                k++;
            }
            ans = Math.max(ans, seg.ls[1]);
        }
        out.println(ans);
    }

    class P implements Comparable<P> {
        long x;
        long y;
        int c;

        P(long x, long y, int c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }

        @Override
        public int compareTo(P o) {
            return y < o.y ? -1 : 1;
        }
    }
}
