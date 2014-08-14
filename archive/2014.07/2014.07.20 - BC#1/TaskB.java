package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskB {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        while (t-- != 0)
            solve();
    }

    private void solve() {
        int n = in.nextInt();
        int m = in.nextInt();
        V[] vs = new V[n];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new V();
        }
        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            vs[u].add(v);
            vs[v].add(u);
        }
        int q = in.nextInt();
        while (q-- != 0) {
            if (in.nextInt() == 0) {
                int u = in.nextInt() - 1;
                int v = in.nextInt();
                vs[u].v += v;
            } else {
                int u = in.nextInt() - 1;
                int res = 0;
                for (int v : vs[u]) res += vs[v].v;
                out.println(res);
            }
        }
    }
    class V extends ArrayList<Integer> {
        int v;
    }
}
