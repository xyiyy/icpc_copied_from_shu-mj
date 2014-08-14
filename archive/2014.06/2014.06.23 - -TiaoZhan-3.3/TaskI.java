package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskI {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        while (in.hasNext()) {
            int n = in.nextInt();
            if (n == 0) break;
            solve(n);
        }
    }

    private void solve(int n) {
        int q = in.nextInt();
        Seg seg = new Seg(n);
        while (q-- != 0) {
            int a = in.nextInt() - 1;
            int b = in.nextInt();
            out.println(seg.query(a, b));
        }
    }

    class Info {
        int cs;
        int pre;
        int suf;
        int pcs;
        int scs;

        Info(int cs, int pre, int suf, int pcs, int scs) {
            this.cs = cs;
            this.pre = pre;
            this.suf = suf;
            this.pcs = pcs;
            this.scs = scs;
        }
    }
    class Seg {
        int N;
        Info[] is;
        Seg(int n) {
            N = Integer.highestOneBit(n) << 1;
            int[] hs = in.nextIntArray(n);
            int[] fs = new int[N];
            is = new Info[N * 2];
            System.arraycopy(hs, 0, fs, 0, n);
            for (int i = N * 2 - 1; i > 1; i--) {
                if (i >= N) {
                    is[i] = new Info(1, fs[i - N], fs[i - N], 1, 1);
                } else {
                    is[i] = merge(is[i * 2], is[i * 2 + 1]);
                }
            }
        }

        int query(int s, int t) {
            Info left = new Info(1, 12341234, 12341234, 1, 1);
            Info right = new Info(1, 43214321, 43214321, 1, 1);
            while (0 < s && s + (s & -s) <= t) {
                int i = (N + s) / (s & -s);
                left = merge(left, is[i]);
                s += s & -s;
            }
            while (s < t) {
                int i = (N + t) / (t & -t) - 1;
                right = merge(is[i], right);
                t -= t & -t;
            }
            return merge(left, right).cs;
        }

        Info merge(Info left, Info right) {
            int cs = Math.max(left.cs, right.cs);
            if (left.suf == right.pre) cs = Math.max(cs, left.scs + right.pcs);
            int pcs = left.pcs;
            int scs = right.scs;
            if (left.pre == right.pre) pcs += right.pcs;
            if (left.suf == right.suf) scs += left.scs;
            return new Info(cs, left.pre, right.suf, pcs, scs);
        }
    }
}
