package main;

import com.shu_mj.graph.HopcroftKarp;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task1654 {
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
            out.printf("Case :%d%n", i);
            solve();
        }
    }

    private void solve() {
        int n = in.nextInt();
        int m = in.nextInt();
        char[][] css = new char[n][];
        for (int i = 0; i < n; i++) {
            css[i] = in.next().toCharArray();
        }

        int row = 0, col = 0;
        int[][] rid = new int[n][m];
        int[][] cid = new int[n][m];
        Algo.fill(rid, -1);
        Algo.fill(cid, -1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (rid[i][j] != -1) continue;
                if (css[i][j] == 'o') {
                    rid[i][j] = row;
                    for (int v = j - 1; v >= 0; v--) {
                        if (css[i][v] == '#') break;
                        if (css[i][v] == 'o') rid[i][v] = row;
                    }
                    for (int v = j + 1; v < m; v++) {
                        if (css[i][v] == '#') break;
                        if (css[i][v] == 'o') rid[i][v] = row;
                    }
                    row++;
                }
            }
        }
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                if (cid[i][j] != -1) continue;
                if (css[i][j] == 'o') {
                    cid[i][j] = col;
                    for (int u = i - 1; u >= 0; u--) {
                        if (css[u][j] == '#') break;
                        if (css[u][j] == 'o') cid[u][j] = col;
                    }
                    for (int u = i + 1; u < n; u++) {
                        if (css[u][j] == '#') break;
                        if (css[u][j] == 'o') cid[u][j] = col;
                    }
                    col++;
                }
            }
        }
        HopcroftKarp.V[] vs = new HopcroftKarp.V[row + col];

        for (int i = 0; i < row + col; i++) vs[i] = new HopcroftKarp.V();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (css[i][j] == 'o') {
                    vs[rid[i][j]].connect(vs[cid[i][j] + row]);
                }
            }
        }
        out.println(HopcroftKarp.hopcroftKarp(vs));
    }

}
