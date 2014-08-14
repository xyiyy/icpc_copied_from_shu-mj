package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskA {
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
        int[] vv = in.nextIntArray(n);
        E[] es = new E[m];
        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            int c = in.nextInt();
            es[i] = new E(u, v, c, vv[u] + vv[v]);
        }
        if (m == 0) {
            out.printf("%.15f%n", 0.0);
            return ;
        }
        Arrays.sort(es, new Comparator<E>() {
            @Override
            public int compare(E o1, E o2) {
                return o1.vCost * o2.cost - o2.vCost * o1.cost;
            }
        });
        out.printf("%.15f%n", es[m - 1].vCost * 1.0 / es[m - 1].cost);
    }
    class E {
        int u;
        int v;
        int cost;
        int vCost;

        E(int u, int v, int cost, int vCost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
            this.vCost = vCost;
        }
    }
}
