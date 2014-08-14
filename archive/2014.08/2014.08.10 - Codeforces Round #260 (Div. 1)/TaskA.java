package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskA {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int N = (int) (1e5);
        long[] ls = new long[N + 1];
        for (int i = 0; i < n; i++) {
            int k = in.nextInt();
            ls[k] += k;
        }
        V[] vs = new V[N + 3];
        for (int i = 0; i < vs.length; i++) vs[i] = new V();
        V s = vs[N + 1];
        V t = vs[N + 2];
        for (int i = 1; i <= N; i++) if (ls[i] != 0 && (ls[i - 1] != 0 || i < N && ls[i + 1] != 0)) {
            if (i % 2 == 0) s.add(vs[i], ls[i]);
            else vs[i].add(t, ls[i]);
            if (i + 1 <= N && ls[i + 1] != 0) {
                if (i % 2 == 0) vs[i].add(vs[i + 1], INF);
                else vs[i + 1].add(vs[i], INF);
            }
            if (ls[i - 1] != 0) {
                if (i % 2 == 0) vs[i].add(vs[i - 1], INF);
                else vs[i - 1].add(vs[i], INF);
            }
        }
        out.println(Algo.sum(ls) - dinic(s, t));
    }
    public static long INF = Long.MAX_VALUE / 4;

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
            for (long f; (f = dfs(s, t, INF)) > 0; ) flow += f;
        }
    }

    public static long dfs(V v, V t, long f) {
        if (v == t) return f;
        for (; v.iter >= 0; v.iter--) {
            E e = v.es.get(v.iter);
            if (v.level < e.to.level && e.cap > 0) {
                long d = dfs(e.to, t, Math.min(f, e.cap));
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
        public void add(V to, long cap) {
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
        public long cap;

        public E(V to, long cap) {
            this.to = to;
            this.cap = cap;
        }
    }

}
