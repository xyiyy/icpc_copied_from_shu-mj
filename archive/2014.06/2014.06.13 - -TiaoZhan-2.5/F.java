package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class F {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt(), m = in.nextInt(), x = in.nextInt();
        int[][] maps = in.nextIntMatrix(m, 3);
        V[] vs = new V[n], rs = new V[n];
        for (int i = 0; i < n; i++) {
            vs[i] = new V();
            rs[i] = new V();
        }
        for (int i = 0; i < maps.length; i++) {
            int[] map = maps[i];
            vs[map[0] - 1].connect(vs[map[1] - 1], map[2]);
            rs[map[1] - 1].connect(rs[map[0] - 1], map[2]);
        }
        dij(vs[x - 1]);
        dij(rs[x - 1]);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (i != x - 1) {
                ans = Math.max(ans, vs[i].min + rs[i].min);
            }
        }
        out.println(ans);
    }

    private void dij(V s) {
        PriorityQueue<E> que = new PriorityQueue<E>();
//        Queue<V> que = new LinkedList<V>();
        s.min = 0;
        s.inQue = true;
        que.add(new E(s, 0));
//        que.R(s);
        while (!que.isEmpty()) {
            E crtE = que.poll();
//            if (crtE.to.min < crtE.cost) continue;
            V crt = crtE.to;
//            V crt = que.poll();
            crt.inQue = false;
            for (E e : crt.es) {
                if (e.to.min > e.cost + crt.min) {
                    e.to.min = e.cost + crt.min;
//                    que.R(new E(e.to, e.to.min));
                    if (!e.to.inQue) {
                        e.to.inQue = true;
//                        que.R(e.to);
                        que.add(new E(e.to, e.to.min));
                    }
                }
            }
        }
    }

    final int INF = Integer.MAX_VALUE / 4;
    class V {
        int min = INF;
        boolean inQue = false;
        List<E> es = new ArrayList<E>();
        void connect(V v, int cost) {
            es.add(new E(v, cost));
        }
    }
    class E implements Comparable<E> {
        V to;
        int cost;

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
