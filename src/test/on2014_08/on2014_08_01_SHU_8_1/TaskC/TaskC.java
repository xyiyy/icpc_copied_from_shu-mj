package test.on2014_08.on2014_08_01_SHU_8_1.TaskC;



import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskC {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        while (t-- != 0)
            solve();
    }

    private void solve() {
        int n = in.nextInt();
        int m = in.nextInt();
        V[] vs = new V[n * 2];
        for (int i = 0; i < vs.length; i++) vs[i] = new V();
        for (int i = 0; i < m; i++) {
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            vs[u].add(v + n);
            vs[v + n].add(u);
        }
        List<Integer> ls = new ArrayList<Integer>();
        List<Integer> rs = new ArrayList<Integer>();
        for (int i = 0; i < n * 2; i++) if (!vs[i].vis) {
            left = right = 0;
            dfs(vs, i);
            ls.add(left);
            rs.add(right);
        }
        boolean[][] dp = new boolean[n / 2 + 1][n / 2 + 1];
        dp[0][0] = true;
        for (int i = 0; i < ls.size(); i++) {
            int l = ls.get(i);
            int r = rs.get(i);
            for (int j = n / 2; j >= 0; j--) {
                for (int k = n / 2; k >= 0; k--) {
                    dp[j][k] |= j - l >= 0 && k - r >= 0 && dp[j - l][k - r];
                }
            }
        }
        int ans = 0;
        for (int i = 0; i <= n / 2; i++) if (dp[i][i]) ans = i;
        out.println(ans);
    }

    private void dfs(V[] vs, int c) {
        int n = vs.length / 2;
        vs[c].vis = true;
        if (c < n) left++; else right++;
        for (int u : vs[c]) if (!vs[u].vis) dfs(vs, u);
    }

    class V extends ArrayList<Integer> {
        boolean vis;
    }
    int left;
    int right;
}
