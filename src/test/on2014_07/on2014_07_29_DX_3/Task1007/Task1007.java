package test.on2014_07.on2014_07_29_DX_3.Task1007;



import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task1007 {
    Scanner in;
    PrintWriter out;
    private long[] fi;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        init();
        while (in.hasNext()) {
            solve();
        }
    }

    private void solve() {
        int n = in.nextInt();
        int m = in.nextInt();
        Seg seg = new Seg(n);
        while (m-- != 0) {
            int t = in.nextInt();
            int l = in.nextInt() - 1, r = in.nextInt();
            if (t == 1) {
                seg.update1(l, r);
            } else if (t == 2) {
                out.println(seg.query(l, r));
            } else if (t == 3) {
                seg.update(l, r);
            }
        }
    }

    void init() {
        fi = new long[80];
        fi[0] = fi[1] = 1;
        for (int i = 2; i < fi.length; i++) {
            fi[i] = fi[i - 1] + fi[i - 2];
        }
    }

    long fib(long f) {
        int i = Algo.lowerBound(fi, f);
        long res = fi[i];
        if (i > 0 && Math.abs(fi[i - 1] - f) <= Math.abs(res - f)) res = fi[i - 1];
        return res;
    }
    class Seg {
        public int N;
        public long[] is;
        public long[] fs;
        public boolean[] bs;
        Seg(int n) {
            N = Integer.highestOneBit(n) << 1;
            is = new long[N * 2];
            fs = new long[N * 2];
            for (int i = N; i < N * 2; i++) {
                fs[i] = 1;
            }
            for (int i = N - 1; i > 0; i--) {
                fs[i] = fs[i * 2] + fs[i * 2 + 1];
            }
            bs = new boolean[N * 2];
        }

        int s;
        int t;

        public long query(int s, int t) {
            this.s = s;
            this.t = t;
            return query(1, 0, N);
        }

        long query(int o, int l, int r) {
            if (s <= l && r <= t) {
                return is[o];
            } else {
                pushDown(o, l, r);
                int m = (l + r) / 2;
                if (t <= m) return query(o * 2, l, m);
                if (s >= m) return query(o * 2 + 1, m, r);
                return merge(query(o * 2, l, m), query(o * 2 + 1, m, r));
            }
        }

        public void update1(int k, long a) {
            pushAllDown(N + k);
            is[k + N] += a;
            fs[k + N] = fib(is[k + N]);
            for (int i = (N + k) / 2; i > 0; i >>= 1) {
                is[i] = merge(is[i * 2], is[i * 2 + 1]);
                fs[i] = merge(fs[i * 2], fs[i * 2 + 1]);
            }
        }
        void pushAllDown(int k) {
            if (k != 1) pushAllDown(k / 2);
            if (k < N) pushDown(k, 0, 0);
        }
        public void update(int s, int t) {
            this.s = s;
            this.t = t;
            update2(1, 0, N);
        }

        void update2(int o, int l, int r) {
            if (s <= l && r <= t) {
                push(o, l, r);
            } else {
                pushDown(o, l, r);
                int m = (l + r) / 2;
                if (s < m) update2(o * 2, l, m);
                if (t > m) update2(o * 2 + 1, m, r);
                is[o] = merge(is[o * 2], is[o * 2 + 1]);
                fs[o] = merge(fs[o * 2], fs[o * 2 + 1]);
            }
        }

        public void pushDown(int o, int l, int r) {
            if (bs[o]) {
                int m = (l + r) / 2;
                push(o * 2, l, m);
                push(o * 2 + 1, m, r);
                bs[o] = false;
            }
        }

        public void push(int o, int l, int r) {
            is[o] = fs[o];
            bs[o] = true;
        }
        public long merge(long a, long b) {
            return a + b;
        }
    }
}
