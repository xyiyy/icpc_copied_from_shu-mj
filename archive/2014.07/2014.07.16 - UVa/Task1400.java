package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task1400 {
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
            out.printf("Case %d:%n", cs++);
            solve();
        }
    }

    void solve() {
        int n = in.nextInt();
        int m = in.nextInt();
        Seg seg = new Seg(n);
        while (m-- != 0) {
            int a = in.nextInt() - 1;
            int b = in.nextInt();
            Int r = seg.query(a, b).max;
            out.println((r.l + 1) + " " + (r.r + 1));
        }
    }

    class Int {
        final long max;
        final long l;
        final long r;

        Int(long max, long l, long r) {
            this.max = max;
            this.l = l;
            this.r = r;
        }

        @Override
        public String toString() {
            return "Int{" +
                    "max=" + max +
                    ", l=" + l +
                    ", r=" + r +
                    '}';
        }
    }

    class Info {
        final long sum;
        final Int max;
        final Int pre;
        final Int suf;

        Info(long sum, Int max, Int pre, Int suf) {
            this.sum = sum;
            this.max = max;
            this.pre = pre;
            this.suf = suf;
        }

        Info(long x, long id) {
            this(x, new Int(x, id, id), new Int(x, id, id), new Int(x, id, id));
        }

        @Override
        public String toString() {
            return "Info{" +
                    "sum=" + sum +
                    ", max=" + max +
                    ", pre=" + pre +
                    ", suf=" + suf +
                    '}';
        }
    }

    class Seg {
        int N;
        Info[] is;

        Seg(int n) {
            N = Integer.highestOneBit(n) << 1;
            is = new Info[N * 2];
            for (int i = 0; i < N; i++) {
                if (i < n) is[i + N] = new Info(in.nextInt(), i);
                else is[i + N] = null;
            }
            for (int i = N - 1; i > 0; i--) {
                is[i] = merge(is[i * 2], is[i * 2 + 1]);
            }
//            Algo.debugTable(is);
        }

        Int merge(Int a, Int b) {
            if (a.max != b.max) return a.max > b.max ? a : b;
            if (a.l != b.l) return a.l < b.l ? a : b;
            return a.r < b.r ? a : b;
        }
        Info merge(Info a, Info b) {
            if (a == null) return b;
            if (b == null) return a;
            long sum = a.sum + b.sum;
            Int max = merge(a.max, b.max);
            max = merge(max, new Int(a.suf.max + b.pre.max, a.suf.l, b.pre.r));
            Int pre = merge(a.pre, new Int(a.sum + b.pre.max, a.pre.l, b.pre.r));
            Int suf = merge(b.suf, new Int(b.sum + a.suf.max, a.suf.l, b.suf.r));
            return new Info(sum, max, pre, suf);
        }
        Info query(int s, int t) {
            Info left = null;
            Info right = null;
//            Algo.debug("left");
            while (0 < s && s + (s & -s) <= t) {
                int i = (N + s) / (s & -s);
//                Algo.debug(i, left, is[i]);
                left = merge(left, is[i]);
//                Algo.debug(left);
                s += s & -s;
            }
//            Algo.debug("right");
            while (s < t) {
                int i = (N + t) / (t & -t) - 1;
//                Algo.debug(i, is[i], right);
                right = merge(is[i], right);
//                Algo.debug(right);
                t -= t & -t;
            }
//            Algo.debug("merge");
//            Algo.debug(left, right);
//            Algo.debug(merge(left, right));
            return merge(left, right);
        }
    }
}
