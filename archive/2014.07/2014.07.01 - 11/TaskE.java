package main;

import com.shu_mj.geo.P;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskE {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        for (int i = 1; i <= n; i++) {
            out.println("Case " + i + ":");
            solve();
        }
    }

    private void solve() {
        P[] ps = new P[4];
        for (int i = 0; i < 4; i++) {
            ps[i] = new P(in.nextInt(), in.nextInt());
        }
        ps = P.convexHull(ps);
        if (P.contains(ps, new P(in.nextInt(), in.nextInt())) >= 0)
            out.println("In");
        else
            out.println("No");
    }

}
