package main;



import com.shu_mj.ds.UnionFindSet;
import com.shu_mj.graph.SCC;
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
        int n = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();
        int[] is = in.nextIntArray(n);
        Map<Integer, Integer> id = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            id.put(is[i], i);
        }
        SCC.V[] vs = new SCC.V[n * 2];
        for (int i = 0; i < vs.length; i++) vs[i] = new SCC.V();
        UnionFindSet uf = new UnionFindSet(n * 2);
        for (int i = 0; i < n; i++) {
            if (id.containsKey(a - is[i])) {
                int j = id.get(a - is[i]);
                vs[i].add(vs[j]);
                uf.union(i, j);
            } else {
                vs[i].add(vs[i + n]);
                uf.union(i, i + n);
            }
            if (id.containsKey(b - is[i])) {
                int j = id.get(b - is[i]);
                vs[i + n].add(vs[j + n]);
                uf.union(i + n, j + n);
            } else {
                vs[i + n].add(vs[i]);
                uf.union(i + n, i);
            }
            if (is[i] * 2 != a && is[i] * 2 != b && id.containsKey(a - is[i]) && id.containsKey(b - is[i])) {
                int x = id.get(a - is[i]);
                int y = id.get(b - is[i]);
                vs[i].add(vs[y]);
                vs[i + n].add(vs[x + n]);
                uf.union(i, y);
                uf.union(i + n, x + n);
            }
        }
        SCC.scc(vs);
        for (int i = 0; i < n; i++) {
            if (vs[i].comp == vs[i + n].comp) {
                out.println("NO");
                return ;
            }
        }
        out.println("YES");
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        for (int i = 0; i < n; i++) {
            if (vs[i].comp > vs[i + n].comp && uf.isSame(i, i + n)) {
                ans[i] = 0;
            } else {
                ans[i] = 1;
            }
        }

        out.println();
    }
}
