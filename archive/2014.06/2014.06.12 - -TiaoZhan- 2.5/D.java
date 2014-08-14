package main;

import com.shu_mj.tpl.Algo;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;

public class D {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        int n = in.nextInt(), m = in.nextInt();
        int[][] map = new int[n][n];
        final int INF = Integer.MAX_VALUE / 4;
        Algo.fill(map, INF);
        for (int i = 0; i < n; i++) {
            map[i][i] = 0;
        }
        for (int i = 0; i < m; i++) {
            int t = in.nextInt();
            int[] is = in.nextIntArray(t);
            for (int ui = 0; ui < is.length; ui++) {
                int u = is[ui];
                for (int vi = 0; vi < is.length; vi++) {
                    int v = is[vi];
                    map[u - 1][v - 1] = Math.min(map[u - 1][v - 1], 1);
                }
            }
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }
        int[] cnt = new int[n];
        for (int i = 0; i < n; i++) {
            cnt[i] = (int) Algo.sum(map[i]);
        }
        out.println(Algo.min(cnt) * 100 / (n - 1));
    }
}
