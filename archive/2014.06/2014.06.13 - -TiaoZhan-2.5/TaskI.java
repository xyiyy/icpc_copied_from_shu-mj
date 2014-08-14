package main;

import com.shu_mj.tpl.Algo;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class TaskI {
    private static final long INF = Long.MAX_VALUE / 4;
    private int[] pre;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt(), m = in.nextInt();
        V[] vs = new V[n];
        for (int i = 0; i < n; i++) {
            vs[i] = new V();
        }
        Ek[] es = new Ek[m];
        for (int i = 0; i < m; i++) {
            int a = in.nextInt(), b = in.nextInt(), cost = in.nextInt();
            es[i] = new Ek(a - 1, b - 1, cost);
        }
        Arrays.sort(es);
        pre = new int[n];
        for (int i = 0; i < n; i++) {
            pre[i] = i;
        }
        long ans = 0;
        for (Ek e : es) {
            int fa = find(e.from), fb = find(e.to);
            if (fa == fb) {

            } else {
                vs[e.from].connect(vs[e.to], e.cost);
                pre[fa] = fb;
                ans = Math.max(ans, e.cost);
            }
        }
//        dfs(vs[0], 0);
//        long ans = 0;
//        for (V v : vs) ans = Math.max(ans, v.min);
        out.println(ans);
    }

    private void dfs(V v, long cost) {
        v.min = cost;
        for (E e : v.es) {
            if (e.to.min == INF) {
                dfs(e.to, cost + e.cost);
            }
        }
    }

    private int find(int x) {
        if (pre[x] != x) pre[x] = find(pre[x]);
        return pre[x];
    }

    private void dij(V s) {
        PriorityQueue<E> que = new PriorityQueue<E>();
        s.min = 0;
        que.add(new E(s, 0));
        while (!que.isEmpty()) {
            E crtE = que.poll();
            V crt = crtE.to;
            if (crt.min < crtE.cost) continue;
            for (E e : crt.es) {
                long newCost = crt.min + e.cost;
                if (e.to.min > newCost) {
                    e.to.min = newCost;
                    que.add(new E(e.to, newCost));
                }
            }
        }
    }

    class Ek implements Comparable<Ek> {
        int from;
        int to;
        int cost;

        Ek(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Ek o) {
            return cost - o.cost;
        }
    }
    class E implements Comparable<E> {
        V to;
        long cost;

        E(V to, long cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(E o) {
            if (cost == o.cost) return 0;
            return cost < o.cost ? -1 : 1;
        }
    }
    class V {
        long min = INF;
        List<E> es = new ArrayList<E>();
        void connect(V to, long cost) {
            es.add(new E(to, cost));
        }
    }
}
