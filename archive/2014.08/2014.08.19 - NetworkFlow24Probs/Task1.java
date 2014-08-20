package main;

import com.shu_mj.graph.BipartiteMatching;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task1 {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int m = in.nextInt();
        int n = in.nextInt();
        BipartiteMatching.V[] vs = new BipartiteMatching.V[n + m];
        Map<BipartiteMatching.V, Integer> index = new HashMap<BipartiteMatching.V, Integer>();
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new BipartiteMatching.V();
            index.put(vs[i], i);
        }
        while (in.hasNext()) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            if (a == -2 && b == -2) break;
            vs[a].connect(vs[b]);
        }
        int ans = BipartiteMatching.bipartiteMatching(vs);
        if (ans == 0) {
            out.println("No Solution!");
        } else {
            out.println(ans);
            for (int i = 0; i < m; i++) if (vs[i].pair != null) {
                out.println((i + 1) + " " + (index.get(vs[i].pair) + 1));
            }
        }
    }
}
