package com.shu_mj.graph;

import java.util.ArrayList;

/**
 * Created by Jun on 6/27/2014.
 */
public class BipartiteMatching {
    public static int bipartiteMatching(V[] vs) {
        int match = 0;
        for (V v : vs)
            if (v.pair == null) {
                for (V u : vs) u.used = false;
                if (dfs(v)) match++;
            }
        return match;
    }

    public static boolean dfs(V v) {
        v.used = true;
        for (V u : v) {
            u.used = true;
            V w = u.pair;
            if (w == null || !w.used && dfs(w)) {
                v.pair = u;
                u.pair = v;
                return true;
            }
        }
        return false;
    }

    public static class V extends ArrayList<V> {
        public V pair;
        public boolean used;

        public void connect(V v) {
            add(v);
            v.add(this);
        }
    }
}
