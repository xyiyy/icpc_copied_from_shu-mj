package main;

import com.shu_mj.graph.BipartiteMatching;
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
        int n = in.nextInt();
        int k = in.nextInt();
        V[] vs = new V[n * 2];
        final int R = 0;
        final int C = n;
        for (int i = 0; i < n * 2; i++) vs[i] = new V();
        for (int i = 0; i < k; i++) {
            int r = in.nextInt() - 1;
            int c = in.nextInt() - 1;
            vs[r + R].connect(vs[c + C]);
        }
        out.println(BipartiteMatching.bipartiteMatching(vs));
    }
    class V extends BipartiteMatching.V {

    }
}
