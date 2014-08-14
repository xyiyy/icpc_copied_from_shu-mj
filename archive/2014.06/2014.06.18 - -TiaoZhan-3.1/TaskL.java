package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskL {
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
        V[] vs = new V[n];
        for (int i = 0; i < n; i++) {
            vs[i] = new V();
        }
        int[] is = new int[m + 1];
        for (int i = 0; i < m; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            int l = in.nextInt();
            vs[a].connect(vs[b], l);
            is[i] = l;
        }
        Arrays.sort(is);
        is = Algo.unique(is);
        int l = 0, r = is.length;
        while (l < r) {
            int mid = (l + r) / 2;
            if (fit(vs, is[mid], k)) r = mid;
            else l = mid + 1;
        }
        if (l == is.length) out.println(-1);
        else out.println(is[l]);
    }

    final int INF = 12341234;
    private boolean fit(V[] vs, int len, int k) {
        for (V v : vs) {
            v.min = INF;
            v.inQue = false;
            for (E e : v.es) {
                e.cost = (e.w <= len ? 0 : 1);
            }
        }
        Queue<V> que = new LinkedList<V>();
        que.add(vs[0]);
        vs[0].min = 0;
        vs[0].inQue = true;
        while (!que.isEmpty()) {
            V crt = que.poll();
            crt.inQue = false;
            for (E e : crt.es) {
                if (e.to.min > crt.min + e.cost) {
                    e.to.min = crt.min + e.cost;
                    if (!e.to.inQue) {
                        e.to.inQue = true;
                        que.add(e.to);
                    }
                }
            }
        }
        return vs[vs.length - 1].min <= k;
    }

    class V {
        boolean inQue;
        List<E> es = new ArrayList<E>();
        int min;
        void connect(V to, int cost) {
            es.add(new E(to, cost));
            to.es.add(new E(this, cost));
        }
    }
    class E {
        V to;
        int w;
        int cost;

        E(V to, int w) {
            this.to = to;
            this.w = w;
        }
    }
}
