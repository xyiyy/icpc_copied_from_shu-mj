package main;

import com.shu_mj.graph.BipartiteMatching;
import com.shu_mj.graph.Dinic;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskN {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int k = in.nextInt();
        int c = in.nextInt();
        int m = in.nextInt();
        int n = k + c;
        int[][] mat = in.nextIntMatrix(n, n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && mat[i][j] == 0) mat[i][j] = INF;
            }
        }
        floyd(mat);
        int l = 0, r = 200 * (k + c - 1) + 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (can(mat, k, c, m, n, mid)) r = mid;
            else l = mid + 1;
        }
        out.println(l);
    }

    final int INF = 12341234;
    private void floyd(int[][] mat) {
        int n = mat.length;
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    mat[i][j] = Math.min(mat[i][j], mat[i][k] + mat[k][j]);
                }
            }
        }
    }

    private boolean can(int[][] mat, int k, int c, int m, int n, int mid) {
        Dinic.V[] vs = new Dinic.V[k + c + 2];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new Dinic.V();
        }
        Dinic.V s = vs[k + c];
        Dinic.V t = vs[k + c + 1];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < c; j++) {
                if (mat[i][k + j] <= mid) {
                    vs[i].add(vs[k + j], 1);
                }
            }
        }
        for (int i = 0; i < k; i++) {
            s.add(vs[i], m);
        }
        for (int j = 0; j < c; j++) {
            vs[j + k].add(t, 1);
        }
        return Dinic.dinic(s, t) == c;
    }

}
