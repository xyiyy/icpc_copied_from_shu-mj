package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskI {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            out.printf("Case %d:%n", i);
            solve();
        }
    }

    private void solve() {
        int n = in.nextInt();
        int m = in.nextInt();
        int s = in.nextInt() - 1;
        V[] vs = new V[n];
        for (int i = 0; i < n; i++) {
            vs[i] = new V();
        }
        E[] es = new E[m];
        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            es[i] = new E(u, v, 0);
            in.nextInt();
            vs[u].add(es[i]);
            vs[v].add(es[i]);
        }
        Queue<Integer> que = new LinkedList<Integer>();
        que.add(s);
        vs[s].vis = true;
        int c = 1;
        while (!que.isEmpty()) {
            int crt = que.poll();
            V v = vs[crt];
            for (E e : v) {
                int u = e.u + e.v - crt;
                if (!vs[u].vis) {
                    vs[u].vis = true;
                    que.add(u);
                    e.c = c++;
                }
            }
        }
        for (E e : es) {
            if (e.c == 0) e.c = c++;
            out.println((e.u + 1) + " " + (e.v + 1) + " " + e.c);
        }
    }
    class E {
        int u;
        int v;
        int c;

        E(int u, int v, int c) {
            this.u = u;
            this.v = v;
            this.c = c;
        }
    }
    class V extends ArrayList<E> {
        boolean vis;
    }
}
