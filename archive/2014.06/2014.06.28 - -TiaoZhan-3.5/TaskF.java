package main;





import com.shu_mj.graph.MinCostFlow;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskF {
    Scanner in;
    PrintWriter out;
    private int n;
    private int m;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        n = in.nextInt();
        m = in.nextInt();
        Item[] bs = new Item[n];
        Item[] ps = new Item[m];
        for (int i = 0; i < n; i++) {
            bs[i] = new Item(in.nextInt(), in.nextInt(), in.nextInt());
        }
        for (int i = 0; i < m; i++) {
            ps[i] = new Item(in.nextInt(), in.nextInt(), in.nextInt());
        }
        int[][] mat = in.nextIntMatrix(n, m);
        int[][] g = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                g[i][j] = cost(bs[i], ps[j]);
            }
        }

        V[] vs = new V[n + m + 2];
        for (int i = 0; i < n + m + 2; i++) {
            vs[i] = new V(i);
        }
        V s = vs[n + m];
        V t = vs[n + m + 1];
//        for (int i = 0; i < n; i++) {
//            s.connect(vs[i], 0);
//        }
        for (int j = 0; j < m; j++) {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                int c = cost(bs[i], ps[j]);
                vs[i].connect(vs[n + j], c);
                if (mat[i][j] > 0) vs[n + j].connect(vs[i], -c);
                sum += mat[i][j];
            }
            if (sum > 0) t.connect(vs[n + j], 0);
            if (sum < ps[j].c) vs[n + j].connect(t, 0);
        }
        if (spfa(mat, t)) out.println("OPTIMAL");
        else {
            out.println("SUBOPTIMAL");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    out.print(mat[i][j]);
                    if (j == m - 1) out.println();
                    else out.print(' ');
                }
            }
        }
    }

    private boolean spfa(int[][] mat, V s) {
        Queue<V> que = new LinkedList<V>();
        s.min = 0;
        s.inQue = true;
        que.add(s);
        s.times++;
        while (!que.isEmpty()) {
            V v = que.poll();
            v.inQue = false;
            for (E e : v.es) {
                V u = e.to;
                if (u.min > v.min + e.cost) {
                    u.min = v.min + e.cost;
                    u.pre = v;
                    if (!u.inQue) {
                        u.inQue = true;
                        que.add(u);
                        u.times++;
                        if (u.times > n + m + 2) {
                            circle(mat, u);
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private void circle(int[][] mat, V v) {
        for (;;) {
            if (!v.vis) {
                v.vis = true;
                v = v.pre;
            } else {
                break;
            }
        }
        V id = v;
        do {
            int from = v.pre.id;
            int to = v.id;
            if (from < n && to >= n && to < n + m) mat[from][to - n]++;
            if (to < n && from >= n && from < n + m) mat[to][from - n]--;
            v = v.pre;
        } while (v != id);
        /*for (;;) {
            V u = v.pre;
            if (u.vis) break;
            u.vis = true;
            int ui = u.id;
            int vi = v.id;
            v = u;
            Algo.debug(ui, "->", vi);
            if (ui >= n + m || vi >= n + m) continue;
            if (vi >= n) {
                mat[ui][vi - n]++;
            } else {
                mat[vi][ui - n]--;
            }
        }
        */
    }


    final int INF = 12341234;

    class V {
        int id;
        int min = INF;
        boolean vis = false;
        boolean inQue = false;
        int times = 0;
        V pre = null;
        List<E> es = new ArrayList<E>();

        V(int id) {
            this.id = id;
        }

        void connect(V to, int cost) {
            es.add(new E(cost, to));
        }
    }

    class E {
        int cost;
        V to;

        E(int cost, V to) {
            this.cost = cost;
            this.to = to;
        }
    }
    private int cost(Item a, Item b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y) + 1;
    }

    class Item {
        int x;
        int y;
        int c;

        Item(int x, int y, int c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }
    }
}
