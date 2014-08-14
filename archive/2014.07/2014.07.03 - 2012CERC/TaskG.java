package main;

import com.shu_mj.ds.BIT;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskG {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        while (t-- != 0)
            solve();
    }

    private void solve() {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] xs = new int[n];
        int[] ys = new int[n];
        int[] cs = new int[n];
        for (int i = 0; i < n; i++) {
            xs[i] = in.nextInt();
            ys[i] = in.nextInt();
            cs[i] = in.nextInt() - 1;
        }
        int xn = hash(xs);
        int yn = hash(ys);
        P[] ps = new P[n];
        for (int i = 0; i < n; i++) ps[i] = new P(xs[i], ys[i], cs[i]);
        Arrays.sort(ps);
        BIT bit = new BIT(xn);
        TreeSet<Integer>[] ss = new TreeSet[k];
        for (int i = 0; i < k; i++) {
            ss[i] = new TreeSet<Integer>();
            ss[i].add(-1);
            ss[i].add(xn);
        }
        int ans = 0;
        for (int i = 0; i < n; ) {
            int j = i + 1;
            while (j < n && ps[j].y == ps[i].y) j++;
            for (int u = i; u < j; u++) {
                int c = ps[u].c, x = ps[u].x;
                int left = ss[c].lower(x);
                int right = ss[c].higher(x);
                if (!ss[c].contains(x)) {
                    ans = Math.max(ans, bit.sum(left + 1, right));
//                } else {
//                    ans = Math.max(ans, Math.max(bit.sum(left + 1, x), bit.sum(x + 1, right)));
                }
//                Algo.debug(u, x, ps[u].y, c, ans, left, right);
            }
            for (int u = i; u < j; u++) {
                bit.add(ps[u].x, 1);
                ss[ps[u].c].add(ps[u].x);
            }
            i = j;
        }
        for (int i = 0; i < k; i++) {
            int left = -2;
            for (int right : ss[i]) {
                if (left != -2) {
                    ans = Math.max(ans, bit.sum(left + 1, right));
                }
                left = right;
            }
        }
        out.println(ans);
    }

    private int hash(int[] is) {
        int[] cs = is.clone();
        Arrays.sort(cs);
        cs = Algo.unique(cs);
        for (int i = 0; i < is.length; i++) {
            is[i] = Algo.lowerBound(cs, is[i]);
        }
        return cs.length;
    }

    class P implements Comparable<P> {
        int x;
        int y;
        int c;

        P(int x, int y, int c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }

        @Override
        public int compareTo(P o) {
            return y - o.y;
        }
    }
}
