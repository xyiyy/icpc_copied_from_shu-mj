package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskB {
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
        int k = in.nextInt();
        int[] is = new int[n];
        V[] vs = new V[n];
        for (int i = 0; i < vs.length; i++) vs[i] = new V();
        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            int c = in.nextInt();
            vs[u].add(vs[v], c);
            vs[v].add(vs[u], c);
        }
        Arrays.fill(is, Integer.MAX_VALUE);
        int ans = 0;
        for (int i = 0; i < k; i++) {
            int x = in.nextInt() - 1;
            int y = in.nextInt();
            if (is[x] != Integer.MAX_VALUE) ans++;
            is[x] = Math.min(is[x], y);
        }
        for (int i = 1; i < n; i++) if (is[i] != Integer.MAX_VALUE) {
            vs[0].add(vs[i], is[i]);
        }
        dijkstra(vs[0]);
//        Algo.debug(ans);
        for (int i = 1; i < n; i++) if (is[i] != Integer.MAX_VALUE) {
            if (vs[i].min < is[i] || vs[i].times > 1) ans++;
//            Algo.debug(i, ans);
        }
        out.println(ans);
    }
    public static void dijkstra(V s) {
        PriorityQueue<E> que = new PriorityQueue<E>();
        s.min = 0;
        que.offer(new E(s, 0));
        while (!que.isEmpty()) {
            E crt = que.poll();
            if (crt.cost > crt.to.min) continue;
            for (E e : crt.to.es) {
                if (crt.cost + e.cost == e.to.min) {
                    e.to.times++;
                }
                if (crt.cost + e.cost < e.to.min) {
                    e.to.min = crt.cost + e.cost;
                    e.to.times = 1;
                    que.offer(new E(e.to, e.to.min));
                }
            }
        }
    }

    public static long INF = Long.MAX_VALUE / 4;

    public static class V {
        public ArrayList<E> es = new ArrayList<E>();
        public long min = INF;
        public long times = 1;
        public void add(V to, long cost) {
            es.add(new E(to, cost));
        }
    }

    public static class E implements Comparable<E> {
        public V to;
        public long cost;

        public E(V to, long cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(E o) {
            return Long.compare(cost, o.cost);
        }
    }
}
