package test.on2014_08.on2014_08_08_NCEPU.TaskBB;



import com.shu_mj.graph.BipartiteMatching;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskBB {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        while (in.hasNext()) {
            int r = in.nextInt();
            int c = in.nextInt();
            int n = in.nextInt();
            if (r == 0 && c == 0 && n == 0) break;
            solve(r, c, n);
        }
    }

    private void solve(int r, int c, int n) {
        BipartiteMatching.V[] vs = new BipartiteMatching.V[r + c];
        for (int i = 0; i < vs.length; i++) vs[i] = new BipartiteMatching.V();
        while (n-- != 0) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            vs[u].connect(vs[v + r]);
        }
        int ans = BipartiteMatching.bipartiteMatching(vs);
        for (BipartiteMatching.V v : vs) v.used = false;
        for (int i = 0; i < r; i++) if (vs[i].pair == null) {
            BipartiteMatching.dfs(vs[i]);
        }
        out.print(ans);
        for (int i = 0; i < r; i++) if (!vs[i].used) out.print(" r" + (i + 1));
        for (int j = 0; j < c; j++) if (vs[j + r].used) out.print(" c" + (j + 1));
        out.println();
    }
}
