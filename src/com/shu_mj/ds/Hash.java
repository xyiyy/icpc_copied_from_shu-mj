package com.shu_mj.ds;

import com.shu_mj.math.Num;

/**
 * Created by Jun on 6/21/2014.
 */
public class Hash {
    public static final long BASE = (long) (1e9 + 7);
    public long[] ps;
    public Hash(int n) {
        ps = Num.powerTable(BASE, n + 1, -1);
    }
    public long[] build(char[] cs) {
        int n = cs.length;
        long[] hs = new long[n];
        hs[0] = cs[0];
        for (int i = 1; i < n; i++) hs[i] = hs[i - 1] * BASE + cs[i];
        return hs;
    }
    public long[] build(int[] is) {
        int n = is.length;
        long[] hs = new long[n];
        hs[0] = is[0];
        for (int i = 1; i < n; i++) hs[i] = hs[i - 1] * BASE + is[i];
        return hs;
    }
    public static long getHash(char[] cs) {
        return getHash(cs, 0, cs.length);
    }
    public static long getHash(char[] cs, int b, int e) {
        long h = cs[b];
        for (int i = b + 1; i < e; i++) {
            h = h * BASE + cs[i];
        }
        return h;
    }
    public static long getHash(int[] is) {
        return getHash(is, 0, is.length);
    }
    public static long getHash(int[] is, int b, int e) {
        long h = is[b];
        for (int i = b + 1; i < e; i++) {
            h = h * BASE + is[i];
        }
        return h;
    }
    public long get(long[] hs, int b, int e) {
        return hs[e - 1] - (b == 0 ? 0 : hs[b - 1] * ps[e - b]);
    }
}
