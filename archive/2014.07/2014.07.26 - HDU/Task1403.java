package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task1403 {
    Scanner in;
    PrintWriter out;
    private Set<Integer> vis;
    private List<Character> path;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        while (in.hasNext()) {
            solve();
        }
    }

    private void solve() {
        int begin = 0;
        for (int i = 0; i < 9; i++) {
            String d = in.next();
            if (d.charAt(0) == 'x') d = "9";
            begin = begin * 10 + Integer.parseInt(d);
        }
        path = new ArrayList<Character>();
        vis = new HashSet<Integer>();
        if (!dfs(begin)) out.println("unsolvable");
    }
    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};
    char[] ds = "rldu".toCharArray();
    boolean dfs(int crt) {
        vis.add(crt);
        if (crt == 123456789) {
            for (char c : path) out.print(c);
            out.println();
            return true;
        }
        int[][] css = decode(crt);
        int x = -1, y = -1;
        for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) {
            if (css[i][j] == 9) {x = i; y = j;}
        }
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (in(nx, ny)) {
                {
                    int t = css[x][y]; css[x][y] = css[nx][ny]; css[nx][ny] = t;
                }
                int nextCode = encode(css);
                if (!vis.contains(nextCode)) {
                    path.add(ds[i]);
                    if (dfs(nextCode)) return true;
                    path.remove(path.size() - 1);
                }
                {
                    int t = css[x][y]; css[x][y] = css[nx][ny]; css[nx][ny] = t;
                }
            }
        }
        return false;
    }

    private int same(int i, int x, int y) {
        return i == x * 3 + y + 1 ? 1 : 0;
    }

    class Item implements Comparable<Item> {
        int code;
        int same;
        int dir;

        Item(int code, int same, int dir) {
            this.code = code;
            this.same = same;
            this.dir = dir;
        }

        @Override
        public int compareTo(Item o) {
            return o.same - same;
        }
    }
    private int encode(int[][] iss) {
        int crt = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                crt = crt * 10 + iss[i][j];
            }
        }
        return crt;
    }

    private boolean in(int x, int y) {
        return x >= 0 && x < 3 && y >= 0 && y < 3;
    }

    private int[][] decode(int crt) {
        int[][] iss = new int[3][3];
        for (int i = 2; i >= 0; i--) {
            for (int j = 2; j >= 0; j--) {
                iss[i][j] =  crt % 10;
                crt /= 10;
            }
        }
        return iss;
    }
}
