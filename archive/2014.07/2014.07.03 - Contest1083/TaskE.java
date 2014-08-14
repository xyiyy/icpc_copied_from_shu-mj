package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskE {
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
            out.printf("Case %d:%n", i);
            solve();
        }
    }
    /*
    void solve() {
        int n = in.nextInt();
        V[] vs = new V[n + 10];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new V();
        }
        for (int i = 0; i < n; i++) {
            vs[in.nextInt()].R(in.nextInt());
        }
        dfs(vs, 0, 0);
        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            out.print(solve(vs, u, v));
            if (i == m - 1) out.println();
            else out.print(' ');
        }
    }

    private Set<Integer> dfs(V[] vs, int v, int dep) {
        vs[v].dep = dep;
        vs[v].sons.R(v);
        for (int i : vs[v]) {
            vs[v].sons.addAll(dfs(vs, i, dep + 1));
        }
        return vs[v].sons;
    }

    private int solve(V[] vs, int u, int v) {
        int dep = -1, id = -1;
        for (int i = 0; i < vs.length; i++) {
            if (vs[i].sons.contains(u) && vs[i].sons.contains(v) && vs[i].dep > dep) {
                dep = vs[i].dep;
                id = i;
            }
        }
        return id;
    }

    class V extends ArrayList<Integer> {
        Set<Integer> sons = new HashSet<Integer>();
        int dep;

    }
    */
    private void solve() {
        int n = in.nextInt();
        V[] vs = new V[n + 10];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new V(i);
        }
        vs[5].hashCode();
        for (V v : vs) Algo.debug(v, v.hashCode());
        for (int i = 0; i < n; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            vs[u].add(vs[v]);
        }
//        boolean b = vs[2].sons.addAll(vs[6]);
//        boolean b = false;
//        vs[2].sons.R(vs[5]);
//        Algo.debug(vs[2].equals(vs[6]));
//        Algo.debug(vs[2].hashCode(), vs[6].hashCode());
//        Algo.debug(vs[5].equals(vs[6]));
//        Algo.debug(vs[5].hashCode(), vs[6].hashCode());
//        Algo.debug("!!!", vs[6].sons, vs[2].sons, b);
//        for (V u : vs[6].sons) {
//            b = vs[2].sons.R(u);
//            Algo.debug("!!!", vs[6].sons, vs[2].sons, b);
//        }
//        Algo.debug("!!!", vs[6].sons, vs[2].sons, b);
        dfs(vs, vs[0], 0);
        for (V v : vs) Algo.debug(v.id, v.directSons(), v.sons);
        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            out.print(solve(vs, vs[x], vs[y]));
            if (i != m - 1) out.print(' ');
            else out.println();
        }
    }

    private Set<V> dfs(V[] vs, V v, int dep) {
        v.dep = dep;
        for (V u : v) {
            Set<V> ss = dfs(vs, u, dep + 1);
//            v.sons.addAll(ss);
            for (V x : ss) {
                v.sons.add(x);
                for (V y : v.sons) if (x.equals(y)) Algo.debug("::", x, y, x.hashCode(), y.hashCode());
                Algo.debug(": ", x, v.sons);
            }
            Algo.debug(v, u, v.sons, u.sons, ss);
        }
        return v.sons;
    }

    private int solve(V[] vs, V v, V u) {
        int ans = -1, id = -1;
        for (V x : vs) if (x.sons.contains(v) && x.sons.contains(u) && x.dep > ans) {
            ans = x.dep;
            id = x.id;
            Algo.debug(x.id, x.dep, x.id, ans, id);
        }
        return id;
    }

    class V extends ArrayList<V> {
        int id;
        int dep = 0;
        V(int id) {
            this.id = id;
            sons.add(this);
        }

        HashSet<V> sons = new HashSet<V>();

        @Override
        public String toString() {
            return "" + id;
        }
        String directSons() {
            return super.toString();
        }
    }
}
