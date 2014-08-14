package main;



import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;

public class TaskC {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt(), m = in.nextInt();
        Seg sum = new Seg(n);
        for (int i = 0; i < m; i++) {
            int t = in.nextInt();
            int l = in.nextInt();
            int r = in.nextInt();
            if (t == 1) {
                sum.update(l - 1, r);
            } else {
                out.println((sum.query(l - 1, r) + MOD) % MOD);
            }
        }
    }

    final int MOD = (int) (1e9 + 9);
    class Seg {
        public int N;
        public int[] is;
        int[] f0, f1;
        int[] fs;
        public Seg(int n) {
            N = Integer.highestOneBit(n) << 1;
            fs = new int[N + 100];
            for (int i = 0; i < N + 100; i++) {
                if (i == 0) fs[i] = 1;
                else if (i == 1) fs[i] = 1;
                else fs[i] = (fs[i - 1] + fs[i - 2]) % MOD;
            }
            is = new int[N * 2];
            for (int i = 0; i < n; i++) is[i + N] = in.nextInt();
            for (int i = N - 1; i > 0; i--) is[i] = (is[i * 2] + is[i * 2 + 1]) % MOD;
            f0 = new int[N * 2];
            f1 = new int[N * 2];
        }

        int s, t;

        void update(int s, int t) {
            this.s = s;
            this.t = t;
            update(1, 0, N);
        }

        int query(int s, int t) {
            this.s = s;
            this.t = t;
            return query(1, 0, N);
        }

        int query(int o, int l, int r) {
            if (s <= l && r <= t) {
                return is[o];
            } else {
                pushDown(o, l, r);
                int m = (l + r) / 2;
                if (t <= m) return query(o * 2, l, m);
                if (s >= m) return query(o * 2 + 1, m, r);
                return (query(o * 2, l, m) + query(o * 2 + 1, m, r)) % MOD;
            }
        }

        void update(int o, int l, int r) {
            if (s <= l && r <= t) {
                push(o, l, r, get(1, 1, l - s), get(1, 1, l - s + 1));
            } else {
                pushDown(o, l, r);
                int m = (l + r) / 2;
                if (s < m)
                    update(o * 2, l, m);
                if (t > m)
                    update(o * 2 + 1, m, r);
                is[o] = (is[o * 2] + is[o * 2 + 1]) % MOD;
            }
        }

        void pushDown(int o, int l, int r) {
            if (f0[o] == 0 && f1[o] == 0) return ;
            int m = (l + r) / 2;
            push(o * 2, l, m, f0[o], f1[o]);
            push(o * 2 + 1, m, r, get(f0[o], f1[o], m - l), get(f0[o], f1[o], m - l + 1));
            f0[o] = f1[o] = 0;
        }

        int get(int a, int b, int k) {
            if (k == 0) return a;
            if (k == 1) return b;
            return (int) (((long) fs[k - 2] * a % MOD + (long) fs[k - 1] * b % MOD) % MOD);
        }

        void push(int o, int l, int r, int a, int b) {
            is[o] += get(a, b, r - l + 1) - b;
            is[o] %= MOD;
            f0[o] += a;
            f0[o] %= MOD;
            f1[o] += b;
            f1[o] %= MOD;
        }
    }
}
