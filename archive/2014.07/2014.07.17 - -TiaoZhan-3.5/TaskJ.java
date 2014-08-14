package main;

import com.shu_mj.graph.Dij;
import com.shu_mj.graph.Dinic;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskJ {
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
        V[] vs = new V[n + 2];
        long sum = 0;
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new V();
        }
        V s = vs[n];
        V t = vs[n + 1];
        for (int i = 0; i < n; i++) {
            int w = in.nextInt();
            if (w > 0) {
                sum += w;
                s.add(vs[i], w);
            } else {
                vs[i].add(t, -w);
            }
        }
        while (m-- != 0) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            vs[u].add(vs[v], Dinic.INF);
        }
        sum -= dinic(s, t);
        for (V v : vs) v.level = 0;
        dfs(s, t);
        int ans = -1;
        for (V v : vs) ans += v.level;
        out.println(ans + " " + sum);
    }
    void dfs(V s, V t) {
        if (s == t) return ;
        s.level = 1;
        for (E e : s.es) {
            if (e.cap > 0 && e.to.level == 0)
                dfs(e.to, t);
        }
    }

    public static int INF = Integer.MAX_VALUE / 4;

    public static long dinic(V s, V t) {
        long flow = 0;
        for (int p = 1; ; p++) {
            Queue<V> que = new LinkedList<V>();
            s.level = 0;
            s.p = p;
            que.offer(s);
            while (!que.isEmpty()) {
                V v = que.poll();
                v.iter = v.es.size() - 1;
                for (E e : v.es)
                    if (e.to.p < p && e.cap > 0) {
                        e.to.level = v.level + 1;
                        e.to.p = p;
                        que.offer(e.to);
                    }
            }
            if (t.p < p) return flow;
            for (int f; (f = dfs(s, t, INF)) > 0; ) flow += f;
        }
    }

    public static int dfs(V v, V t, int f) {
        if (v == t) return f;
        for (; v.iter >= 0; v.iter--) {
            E e = v.es.get(v.iter);
            if (v.level < e.to.level && e.cap > 0) {
                int d = dfs(e.to, t, Math.min(f, e.cap));
                if (d > 0) {
                    e.cap -= d;
                    e.rev.cap += d;
                    return d;
                }
            }
        }
        return 0;
    }

    public static class V {
        public ArrayList<E> es = new ArrayList<E>();
        public int level;
        public int p;
        public int iter;
        public void add(V to, int cap) {
            E e = new E(to, cap), rev = new E(this, 0);
            e.rev = rev;
            rev.rev = e;
            es.add(e);
            to.es.add(rev);
        }
    }

    public static class E {
        public V to;
        public E rev;
        public int cap;

        public E(V to, int cap) {
            this.to = to;
            this.cap = cap;
        }
    }
}
