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
        int n = in.nextInt();
        int m = in.nextInt();
        V[] vs = new V[n];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new V();
        }
        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            int w = in.nextInt();
            vs[u].add(new E(vs[v], w));
        }
        for (V v : vs) Collections.sort(v);
        for (V v : vs) v.iter = v.size();
        int ans = 0;
        for (V v : vs) {
            ans = Math.max(ans, dfs(v, 0));
        }
        out.println(ans);
    }

    private int dfs(V v, int w) {
        int upper = Algo.upperBound(v, new E(null, w));
        if (upper == v.size()) return 0;
        if (v.get(upper).step != -1) return v.get(upper).step;
        for (int i = v.iter - 1; i >= upper; i--) {
            v.get(i).step = 1 + dfs(v.get(i).to, v.get(i).cost);
            if (i + 1 < v.size()) v.get(i).step = Math.max(v.get(i).step, v.get(i + 1).step);
            v.iter = i;
        }
        return v.get(upper).step;
    }

    class V extends ArrayList<E> {
        int iter;
    }
    class E implements Comparable<E> {
        V to;
        int cost;
        int step = -1;

        E(V to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(E o) {
            return cost - o.cost;
        }
    }

}