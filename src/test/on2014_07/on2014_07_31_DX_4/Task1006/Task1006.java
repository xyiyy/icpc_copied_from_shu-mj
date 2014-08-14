package test.on2014_07.on2014_07_31_DX_4.Task1006;



import com.shu_mj.math.Num;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task1006 {
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
        int[] is = in.nextIntArray(n);
        Seg seg = new Seg(n);
        for (int i = 0; i < n; i++) seg.update1(i, i + 1, is[i]);
        int q = in.nextInt();
        while (q-- != 0) {
            int t = in.nextInt();
            int l = in.nextInt() - 1;
            int r = in.nextInt();
            int a = in.nextInt();
            if (t == 1) {
                seg.update1(l, r, a);
            } else {
                if (a == 0) continue;
                seg.update2(l, r, a);
            }
        }
        seg.pushAllDown(1);
        for (int i = 0; i < n; i++) {
            out.print(seg.is[i + seg.N] + " ");
        }
        out.println();
    }
    static class Seg {
        public int N;
        public long[] is;
        public long[] js;
        public long[] lz;
        public boolean[] bs;
        public Seg(int n) {
            N = Integer.highestOneBit(n) << 1;
            is = new long[N * 2];
            js = new long[N * 2];
            lz = new long[N * 2];
            bs = new boolean[N * 2];
        }

        int s;
        int t;

        public void update1(int s, int t, int a) {
            this.s = s;
            this.t = t;
            update1(1, 0, N, a);
        }

        void update1(int o, int l, int r, long a) {
            if (s <= l && r <= t) {
                push(o, l, r, a);
            } else {
                pushDown(o, l, r);
                int m = (l + r) / 2;
                if (s < m) update1(o * 2, l, m, a);
                if (t > m) update1(o * 2 + 1, m, r, a);
                is[o] = mergeI(is[o * 2], is[o * 2 + 1]);
                js[o] = mergeJ(js[o * 2], js[o * 2 + 1]);
            }
        }

        public void update2(int s, int t, int a) {
            this.s = s;
            this.t = t;
            update2(1, 0, N, a);
        }

        void update2(int o, int l, int r, long a) {
            if (is[o] <= a) return ;
            if (s <= l && r <= t) {
                updateAll(o, l, r, a);
            } else {
                pushDown(o, l, r);
                int m = (l + r) / 2;
                if (s < m) update2(o * 2, l, m, a);
                if (t > m) update2(o * 2 + 1, m, r, a);
                is[o] = mergeI(is[o * 2], is[o * 2 + 1]);
                js[o] = mergeJ(js[o * 2], js[o * 2 + 1]);
            }
        }

        void updateAll(int o, int l, int r, long a) {
            if (is[o] <= a) return ;
            if (is[o] == js[o]) {
                lz[o] = is[o] = js[o] = Num.gcd(is[o], a);
                bs[o] = true;
            } else {
                pushDown(o, l, r);
                int m = (l + r) / 2;
                updateAll(o * 2, l, m, a);
                updateAll(o * 2 + 1, m, r, a);
                is[o] = mergeI(is[o * 2], is[o * 2 + 1]);
                js[o] = mergeJ(js[o * 2], js[o * 2 + 1]);
            }
        }
        public void pushDown(int o, int l, int r) {
            if (bs[o]) {
                int m = (l + r) / 2;
                push(o * 2, l, m, lz[o]);
                push(o * 2 + 1, m, r, lz[o]);
                bs[o] = false;
            }
        }

        public void push(int o, int l, int r, long a) {
            is[o] = js[o] = lz[o] = a;
            bs[o] = true;
        }

        public long mergeI(long a, long b) {
            return Math.max(a, b);
        }
        public long mergeJ(long a, long b) {
            return Math.min(a, b);
        }

        public void pushAllDown(int o) {
            if (o >= N) return ;
            pushDown(o, 0, 0);
            pushAllDown(o * 2);
            pushAllDown(o * 2 + 1);
        }
    }
}
