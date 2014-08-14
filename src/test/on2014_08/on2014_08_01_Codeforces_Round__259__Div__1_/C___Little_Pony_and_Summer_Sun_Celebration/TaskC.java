package test.on2014_08.on2014_08_01_Codeforces_Round__259__Div__1_.C___Little_Pony_and_Summer_Sun_Celebration;



import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskC {
    Scanner in;
    PrintWriter out;
    private List<Integer> path;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int m = in.nextInt();
        V[] vs = new V[n];
        for (int i = 0; i < n; i++) {
            vs[i] = new V(i);
        }
        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            vs[u].add(vs[v]);
            vs[v].add(vs[u]);
        }
        for (int i = 0; i < n; i++) vs[i].x = in.nextInt() == 1;
        V begin = null;
        for (V v : vs) if (v.x) begin = v;
        if (begin == null) { out.println(0); return ; }
        path = new ArrayList<Integer>();
        dfs(begin, null);
        if (begin.x) { path.remove(0); begin.x = false; }
        for (V v : vs) if (v.x) { out.println(-1); return ; }
        out.println(path.size());
        for (int i : path) out.print((i + 1) + " ");
        out.println();
    }

    private void dfs(V v, V fa) {
        v.visit();
        for (V u : v) if (!u.vis && u != fa) {
            dfs(u, v);
            v.visit();
            if (u.x) {
                u.visit();
                v.visit();
            }
        }
    }

    class V extends ArrayList<V> {
        int id;
        boolean x;
        boolean vis;

        V(int id) {
            this.id = id;
        }

        public void visit() {
            path.add(id);
            x ^= true;
            vis = true;
        }
    }
}
