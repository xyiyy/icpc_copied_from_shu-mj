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
        int[] ind = new int[n];
        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            vs[u].add(v);
            ind[v]++;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for (int i = 0; i < n; i++) if (ind[i] == 0) pq.add(i);
        boolean f = true;
        while (!pq.isEmpty()) {
            int crt = pq.poll();
            if (f) f = false; else out.print(' ');
            out.print(crt + 1);
            for (int i : vs[crt]) {
                ind[i]--;
                if (ind[i] == 0) pq.add(i);
            }
        }
        out.println();
    }
    class V extends ArrayList<Integer> {

    }
}
