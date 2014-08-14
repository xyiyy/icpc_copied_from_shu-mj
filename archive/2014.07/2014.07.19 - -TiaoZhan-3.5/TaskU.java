package main;

import com.shu_mj.graph.MinCostFlow;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskU {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        while (in.hasNext()) {
            int n = in.nextInt();
            int m = in.nextInt();
            if (n == 0 && m == 0) break;
            solve(n, m);
        }
    }

    private void solve(int n, int m) {
        char[][] maps = new char[n][];
        for (int i = 0; i < n; i++) {
            maps[i] = in.next().toCharArray();
        }
        MinCostFlow.V[] vs = new MinCostFlow.V[n * m + 2];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new MinCostFlow.V();
        }
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        MinCostFlow.V s = vs[n * m];
        MinCostFlow.V t = vs[n * m + 1];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (maps[i][j] == 'H') vs[i * m + j].add(t, 1, 0);
                if (maps[i][j] == 'm') {
                    s.add(vs[i * m + j], 1, 0);
                    cnt++;
                }
                for (int k = 0; k < 4; k++) {
                    int x = i + dx[k];
                    int y = j + dy[k];
                    if (x >= 0 && x < n && y >= 0 && y < m) {
                        vs[i * m + j].add(vs[x * m + y], MinCostFlow.INF, 1);
                    }
                }
            }
        }
        out.println(MinCostFlow.minCostFlow(vs, s, t, cnt));
    }
}
