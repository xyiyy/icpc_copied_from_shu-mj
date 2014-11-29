package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class USACO {
    /*
    LANG: JAVA
    TASK: concom
     */
    Scanner in;
    PrintWriter out;
    private boolean[][] con;
    private int[][] own;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    final int N = 101;
    void run() {
        int n = in.nextInt();
        con = new boolean[N][N];
        own = new int[N][N];
        for (int i = 1; i < N; i++) con[i][i] = true;
        for (int i = 0; i < n; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.nextInt();
            addOwn(a, b, c);
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                if (i != j && con[i][j]) out.println(i + " " + j);
            }
        }
    }

    private void addOwn(int a, int b, int c) {
        for (int i = 1; i < N; i++) {
            if (con[i][a]) own[i][b] += c;
        }
        for (int i = 1; i < N; i++) {
            if (own[i][b] > 50) addCon(i, b);
        }
    }

    private void addCon(int a, int b) {
        if (con[a][b]) return ;
        con[a][b] = true;
        for (int i = 1; i < N; i++) {
            own[a][i] += own[b][i];
        }
        for (int i = 1; i < N; i++) {
            if (con[i][a]) addCon(i, b);
        }
        for (int i = 1; i < N; i++) {
            if (own[a][i] > 50) addCon(a, i);
        }
    }
}
