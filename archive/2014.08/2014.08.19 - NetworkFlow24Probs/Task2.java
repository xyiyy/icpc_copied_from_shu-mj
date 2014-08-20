package main;



import com.shu_mj.graph.Dinic;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task2 {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int m = in.nextInt();
        int n = in.nextInt();
        int[] mv = new int[m];
        int[] nv = new int[n];
        Dinic.V[] vs = new Dinic.V[n + m + 2];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new Dinic.V();
        }
        Dinic.V s = vs[n + m];
        Dinic.V t = vs[n + m + 1];
        int total = 0;
        for (int i = 0; i < m; i++) {
            String[] ss = in.nextLine().split(" ");
            int sn = ss.length;
            int[] is = new int[sn];
            for (int j = 0; j < sn; j++) is[j] = Integer.parseInt(ss[j]);
            int val = is[0];
            total += val;
            s.add(vs[i], val);
            for (int j = 1; j < sn; j++) {
                vs[i].add(vs[m + is[j] - 1], Dinic.INF);
            }
        }
        for (int i = 0; i < n; i++) {
            vs[m + i].add(t, in.nextInt());
        }
        int ans = total - Dinic.dinic(s, t);
        for (int i = 0; i < m; i++) {
            if (vs[i].p == s.p) out.print((i + 1) + " ");
        }
        out.println();
        for (int i = 0; i < n; i++) {
            if (vs[i + m].p == s.p) out.print((i + 1) + " ");
        }
        out.println();
        out.println(ans);
    }
}
