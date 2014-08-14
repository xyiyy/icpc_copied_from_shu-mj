package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task4942 {
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
        int m = in.nextInt();
        int[] d = new int[n];
        T[] ts = new T[n];
        for (int i = 0; i < n; i++) ts[i] = new T();
        for (int i = 0; i < n; i++) {
            ts[i].w = in.nextInt();
            int l = in.nextInt() - 1;
            int r = in.nextInt() - 1;
            if (l != -1) {
                d[l]++;
                ts[i].left = ts[l];
            }
            if (r != -1) {
                d[r]++;
                ts[i].right = ts[r];
            }
        }
        for (int i = 0; i < n; i++) if (d[i] == 0) {
            inOrder(ts[i]);
            postOrder(ts[i]);
        }
        Seg seg = new Seg(ts);
        while (m-- != 0) {
            int t = in.nextInt();
            int x = in.nextInt() - 1;
            if (t == 0) {
                rightRotate(ts[x], seg);
            } else if (t == 1) {
                leftRotate(ts[x], seg);
            } else {
                out.println(seg.query(ts[x].L, ts[x].R + 1));
            }
        }
    }

    private void leftRotate(T t, Seg seg) {
        if (t.right == null) return ;
        T r = t.right;
        T p = t.pre;
        t.change(t.left, r.left);
        r.change(t, r.right);
        if (p != null) {
            if (p.left == t) p.change(r, p.right);
            else p.change(p.left, r);
        } else {
            r.pre = null;
        }
        seg.update(t.id, t.L, t.R);
        seg.update(r.id, r.L, r.R);
    }

    private void rightRotate(T t, Seg seg) {
        if (t.left == null) return ;
        T l = t.left;
        T p = t.pre;
        t.change(l.right, t.right);
        l.change(l.left, t);
        if (p != null) {
            if (p.left == t) p.change(l, p.right);
            else p.change(p.left, l);
        } else {
            l.pre = null;
        }
        seg.update(t.id, t.L, t.R);
        seg.update(l.id, l.L, l.R);
    }

    final long M = (long) (1e9 + 7);
    class Seg {
        long[] ws;
        long[] is;
        int N;
        Seg(T[] ts) {
            int n = ts.length;
            N = Integer.highestOneBit(n) << 1;
            ws = new long[n];
            is = new long[N * 2];
            for (T t : ts) {
                ws[t.id] = t.w;
            }
            for (int i = 0; i < n; i++) {
                ws[i] += (i == 0 ? 0 : ws[i - 1]);
            }
            for (T t : ts) {
                is[t.id + N] = ws[t.R] - (t.L == 0 ? 0 : ws[t.L - 1]);
                is[t.id + N] %= M;
            }
            for (int i = N - 1; i > 0; i--) {
                is[i] = is[i * 2] * is[i * 2 + 1] % M;
            }
        }

        void update(int k, int l, int r) {
            is[k + N] = (ws[r] - (l == 0 ? 0 : ws[l - 1])) % M;
            for (int i = (N + k) / 2; i > 0; i >>= 1) {
                is[i] = is[i * 2] * is[i * 2 + 1] % M;
            }
        }

        long query(int s, int t) {
            long res = 1;
            while (0 < s && s + (s & -s) <= t) {
                int i = (N + s) / (s & -s);
                res *= is[i];
                res %= M;
                s += s & -s;
            }
            while (s < t) {
                int i = (N + t) / (t & -t) - 1;
                res *= is[i];
                res %= M;
                t -= t & -t;
            }
            return res;
        }
    }

    private void postOrder(T t) {
        Stack<T> st = new Stack<T>();
        Stack<T> ps = new Stack<T>();
        st.push(t);
        while (!st.isEmpty()) {
            t = st.pop();
            if (t == null) continue;
            ps.push(t);
            st.push(t.left);
            st.push(t.right);
        }
        while (!ps.empty()) {
            t = ps.pop();
            t.change(t.left, t.right);
        }
    }


    private void inOrder(T t) {
        Stack<T> st = new Stack<T>();
        int id = 0;
        while (t != null || !st.empty()) {
            while (t != null) {
                st.push(t);
                t = t.left;
            }
            if (!st.empty()) {
                t = st.pop();
                t.id = id++;
                t = t.right;
            }
        }
    }

    class T {
        int id;
        int w;
        int L;
        int R;
        T left;
        T right;
        T pre;

        T change(T left, T right) {
            this.left = left;
            this.right = right;
            L = R = id;
            if (left != null) {
                left.pre = this;
                L = left.L;
            }
            if (right != null) {
                right.pre = this;
                R = right.R;
            }
            return this;
        }
    }
}
