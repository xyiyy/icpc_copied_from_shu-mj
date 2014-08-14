package main;

import com.shu_mj.graph.BipartiteMatching;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskB {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        while (t-- != 0) {
            solve();
        }
    }

    private void solve() {
        int r = in.nextInt();
        int c = in.nextInt();
        char[][] maps = new char[r][];
        for (int i = 0; i < r; i++) {
            maps[i] = in.next().toCharArray();
        }
        int[][][][] dis = new int[r][c][r][c];
        List<P> doors = new ArrayList<P>();
        List<P> persons = new ArrayList<P>();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (maps[i][j] == '.') {
                    persons.add(new P(i, j));
                } else if (maps[i][j] == 'D') {
                    doors.add(new P(i, j));
                    bfs(i, j, maps, dis[i][j]);
                }
            }
        }
        for (int i = 0; i < r; i++) {
            outer: for (int j = 0; j < c; j++) {
                if (maps[i][j] == '.') {
                    for (P p : doors) {
                        if (dis[p.x][p.y][i][j] != -1) continue outer;
                    }
                    out.println("impossible");
                    return ;
                }
            }
        }
        int L = 0, R = r * c;
        while (L < R) {
            int M = (L + R) / 2;
            if (fit(M, dis, doors, persons)) R = M;
            else L = M + 1;
        }
        out.println(L);
    }

    private boolean fit(int m, int[][][][] dis, List<P> doors, List<P> persons) {
        int d = doors.size();
        int p = persons.size();
        V[] vs = new V[m * d + p];
        // 0 ~ m := door 1
        // m ~ 2 * m := door 2
        // ...
        // m * d ~ m * d + p := person
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new V();
        }
        for (int i = 0; i < d; i++) {
            for (int j = 0; j < p; j++) {
                int dx = doors.get(i).x, dy = doors.get(i).y;
                int px = persons.get(j).x, py = persons.get(j).y;
                for (int k = dis[dx][dy][px][py] - 1; k >= 0 && k < m; k++) {
//                    Algo.debug(dx, dy, px, py, k);
                    vs[i * m + k].connect(vs[d * m + j]);
                }
            }
        }
        return BipartiteMatching.bipartiteMatching(vs) == p;
    }

    class V extends BipartiteMatching.V {

    }

    private void bfs(int sx, int sy, char[][] maps, int[][] dis) {
        Queue<P> que = new LinkedList<P>();
        que.add(new P(sx, sy));
        int n = maps.length;
        int m = maps[0].length;
        Algo.fill(dis, -1);
        dis[sx][sy] = 0;
        while (!que.isEmpty()) {
            P crt = que.poll();
            for (int i = 0; i < 4; i++) {
                int x = crt.x + dx[i];
                int y = crt.y + dy[i];
                if (in(n, m, x, y) && maps[x][y] == '.' && dis[x][y] == -1) {
                    dis[x][y] = dis[crt.x][crt.y] + 1;
                    que.add(new P(x, y));
                }
            }
        }
    }

    private boolean in(int n, int m, int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    final int[] dx = {0, 0, 1, -1};
    final int[] dy = {1, -1, 0, 0};

    class P {
        int x;
        int y;

        P(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "P{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

}
