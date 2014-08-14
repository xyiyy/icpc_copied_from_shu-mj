package main;

import com.shu_mj.ds.Intervals;
import com.shu_mj.geo.P;
import com.shu_mj.graph.Dij;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task3549 {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        P s = new P(in.nextDouble(), in.nextDouble());
        P t = new P(in.nextDouble(), in.nextDouble());
        int k = in.nextInt();
        P[] ps = new P[k];
        double[] rs = new double[k];
        for (int i = 0; i < k; i++) {
            ps[i] = new P(in.nextDouble(), in.nextDouble());
            rs[i] = in.nextDouble();
        }
        List<P> cs = new ArrayList<P>();
        cs.add(s);
        cs.add(t);
        for (int i = 0; i < k; i++) {
            for (int j = i + 1; j < k; j++) {
                P[] cc = P.isCC(ps[i], rs[i], ps[j], rs[j]);
                if (cc.length != 0) {
                    cs.add(cc[0]);
                    cs.add(cc[1]);
                }
            }
        }
        P[] ccs = cs.toArray(new P[0]);
        int n = ccs.length;
        V[] vs = new V[n];
        for (int i = 0; i < n; i++) {
            vs[i] = new V();
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                Intervals<Double, Boolean> IntX = null, IntY = null;
                boolean b = Math.abs(ccs[i].x - ccs[j].x) > P.EPS;
                if (b) IntX = new Intervals<Double, Boolean>(-INF, INF, false);
                else IntY = new Intervals<Double, Boolean>(-INF, INF, false);
                for (int c = 0; c < k; c++) {
                    P[] cl = P.isCL(ps[c], rs[c], ccs[i], ccs[j]);
                    if (cl.length != 0) {
                        if (b) {
                            double x0 = Math.min(cl[0].x, cl[1].x), x1 = Math.max(cl[0].x, cl[1].x);
                            IntX.paint(x0, x1, true);
                        } else {
                            double y0 = Math.min(cl[0].y, cl[1].y), y1 = Math.max(cl[0].y, cl[1].y);
                            IntY.paint(y0, y1, true);
                        }
                    }
                }
                double x0 = Math.min(ccs[i].x, ccs[j].x), x1 = Math.max(ccs[i].x, ccs[j].x);
                double y0 = Math.min(ccs[i].y, ccs[j].y), y1 = Math.max(ccs[i].y, ccs[j].y);
                if (b && IntX.sameColor(x0, x1 + P.EPS, true) || !b && IntY.sameColor(y0, y1 + P.EPS, true)) {
                    double dis = ccs[i].sub(ccs[j]).abs();
                    vs[i].add(vs[j], dis);
                    vs[j].add(vs[i], dis);
                }
            }
        }
        dijkstra(vs[0]);
        out.printf("%.5f%n", vs[1].min);
    }

    public static void dijkstra(V s) {
        PriorityQueue<E> que = new PriorityQueue<E>();
        s.min = 0;
        que.offer(new E(s, 0));
        while (!que.isEmpty()) {
            E crt = que.poll();
            if (crt.cost > crt.to.min) continue;
            for (E e : crt.to.es) {
                if (crt.cost + e.cost < e.to.min) {
                    e.to.min = crt.cost + e.cost;
                    que.offer(new E(e.to, e.to.min));
                }
            }
        }
    }

    public static double INF = 1e100;

    public static class V {
        public ArrayList<E> es = new ArrayList<E>();
        public double min = INF;

        public void add(V to, double cost) {
            es.add(new E(to, cost));
        }
    }

    public static class E implements Comparable<E> {
        public V to;
        public double cost;

        public E(V to, double cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(E o) {
            return Double.compare(cost, o.cost);
        }
    }

}
