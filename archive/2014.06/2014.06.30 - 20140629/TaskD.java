package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskD {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        while (t-- != 0) {
            solve();
        }
    }

    private void solve() {
        int n = in.nextInt();
        int[] is = new int[n];
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int id = 0;
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            if (!map.containsKey(x)) map.put(x, id++);
            is[i] = map.get(x);
        }
        int[] L = new int[n];
        int[] R = new int[n];
        int[] last = new int[id];
        Arrays.fill(last, -1);
        for (int i = 0; i < n; i++) {
            L[i] = last[is[i]];
            last[is[i]] = i;
        }
        Arrays.fill(last, n);
        for (int i = n - 1; i >= 0; i--) {
            R[i] = last[is[i]];
            last[is[i]] = i;
        }
        Seg seg = new Seg(n);
        for (int i = 0; i < n + 1; i++) {
            if (seg.is[1] < i - 1) {
                out.println("boring");
                return ;
            }
            if (i < n) seg.update(L[i] + 1, i + 1, R[i] - 1);
        }
        out.println("non-boring");
    }

    class Seg {
        int N;
        int[] is;
        int[] lazy;
        private int s;
        private int t;
        private int v;

        Seg(int n) {
            N = Integer.highestOneBit(n) << 1;
            is = new int[N * 2];
            lazy = new int[N * 2];
            Arrays.fill(lazy, -1);
            for (int i = 0; i < N; i++) {
                is[i + N] = i;
            }
            for (int i = N - 1; i > 0; i--) {
                is[i] = Math.min(is[i * 2], is[i * 2 + 1]);
            }
        }
        void update(int s, int t, int v) {
            update(1, 0, N, s, t, v);
        }

        void pushDown(int o) {
            if (lazy[o] != -1 && o < N) {
                updateMin(o * 2, lazy[o]);
                updateMin(o * 2 + 1, lazy[o]);
                lazy[o] = -1;
            }
        }
        void updateMin(int o, int v) {
            if (v > is[o]) {
                is[o] = Math.max(is[o], v);
                lazy[o] = v;
            }
        }
        void pushUp(int o) {
            is[o] = Math.min(is[o * 2], is[o * 2 + 1]);
        }
        void update(int o, int L, int R, int s, int t, int v) {
            pushDown(o);
            if (L >= s && R <= t) {
                updateMin(o, v);
            } else {
                int M = (L + R) / 2;
                if (s < M) update(o * 2, L, M, s, t, v);
                if (t > M) update(o * 2 + 1, M, R, s, t, v);
                pushUp(o);
            }
        }
    }
}
