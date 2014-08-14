package com.shu_mj.ds;

/**
 * Created by Jun on 6/18/2014.
 */
public class BooleanBIT {
    boolean[] vs;

    public BooleanBIT(int n) {
        vs = new boolean[n + 1];
    }

    public void add(int k, boolean a) {
        if (a) for (int i = k + 1; i < vs.length; i += i & -i) {
            vs[i] ^= true;
        }
    }

    public boolean sum(int s, int t) {
        if (s > 0) return sum(0, t) ^ sum(0, s);
        boolean res = false;
        for (int i = t; i > 0; i -= i & -i) {
            res ^= vs[i];
        }
        return res;
    }

}
