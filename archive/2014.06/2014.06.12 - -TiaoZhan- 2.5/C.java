package main;

import com.shu_mj.tpl.Algo;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class C {
    private static final int INF = Integer.MAX_VALUE / 4;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt(), ml = in.nextInt(), md = in.nextInt();
        V[] vs = new V[n];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new V();
        }
        for (int i = 0; i < vs.length; i++) {
            V v = vs[i];
            if (i != 0) v.connect(vs[i - 1], 0);
        }
        for (int i = 0; i < ml; i++) {
            int al = in.nextInt() - 1;
            int bl = in.nextInt() - 1;
            int dl = in.nextInt();
            vs[al].connect(vs[bl], dl);
        }
        for (int i = 0; i < md; i++) {
            int ad = in.nextInt() - 1;
            int bd = in.nextInt() - 1;
            int dd = in.nextInt();
            vs[bd].connect(vs[ad], -dd);
        }
        if (!spfa(vs[0], n)) out.println(-1);
        else if (vs[n - 1].min == INF) out.println(-2);
        else out.println(vs[n - 1].min);
    }

    private boolean spfa(V s, int n) {
        s.times++;
        s.min = 0;
        PriorityQueue<E> que = new PriorityQueue<E>(); // dij 的优先队列
        que.add(new E(s, 0));
        while (!que.isEmpty()) {
            E crtE = que.poll();
            V crt = crtE.to;
            if (crtE.cost > crt.min) continue; // 过时的节点
            for (E e : crt.es) {
                if (e.to.min > e.cost + crt.min) {
                    e.to.min = e.cost + crt.min;
                    que.add(new E(e.to, e.to.min));
                    e.to.times++;
                    if (e.to.times >= n) return false; // 入队达到 n 次，有负环
                }
            }
        }
        return true;
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
    class V {
        int min = INF;
        int times = 0;
        ArrayList<E> es = new ArrayList<E>();

        void connect(V v, int cost) {
            es.add(new E(v, cost));
        }
    }
}
