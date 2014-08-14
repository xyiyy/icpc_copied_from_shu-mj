package main;

import com.shu_mj.graph.MinCostFlow;
import com.shu_mj.tpl.Algo;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            out.printf("Case %d: ", i);
            solve(in, out);
        }
    }

    private void solve(Scanner in, PrintWriter out) {
        int n = in.nextInt(), m = in.nextInt();
        char[][] css = new char[n][];
        for (int i = 0; i < n; i++) {
            css[i] = in.next().toCharArray();
        }
        int[] row = new int[n];
        int[] col = new int[m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (css[i][j] == '1') {
                    row[i]++;
                    col[j]++;
                }
            }
        }
        int rowSum = (int) Algo.sum(row);
        int colSum = (int) Algo.sum(col);
        boolean r = rowSum % n == 0, c = colSum % m == 0;
        if (r && c) {
            out.println("both " + (calc(row, n, rowSum / n) + calc(col, m, colSum / m)));
        } else if (r) {
            out.println("row " + calc(row, n, rowSum / n));
        } else if (c) {
            out.println("column " + calc(col, m, colSum / m));
        } else {
            out.println("impossible");
        }
    }

    private int calc(int[] is, int n, int ave) {
        MinCostFlow.V[] vs = new MinCostFlow.V[n + 2];
        for (int i = 0; i < vs.length; i++) vs[i] = new MinCostFlow.V();
        MinCostFlow.V s = vs[n], t = vs[n + 1];
        for (int i = 0; i < n; i++) {
            s.add(vs[i], is[i], 0);
            vs[i].add(t, ave, 0);
            vs[i].add(vs[(i + 1) % n], MinCostFlow.INF, 1);
            vs[i].add(vs[(i + n - 1) % n], MinCostFlow.INF, 1);
        }
        return MinCostFlow.minCostFlow(vs, s, t, ave * n);
    }

}
