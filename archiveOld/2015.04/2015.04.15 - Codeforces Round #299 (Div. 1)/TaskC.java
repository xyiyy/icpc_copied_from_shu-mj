package main;

import com.shu_mj.geo.P;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskC {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        P[] ps = new P[n];
        for (int i = 0; i < n; i++) {
            ps[i] = new P(10000. / in.nextInt(), 10000. / in.nextInt());
        }
        P[] ps2 = ps.clone();
        Arrays.sort(ps2);
        ps2 = Algo.unique(ps2);
        P[] ch = P.convexHull(ps2);
        P[] ch2 = new P[ch.length * 2];
        for (int i = 0; i < ch.length; i++) ch2[i] = ch2[i + ch.length] = ch[i];
        int l = 0, r = 0;
        for (int i = 0; i < ch.length; i++) {
            if (ch[i].x < ch[l].x || ch[i].x == ch[l].x && ch[i].y < ch[l].y) l = i;
        }
        r = l;
        for (int i = l; i < ch2.length; i++) {
            if (ch2[i].y < ch2[r].y || ch2[i].y == ch2[r].y && ch2[i].x < ch2[r].x) r = i;
        }
        Set<P> set = new TreeSet<>();
        for (int i = l; i <= r; i++) {
            set.add(ch2[i]);
        }
        for (int i = 0; i < n; i++) {
            if (set.contains(ps[i])) {
                out.print((i + 1) + " ");
            }
        }
        out.println();
    }

}
