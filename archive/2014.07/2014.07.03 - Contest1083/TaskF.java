package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskF {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int cs = 1;
        while (in.hasNext()) {
            out.printf("case %d: ", cs++);
            solve();
        }
    }

    private void solve() {
        int n = in.nextInt();
        double c = in.nextInt();
        Item[] is = new Item[n];
        double[] vs = in.nextDoubleArray(n);
        double[] ws = in.nextDoubleArray(n);
        for (int i = 0; i < n; i++) {
            is[i] = new Item(vs[i], ws[i]);
        }
        Arrays.sort(is);
        double ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            double r = Math.min(c, is[i].w);
            c -= r;
            ans += r / is[i].w * is[i].v;
        }
        out.printf("%.2f%n", ans);
    }
    class Item implements Comparable<Item> {
        double v;
        double w;

        @Override
        public int compareTo(Item o) {
            return Double.compare(v / w, o.v / o.w);
        }

        Item(double v, double w) {
            this.v = v;
            this.w = w;
        }
    }
}
