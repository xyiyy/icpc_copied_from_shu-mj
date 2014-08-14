package test.on2014_08.on2014_08_01_8_1.TaskD;



import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskD {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            if (i != 1) out.println();
            out.printf("Case %d: ", i);
            solve();
        }
    }

    private void solve() {
        int n = in.nextInt();
        int m = in.nextInt();
        V[] vs = new V[n];
        for (int i = 0; i < n; i++) {
            vs[i] = new V();
        }
        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            vs[u].add(v);
            vs[v].add(u);
            vs[u].ind++;
            vs[v].ind++;
        }
        dfs(vs, 0);
        for (int i = 0; i < n; i++) if (!vs[i].vis) {out.println(0); return ;}
        for (V v : vs) if (v.ind > 3) {out.println(0); return ;}
        if (n == m + 1) work125734(vs);
        else if (n == m) work069(vs);
        else if (n == m - 1) work8(vs);
        else out.println(0);
    }

    private void work069(V[] vs) {
        int[] is = new int[4];
        for (V v : vs) is[v.ind]++;

        if (is[1] == 0 && is[3] == 0 && vs.length % 6 == 0) {
            out.println(1);
            out.println(0 + " " + (vs.length / 6 - 1));
        } else if (is[1] == 1 && is[3] == 1) work69(vs);
        else out.println(0);
    }

    private void work69(V[] vs) {
        for (V v : vs) v.vis = v.ind == 3;
        for (V v : vs) v.vis = v.ind == 3;
        List<Integer> lens = new ArrayList<Integer>();
        for (V v : vs) if (v.ind == 1) lens.add(flood(vs, v));
        for (V v : vs) if (!v.vis) lens.add(1 + flood(vs, v));
        if (lens.size() != 2) {
            out.println(0);
            return;
        }
        int a = lens.get(0);
        int b = lens.get(1);
        if (a % 2 == 0 && b == 2 * a) {
            out.println(2);
            out.println(6 + " " + (a / 2 - 1));
            out.println(9 + " " + (a / 2 - 1));
        } else {
            out.println(0);
        }
    }

    private void work8(V[] vs) {
        int[] is = new int[4];
        for (V v : vs) is[v.ind]++;
        if (!(is[3] == 2 && is[1] == 0)) {
            out.println(0);
            return ;
        }
        for (V v : vs) v.vis = v.ind == 3;
        List<Integer> lens = new ArrayList<Integer>();
        for (V v : vs) if (!v.vis) lens.add(flood(vs, v));
        while (lens.size() < 3) lens.add(0);
        for (int i = 0; i < lens.size(); i++) lens.set(i, lens.get(i) + 1);
        Collections.sort(lens);
        int a = lens.get(0);
        int b = lens.get(1);
        int c = lens.get(2);
        if (b == c && b == 3 * a) {
            out.println(1);
            out.println(8 + " " + (a - 1));
        } else {
            out.println(0);
        }
    }

    private Integer flood(V[] vs, V v) {
        v.vis = true;
        int res = 1;
        for (int u : v) if (!vs[u].vis) {
            res += flood(vs, vs[u]);
        }
        return res;
    }

    private void work125734(V[] vs) {
        int[] is = new int[4];
        for (V v : vs) is[v.ind]++;
        if (is[3] == 1 && is[1] == 3) work34(vs);
        else if (is[3] == 0 && is[1] == 2) work1257(vs);
        else out.println(0);
    }

    private void work1257(V[] vs) {
        List<P> ls = new ArrayList<P>();
        int e = vs.length - 1;
        if (e % 2 == 0) ls.add(new P(1, e / 2 - 1));
        if (e % 5 == 0) ls.add(new P(2, e / 5 - 1));
        if (e % 5 == 0) ls.add(new P(5, e / 5 - 1));
        if (e % 3 == 0) ls.add(new P(7, e / 3 - 1));
        out.println(ls.size());
        for (P p : ls) out.println(p.x + " " + p.y);
    }
    class P {
        int x;
        int y;

        P(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private void work34(V[] vs) {
        for (V v : vs) v.vis = v.ind == 3;
        List<Integer> lens = new ArrayList<Integer>();
        for (V v : vs) if (!v.vis) lens.add(flood(vs, v));
        Collections.sort(lens);
        int a = lens.get(0);
        int b = lens.get(1);
        int c = lens.get(2);
        if (b == c && b == a * 2) {
            out.println(1);
            out.println(3 + " " + (a - 1));
        } else if (a == b && c == a * 2) {
            out.println(1);
            out.println(4 + " " + (a - 1));
        } else {
            out.println(0);
        }
    }

    private void dfs(V[] vs, int c) {
        vs[c].vis = true;
        for (int u : vs[c]) if (!vs[u].vis) dfs(vs, u);
    }

    class V extends ArrayList<Integer> {
        boolean vis;
        int ind;
    }
}
