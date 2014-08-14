package main;
import java.util.*;

import com.shu_mj.ds.BIT;
import com.shu_mj.tpl.Algo;

public class GraphInversions {


    final int INF = Integer.MAX_VALUE / 4;
    private BIT bit;

    public int getMinimumInversions(int[] A, int[] B, int[] V, int K) {
        int n = A.length;
        int ans = INF;
        V[] vs = new V[n];
        for (int i = 0; i < n; i++) vs[i] = new V(V[i]);
        for (int i = 0; i < n; i++) {
            vs[A[i]].vs.add(vs[B[i]]);
            vs[B[i]].vs.add(vs[A[i]]);
        }
        for (int i = 0; i < n; i++) {
            ans = Math.min(ans, calc(vs[i], K));
        }
        return ans == INF ? -1 : ans;
    }

    class V {
        int val;
        boolean vis;
        ArrayList<V> vs = new ArrayList<>();

        V(int val) {
            this.val = val;
        }
    }

    private int calc(V v, int k) {
        bit = new BIT(1001);
        return dfs(v, 1, k, 0);
    }

    private int dfs(V v, int s, int k, int cost) {
//        Algo.debug(s, k, cost);
        int res = INF;
        v.vis = true;
        bit.add(v.val, 1);
        if (s >= k) res = cost;
        for (V u : v.vs) {
            if (!u.vis) {
                res = Math.min(res, dfs(u, s + 1, k, cost + bit.sum(u.val + 1, 1001)));
            }
        }
        v.vis = false;
        bit.add(v.val, -1);
        return res;
    }


    int ANS;
}
