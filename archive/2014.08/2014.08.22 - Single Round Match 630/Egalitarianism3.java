package main;
import java.util.*;

import com.shu_mj.math.Num;
import com.shu_mj.tpl.Algo;

public class Egalitarianism3 {
    final int INF = (int) 1e9;
    public int maxCities(int n, int[] a, int[] b, int[] len) {
        if (n == 1) return 1;
        int[][] dis = new int[n][n];
        Algo.fill(dis, INF);
        for (int i = 0; i < n; i++) dis[i][i] = 0;
        V[] vs = new V[n];
        for (int i = 0; i < n; i++) vs[i] = new V();
        for (int i = 0; i < a.length; i++) {
            int u = a[i] - 1;
            int v = b[i] - 1;
            dis[v][u] = dis[u][v] = len[i];
            vs[u].add(v);
            vs[v].add(u);
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dis[i][j] = Math.min(dis[i][j], dis[i][k] + dis[k][j]);
                }
            }
        }
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) if (i != j) set.add(dis[i][j]);
        int res = 2;
        for (int i = 0; i < n; i++) {
            for (int d : set) {
                int tmp = 0;
                for (int j : vs[i]) {
                    tmp += dfs(j, i, i, vs, dis, d);
                }
                res = Math.max(res, tmp);
            }
        }
        return res;
        /*int[][] dp = new int[n][n];
        int res = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                boolean[] vis = new boolean[n];
                vis[i] = true;
                res = Math.max(res, 1 + dfs(dis, dp, vis, i, j));
            }
        }
        return res;*/
    }

    private int dfs(int v, int fa, int root, V[] vs, int[][] dis, int d) {
        if (dis[root][v] == d) return 1;
        int res = 0;
        for (int u : vs[v]) if (u != fa) {
            res += dfs(u, v, root, vs, dis, d);
        }
        return Math.min(1, res);
    }

    class V extends ArrayList<Integer> {

    }

    private int dfs(int[][] dis, int[][] dp, boolean[] vis, int i, int j) {
        if (dp[i][j] != 0) return dp[i][j];
        int res = 1;
        vis[j] = true;
        int n = dis.length;
        for (int u = 0; u < n; u++) if (!vis[u]) {
            if (dis[j][u] == dis[i][j]) {
                res += dfs(dis, dp, vis, j, u);
            }
        }
        return dp[j][i] = dp[i][j] = res;
    }
}
