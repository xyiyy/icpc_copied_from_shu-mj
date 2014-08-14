package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class E {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int f = in.nextInt();
        while (f-- != 0)
            solve(in, out);
    }

    final int INF = Integer.MAX_VALUE / 4;

    private void solve(Scanner in, PrintWriter out) {
        int n = in.nextInt(), m = in.nextInt(), w = in.nextInt();
        V[] vs = new V[n];
        for (int i = 0; i < n; i++) {
            vs[i] = new V();
        }
        for (int i = 0; i < m; i++) {
            int a = in.nextInt() - 1, b = in.nextInt() - 1, d = in.nextInt();
            vs[a].connect(vs[b], d);
            vs[b].connect(vs[a], d);
        }
        for (int i = 0; i < w; i++) {
            int a = in.nextInt() - 1, b = in.nextInt() - 1, d = in.nextInt();
            vs[a].connect(vs[b], -d);
        }
        if (spfa(vs[0], n)) out.println("NO");
        else out.println("YES");
    }

    private boolean spfa(V s, int n) {
        PriorityQueue<Eg> que = new PriorityQueue<Eg>();
        s.min = 0;
        s.times++;
        que.add(new Eg(s, 0));
        while (!que.isEmpty()) {
            Eg crtE = que.poll();

            V crt = que.poll();
            for (Eg e : crt.es) {
                if (e.to.min > crt.min + e.cost) {
                    e.to.min = crt.min + e.cost;
                    que.add(e.to);
                    e.to.times++;
                    if (e.to.times >= n) return false;
                }
            }
        }
        return true;
    }

    class V {
        int min = INF;
        int times = 0;
        List<Eg> es = new ArrayList<Eg>();
        void connect(V v, int cost) {
            es.add(new Eg(v, cost));
        }
    }
    class Eg implements Comparable<Eg> {
        V to;
        int cost;

        Eg(V v, int cost) {
            this.to = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Eg o) {
            return cost - o.cost;
        }
    }
}
