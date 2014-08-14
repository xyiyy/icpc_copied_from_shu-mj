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
        while (in.hasNext())
            solve();
    }

    private void solve() {
        int n = in.nextInt();
        int[] ls = new int[n];
        int[] cs = new int[n];
        double[] ps = new double[n];
        for (int i = 0; i < n; i++) {
            ls[i] = in.nextInt();
            cs[i] = in.nextInt();
        }
        long sum = Algo.sum(cs);
        for (int i = 0; i < n; i++) {
            ps[i] = cs[i] * 1.0 / sum;
        }
        Item[] is = new Item[n];
        for (int i = 0; i < n; i++) {
            is[i] = new Item(ls[i], ps[i]);
        }
        Arrays.sort(is);
        double ans = 0;
        double last = 0;
        for (int i = 0; i < n; i++) {
            last += is[i].p * is[i].len;
            ans += last;
        }
        out.printf("%.4f%n", ans);
    }
    class Item implements Comparable<Item> {
        int len;
        double p;

        Item(int len, double p) {
            this.len = len;
            this.p = p;
        }

        @Override
        public int compareTo(Item o) {
            return Double.compare(p * len, o.p * o.len);
        }
    }
}
