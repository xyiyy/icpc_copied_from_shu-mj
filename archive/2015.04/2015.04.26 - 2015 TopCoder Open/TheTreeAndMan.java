package main;
import java.util.*;

import com.shu_mj.math.Num;
import com.shu_mj.tpl.Algo;

public class TheTreeAndMan {
    public int solve(int[] parent) {
        int n = parent.length + 1;
        V[] vs = new V[n];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new V();
        }
        for (int i = 1; i < n; i++) {
            vs[parent[i - 1]].add(vs[i]);
        }
        dfsTail(vs[0]);
        dfsTailSum(vs[0]);
        dfsNeck(vs[0]);
        dfsNeckSum(vs[0]);
        long res = 0;
        for (V v : vs) res += v.res;
        return (int) ((res % M + M) % M);
    }

    private void dfsNeckSum(V v) {
        for (V u : v) {
            dfsNeckSum(u);
            v.neckSum += u.neckSum;
            v.neckSum %= M;
            v.res += u.neckSum;
            v.res %= M;
        }
        v.neckSum += v.neck;
        v.neckSum %= M;
    }

    private void dfsTailSum(V v) {
        for (V u : v) {
            dfsTailSum(u);
            v.tailSum += u.tailSum;
            v.tailSum %= M;
        }
        v.tailSum += v.tail;
        v.tailSum %= M;
    }

    private void dfsNeck(V v) {
        for (V u : v) {
            dfsNeck(u);
            v.neck += u.tailSum * (v.tail - u.sons * (v.sons - u.sons - 1) % M) % M;
            v.neck %= M;
        }
    }

    private void dfsTail(V v) {
        for (V u : v) {
            dfsTail(u);
            v.tail += u.sons * v.sons % M;
            v.tail %= M;
            v.sons += u.sons;
        }
        v.sons++;
    }

    final long M = (long) (1e9 + 7);
    final long d2 = Num.inv(2, M);

    class V extends ArrayList<V> {
        int sons;
        long tail;
        long tailSum;
        long neck;
        long neckSum;
        long res;
    }
}
