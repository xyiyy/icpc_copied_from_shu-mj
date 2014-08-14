package com.shu_mj.ds;

/**
 * Created by Jun on 7/14/2014.
 */
public abstract class Seg {
    public int N;
    public long[] is;
    public long[] lz;
    public boolean[] bs;
    public Seg(int n) {
        N = Integer.highestOneBit(n) << 1;
        is = new long[N * 2];
        lz = new long[N * 2];
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

    public void update(int s, int t, int a) {
        this.s = s;
        this.t = t;
        update(1, 0, N, a);
    }

    void update(int o, int l, int r, long a) {
        if (s <= l && r <= t) {
            push(o, l, r, a);
        } else {
            pushDown(o, l, r);
            int m = (l + r) / 2;
            if (s < m) update(o * 2, l, m, a);
            if (t > m) update(o * 2 + 1, m, r, a);
            is[o] = merge(is[o * 2], is[o * 2 + 1]);
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
        pushIs(o, l, r, a);
        pushLz(o, l, r, a);
    }
    public void pushLz(int o, int l, int r, long a) {
        if (bs[o]) overPushLz(o, l, r, a);
        else {
            lz[o] = a;
            bs[o] = true;
        }
    }
    public abstract long merge(long a, long b);
    public abstract void pushIs(int o, int l, int r, long a);
    public abstract void overPushLz(int o, int l, int r, long a);
}
