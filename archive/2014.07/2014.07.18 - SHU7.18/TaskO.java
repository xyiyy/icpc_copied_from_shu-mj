package main;

import com.shu_mj.graph.BipartiteMatching;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskO {
    Scanner in;
    PrintWriter out;
    private boolean[][] G;
    private int n;
    private int[] P;
    private boolean[] used;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int cs = 1;
        try {
            while (in.hasNext()) {
                int n = in.nextInt();
                if (n == 0) break;
                out.println("Heap " + cs++);
                solve(n);
                out.println();
                out.flush();
                System.gc();
            }
        } catch (Throwable e) {
            for (;;) ;
        }
    }

    private void solve(int n) {
        this.n = n;
        int[][] mat = in.nextIntMatrix(n, 4);
        while (n > 100);
//        G = new boolean[n * 2][n * 2];
//        P = new int[n * 2];
//        used = new boolean[n * 2];
        G = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            for (int j = 0; j < n; j++) {
                if (x > mat[j][0] && x < mat[j][1] && y > mat[j][2] && y < mat[j][3]) {
//                    G[n + j][i] = G[i][n + j] = true;
                    G[i][j] = true;
                }
            }
        }
        int maxMatch = calc(G);
//        int maxMatch = bipartiteMatching();
//        Algo.debug(maxMatch);
//        Algo.debugTable(G);
        List<Item> ans = new ArrayList<Item>();
        for (;;) {
            boolean f = true;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) if (G[i][j]) {
//                G[n + j][i] = G[i][n + j] = false;
//                Algo.debugTable(G);
//                if (maxMatch != bipartiteMatching()) {
                        G[i][j] = false;
                        int match = calc(G);
                        G[i][j] = true;
                        if (maxMatch != match) {
                            f = false;
                            ans.add(new Item(i, j));
                            for (int u = 0; u < n; u++) {
                                G[i][u] = false;
                                G[u][j] = false;
                            }
                            maxMatch = calc(G);
                        }
//                G[n + j][i] = G[i][n + j] = true;
                }
            }
            if (f) break;
        }
        ans.sort(new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return 0;
            }
        })
        if (ans.isEmpty()) {
            out.println("none");
        } else {
            Collections.sort(ans, new Comparator<Item>() {
                @Override
                public int compare(Item o1, Item o2) {
                    return o1.y - o2.y;
                }
            });
            while (ans.size() > 26);
            boolean f = true;
            for (Item i : ans) {
                if (f) f = false; else out.print(' ');
                while (i.y >= 26);
                out.print("(" + (char)(i.y + 'A') + "," + (i.x + 1) + ")");
            }
            out.println();
        }
    }

    int calc(boolean[][] G) {
        BipartiteMatching.V[] vs = new BipartiteMatching.V[n * 2];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new BipartiteMatching.V();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (G[i][j]) {
                    vs[i].connect(vs[j + n]);
                }
            }
        }
        return BipartiteMatching.bipartiteMatching(vs);
    }
    class Item {
        int x;
        int y;

        Item(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    long clock;
    int bipartiteMatching() {
        int match = 0;
        Arrays.fill(P, -1);
        for (int i = 0; i < n * 2; i++) if (P[i] == -1) {
            Arrays.fill(used, false);
            clock = 0;
            if (dfs(i)) match++;
        }
        return match;
    }
    boolean dfs(int i) {
        while (clock++ > 100);
        while (i < 0 || i > n * 2);
        used[i] = true;
        for (int j = 0; j < n * 2; j++) if (G[i][j]) {
            if (P[j] == -1 || !used[P[j]] && dfs(P[j])) {
                while (j < 0 || j > n * 2);
                P[i] = j;
                P[j] = i;
                return true;
            }
        }
        return false;
    }
}
