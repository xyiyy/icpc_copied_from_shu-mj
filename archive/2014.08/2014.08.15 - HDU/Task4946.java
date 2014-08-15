package main;

import com.shu_mj.geo.P;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task4946 {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int cs = 1;
        while (in.hasNext()) {
            int n = in.nextInt();
            if (n == 0) break;
            out.printf("Case #%d: ", cs++);
            solve(n);
        }
    }

    private void solve(int n) {
        int[] xs = new int[n];
        int[] ys = new int[n];
        int[] vs = new int[n];
        for (int i = 0; i < n; i++) {
            xs[i] = in.nextInt();
            ys[i] = in.nextInt();
            vs[i] = in.nextInt();
        }
        int maxV = Algo.max(vs);
        if (maxV == 0) {
            for (int i = 0; i < n; i++) out.print('0');
            out.println();
            return ;
        }
        Set<P> toConvexSet = new TreeSet<P>();
        Set<P> samePoint = new TreeSet<P>();
        Map<P, Integer> index = new HashMap<P, Integer>();
        for (int i = 0; i < n; i++) if (vs[i] == maxV) {
            P p = new P(xs[i], ys[i]);
            if (toConvexSet.contains(p)) samePoint.add(p);
            else {
                toConvexSet.add(p);
                index.put(p, i);
            }
        }
        P[] convex = P.convexHull(toConvexSet.toArray(new P[0]));
        boolean[] ans = new boolean[n];
        for (P p : convex) if (!samePoint.contains(p)) ans[index.get(p)] = true;
        for (int i = 0; i < n; i++) {
            out.print(ans[i] ? '1' : '0');
        }
        out.println();
    }
}
