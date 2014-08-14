package main;

import com.shu_mj.graph.BipartiteMatching;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskS {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int r = in.nextInt();
        int c = in.nextInt();
        char[][] maps = new char[r][];
        for (int i = 0; i < r; i++) {
            maps[i] = in.next().toCharArray();
        }
        int[][] rows = new int[r][c];
        int[][] cols = new int[r][c];
        Algo.fill(rows, -1);
        int row = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (rows[i][j] == -1 && maps[i][j] == '*') {
                    for (int k = j; k < c; k++) {
                        if (maps[i][k] == '*') rows[i][k] = row;
                        else break;
                    }
                    row++;
                }
            }
        }
        int col = 0;
        Algo.fill(cols, -1);
        for (int j = 0; j < c; j++) {
            for (int i = 0; i < r; i++) {
                if (cols[i][j] == -1 && maps[i][j] == '*') {
                    for (int k = i; k < r; k++) {
                        if (maps[k][j] == '*') cols[k][j] = col;
                        else break;
                    }
                    col++;
                }
            }
        }
        BipartiteMatching.V[] vs = new BipartiteMatching.V[row + col];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new BipartiteMatching.V();
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (maps[i][j] == '*') {
                    vs[rows[i][j]].connect(vs[cols[i][j] + row]);
                }
            }
        }
        out.println(BipartiteMatching.bipartiteMatching(vs));
    }
}
