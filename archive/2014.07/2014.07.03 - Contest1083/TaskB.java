package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskB {
    Scanner in;
    PrintWriter out;
    private int[] pre;
    private int[] num;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        while (in.hasNext())
            solve();
    }

    private void solve() {
        int n = in.nextInt();
        int[][] mat = in.nextIntMatrix(n, 2);
        int max = mat[0][0];
        for (int[] is : mat) max = Math.max(max, Algo.max(is));
        pre = new int[max + 1];
        num = new int[max + 1];
        Arrays.fill(num, 1);
        for (int i = 0; i <= max; i++) pre[i] = i;
        for (int i = 0; i < n; i++) {
            join(mat[i][0], mat[i][1]);
        }
        out.println(Algo.max(num));
    }

    int find(int x) {
        if (pre[x] != x) pre[x] = find(pre[x]);
        return pre[x];
    }
    private void join(int u, int v) {
        u = find(u);
        v = find(v);
        if (u != v) {
            pre[u] = v;
            num[v] += num[u];
        }
    }
}
