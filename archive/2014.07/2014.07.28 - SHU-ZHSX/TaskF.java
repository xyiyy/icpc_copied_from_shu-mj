package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskF {
    Scanner in;
    PrintWriter out;
    private int[] pre;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int d = in.nextInt();
        V[] vs = new V[n];
        int[] xs = new int[n];
        int[] ys = new int[n];
        pre = new int[n];
        for (int i = 0; i < n; i++) {
            xs[i] = in.nextInt();
            ys[i] = in.nextInt();
            vs[i] = new V();
            pre[i] = i;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) if (i != j) {
                if (dis(xs[i], ys[i], xs[j], ys[j]) <= d * d) vs[i].add(j);
            }
        }
        boolean[] ok = new boolean[n];
        while (in.hasNext()) {
            if (in.next().charAt(0) == 'S') {
                int u = in.nextInt() - 1;
                int v = in.nextInt() - 1;
                if (find(u) == find(v)) out.println("SUCCESS");
                else out.println("FAIL");
            } else {
                int x = in.nextInt() - 1;
                ok[x] = true;
                for (int i : vs[x]) if (ok[i]) union(i, x);
            }
        }
    }

    private void union(int i, int x) {
        pre[find(i)] = find(x);
    }

    private int find(int u) {
        if (u != pre[u]) pre[u] = find(pre[u]);
        return pre[u];
    }

    private int dis(int x, int y, int x1, int y1) {
        x = x - x1;
        y = y - y1;
        return x * x + y * y;
    }

    class V extends ArrayList<Integer> {

    }
}
