package main;

import com.shu_mj.ds.UnionFindSet;
import com.shu_mj.tpl.*;
import com.shu_mj.tpl.Scanner;

import java.io.PrintWriter;
import java.util.*;

public class USACO {
    /*
    LANG: JAVA
    TASK: agrinet
     */
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        P[] ps = new P[n * (n - 1) / 2];
        for (int i = 0, c = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int d = in.nextInt();
                if (i < j) {
                    ps[c++] = new P(d, i, j);
                }
            }
        }
        Arrays.sort(ps);
        UnionFindSet uf = new UnionFindSet(n);
        int sum = 0;
        for (P p : ps) {
            if (!uf.isSame(p.u, p.v)) {
                uf.union(p.u, p.v);
                sum += p.cost;
            }
        }
        out.println(sum);
    }

    class P implements Comparable<P> {
        int cost;
        int u;
        int v;

        P(int cost, int u, int v) {
            this.cost = cost;
            this.u = u;
            this.v = v;
        }

        @Override
        public int compareTo(P o) {
            return cost - o.cost;
        }
    }
}
