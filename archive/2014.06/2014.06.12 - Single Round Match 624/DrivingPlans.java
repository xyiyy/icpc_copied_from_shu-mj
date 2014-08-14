package main;

import com.shu_mj.tpl.Algo;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class DrivingPlans {
    public int count(int N, int[] A, int[] B, int[] C) {
        V[] vs = new V[N];
        for (int i = 0; i < N; i++) vs[i] = new V();
        for (int i = 0; i < A.length; i++) {
            vs[A[i] - 1].add(vs[B[i] - 1], C[i]);
            vs[B[i] - 1].add(vs[A[i] - 1], C[i]);
        }
        Algo.debug(vs[N - 1].min);
        dijkstra(vs[0]);
        if (vs[N - 1].zero) return -1;
        return vs[N - 1].cnt;
    }

    static void dijkstra(V s) {
        PriorityQueue<E> que = new PriorityQueue<E>();
        s.min = 0;
        s.cnt = 1;
        que.offer(new E(s, 0));
        while (!que.isEmpty()) {
            E crt = que.poll();
            if (crt.cost > crt.to.min) continue;
            for (E e : crt.to.es) {
                if (crt.cost + e.cost == e.to.min) {
                    e.to.cnt += crt.to.cnt;
                    e.to.zero |= crt.zero;
                }
                if (crt.cost + e.cost < e.to.min) {
                    e.to.min = crt.cost + e.cost;
                    e.to.cnt = crt.to.cnt;
                    e.to.zero = crt.zero || e.cost == 0;
                    que.offer(new E(e.to, e.to.min, crt.zero || e.cost == 0));
                }
            }
        }
    }

    static int INF = 1 << 29;

    static class V {
        ArrayList<E> es = new ArrayList<E>();
        int min = INF;
        int cnt = 0;
        boolean zero = false;

        public void add(V to, int cost) {
            es.add(new E(to, cost));
        }
    }

    static class E implements Comparable<E> {
        V to;
        int cost;
        boolean zero;

        public E(V to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        E(V to, int cost, boolean zero) {
            this.to = to;
            this.cost = cost;
            this.zero = zero;
        }

        @Override
        public int compareTo(E o) {
            return cost - o.cost;
        }
    }

}
