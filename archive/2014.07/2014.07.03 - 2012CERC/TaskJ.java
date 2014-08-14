package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskJ {
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
        int[] cs = new int[n];
        for (int i = 0; i < n; i++) cs[i] = in.nextInt() - 1;
        V[] vs = new V[n];
        for (int i = 0; i < n; i++) vs[i] = new V();
        int[] ind = new int[n];
        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            vs[u].add(v);
            ind[v]++;
        }
        out.println(Math.min(solve(cs, ind.clone(), vs, n, 0), solve(cs, ind.clone(), vs, n, 1)));
    }

    private int solve(int[] cs, int[] ind, V[] vs, int n, int crt) {
        Queue<Integer>[] qs = new Queue[]{new LinkedList<Integer>(), new LinkedList<Integer>()};
        for (int i = 0; i < n; i++) if (ind[i] == 0) {
            qs[cs[i]].add(i);
        }
        int res = 0;
        while (!qs[0].isEmpty() || !qs[1].isEmpty()) {
            if (qs[crt].isEmpty()) {
                crt ^= 1;
                res++;
            }
            int v = qs[crt].poll();
            for (int u : vs[v]) {
                ind[u]--;
                if (ind[u] == 0) {
                    qs[cs[u]].add(u);
                }
            }
        }
        return res;
    }

    class V extends ArrayList<Integer> {
    }
}
