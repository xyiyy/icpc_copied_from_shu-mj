package main;

import com.shu_mj.tpl.PII;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskD {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int m = in.nextInt();
        char[][] maps = in.nextCharMap(n);
        Queue<Integer> que = new LinkedList<>();
        boolean[][] vis = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (check(maps, i, j)) {
                    que.add(i * m + j);
                    vis[i][j] = true;
                }
            }
        }
        while (!que.isEmpty()) {
            int x = que.peek() / m;
            int y = que.poll() % m;
            maps[x][y] = '.';
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) if (i != 0 || j != 0) {
                    if (check(maps, x + i, y + j) && !vis[x + i][y + j]) {
                        que.add((x + i) * m + y + j);
                        vis[x + i][y + j] = true;
                    }
                }
            }
        }
        for (char[] cs : maps) out.println(cs);
    }

    private boolean check(char[][] maps, int i, int j) {
        if (i < 0 || j < 0 || i >= maps.length || j >= maps[0].length) return false;
        if (maps[i][j] == '.') return false;
        for (int x = i - 1; x <= i + 1; x += 2) {
            for (int y = j - 1; y <= j + 1; y += 2) {
                if (x >= 0 && y >= 0 && x < maps.length && y < maps[0].length) {
                    if (maps[x][y] == '.' && maps[x][j] == '.' && maps[i][y] == '.') return true;
                }
            }
        }
        return false;
    }
}
