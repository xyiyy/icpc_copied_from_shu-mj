package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task1003 {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            out.printf("Case #%d:%n", i);
            solve();
        }
    }

    private void solve() {
        int n = in.nextInt();
        Seg seg = new Seg(n);
        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            char c = in.next().charAt(0);
            long l = in.nextLong() - 1;
            long r = in.nextLong();
            if (c == 'Q') {
                out.println(seg.query(l, r));
            } else {
                seg.update(l, r, 2);
            }
        }
    }
    class Seg {
        public int N;
        public long[] is;
        public long[] max;
        public long[] lz;
        public boolean[] bs;
        public Seg(int n) {
            N = Integer.highestOneBit(n) << 1;
            is = new long[N * 2];
            max = new long[N * 2];
            lz = new long[N * 2];
            bs = new boolean[N * 2];
            for (int i = N; i < N * 2; i++) {
                is[i] = 1;
                max[i] = 1;
                lz[i] = 1;
            }
            for (int i = N - 1; i > 0; i--) {
                is[i] = is[i * 2] + is[i * 2 + 1];
                max[i] = 1;
                lz[i] = 1;
            }
        }

        public long query(long s, long t) {
            return query(1, 0, is[1], s, t);
        }

        long query(int o, long l, long r, long s, long t) {
            if (s <= l && r <= t || o >= N) {
                return Math.min(max[o], t - s);
            } else {
                pushDown(o);
                long m = l + is[o * 2];
                if (t <= m) return query(o * 2, l, m, s, t);
                if (s >= m) return query(o * 2 + 1, m, r, s, t);
                return merge(query(o * 2, l, m, s, m), query(o * 2 + 1, m, r, m, t));
            }
        }

        public void update(long s, long t, long a) {
            update(1, 0, is[1], a, s, t);
        }

        void update(int o, long l, long r, long a, long s, long t) {
            if (s <= l && r <= t || o >= N) {
                if (o < N) push(o, a);
                else {
                    is[o] += t - s;
                    max[o] = is[o];
                }
            } else {
                pushDown(o);
                long m = l + is[o * 2];
                if (s < m) update(o * 2, l, m, a, s, Math.min(m, t));
                if (t > m) update(o * 2 + 1, m, r, a, Math.max(s, m), t);
                is[o] = is[o * 2] + is[o * 2 + 1];
                max[o] = Math.max(max[o * 2], max[o * 2 + 1]);
            }
        }

        public void pushDown(int o) {
            if (bs[o]) {
                push(o * 2, lz[o]);
                push(o * 2 + 1, lz[o]);
                lz[o] = 1;
                bs[o] = false;
            }
        }

        public void push(int o, long a) {
            lz[o] *= a;
            bs[o] = true;
            is[o] *= a;
            max[o] *= a;
        }
        public long merge(long a, long b) {
            return Math.max(a, b);
        }
    }

}
