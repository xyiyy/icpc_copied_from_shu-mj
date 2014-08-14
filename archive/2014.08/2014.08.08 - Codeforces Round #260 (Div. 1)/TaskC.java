package main;



import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskC {
    Scanner in;
    PrintWriter out;
    private int[] pre;
    private int[] min;
    private int[] min2;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() { // update
        int n = in.nextInt();
        int m = in.nextInt();
        int q = in.nextInt();
        V[] vs = new V[n];
        for (int i = 0; i < n; i++) vs[i] = new V();
        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            vs[u].add(v);
            vs[v].add(u);
        }
        boolean[] vis = new boolean[n];
        pre = new int[n];
        min = new int[n];
        min2 = new int[n];
        for (int i = 0; i < n; i++) {
            pre[i] = i;
        }
        for (int i = 0; i < n; i++) {
            if (!vis[i]) build(vs, vis, i);
        }
        while (q-- != 0) {
            if (in.nextInt() == 1) {
                int u = in.nextInt() - 1;
                u = find(u);
                out.println(min[u] + min2[u]);
            } else {
                int u =  in.nextInt() - 1;
                int v = in.nextInt() - 1;
                join(u, v);
            }
        }
    }

    private void join(int u, int v) {
        u = find(u);
        v = find(v);
        if (u != v) {
            if (min[u] > min[v]) { int t = u; u = v; v = t; }
            pre[u] = v;
            if (min[u] + 1 > min[v]) {
                min2[v] = min[v];
                min[v] = min[u] + 1;
            } else if (min[u] + 1 > min2[v]) {
                min2[v] = min[u] + 1;
            }
        }
    }

    private int find(int u) {
        if (u != pre[u]) {
            pre[u] = find(pre[u]);
        }
        return pre[u];
    }

    int res;
    int dep;
    private void build(V[] vs, boolean[] vis, int v) {
        dep = -1;
        dfs1(vs, v, -1, 0, vis);
        dfs0(vs, res, -1, 0);
        dfs2(vs, res, -1, 0);
        for (int u : vs[res]) {
            int d = dfs3(vs, u, res, res, 1);
            if (d > min[res]) {
                min2[res] = min[res];
                min[res] = d;
            } else if (d > min2[res]) {
                min2[res] = d;
            }
        }
    }

    private void dfs0(V[] vs, int v, int fa, int dep) {
        if (dep > this.dep) {
            this.dep = dep;
            res = v;
        }
        for (int u : vs[v]) if (u != fa) {
            dfs0(vs, u, v, dep + 1);
        }
    }

    private int dfs3(V[] vs, int v, int fa, int root, int dep) {
        pre[v] = root;
        int res = dep;
        for (int u : vs[v]) if (u != fa) {
            res = Math.max(res, dfs3(vs, u, v, root, dep + 1));
        }
        return res;
    }

    private int dfs2(V[] vs, int v, int fa, int dep) {
        int max = dep;
        for (int u : vs[v]) if (u != fa) {
            max = Math.max(max, dfs2(vs, u, v, dep + 1));
        }
        if (max == this.dep && dep == this.dep / 2) res = v;
        return max;
    }

    private void dfs1(V[] vs, int v, int fa, int dep, boolean[] vis) {
        vis[v] = true;
        if (dep > this.dep) {
            this.dep = dep;
            res = v;
        }
        for (int u : vs[v]) if (u != fa) {
            dfs1(vs, u, v, dep + 1, vis);
        }
    }

    class V extends ArrayList<Integer> {

    }
}
