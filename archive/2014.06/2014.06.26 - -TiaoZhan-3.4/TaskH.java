package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskH {
    Scanner in;
    PrintWriter out;
    private String ansS;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            out.println("Scenario #" + i + ":");
            solve();
            System.gc();
        }
    }

    private void solve() {
        int n = in.nextInt();
        String[] ss = new String[n];
        for (int i = 0; i < n; i++) {
            ss[i] = in.next();
        }
        ss = IgnoreContains(ss);
        n = ss.length;
        Arrays.sort(ss);
        int[][] common = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                common[i][j] = getCommon(ss[i], ss[j]);
            }
        }
//        Algo.debug(n);
//        Algo.debugTable(ss);
//        Algo.debugTable(common);
        int[][] dp = new int[1 << n][n];
        final int INF = 12341234;
        Algo.fill(dp, INF);
        for (int i = 0; i < (1 << n); i++) {
            for (int j = 0; j < n; j++) if ((i & (1 << j)) != 0) {
                if (Integer.bitCount(i) == 1) {
                    dp[i][j] = ss[j].length();
                    continue;
                }
                for (int k = 0; k < n; k++) if ((i & (1 << k)) != 0 && k != j) {
                    int cost = dp[i & ~(1 << j)][k] + ss[j].length() - common[j][k];
                    if (dp[i][j] > cost) {
                        dp[i][j] = cost;
                    }
                }
            }
        }
        int len = Algo.min(dp[(1 << n) - 1]);
        ansS = "Z";
        for (int i = 0; i < n; i++) {
            if (dp[(1 << n) - 1][i] == len) {
                dfs(dp, common, ss, n, i, (1 << n) - 1, ss[i]);
            }
        }
        out.println(ansS);
        out.println();
    }

    private void dfs(int[][] dp, int[][] common, String[] ss, int n, int i, int st, String crt) {
        if (Integer.bitCount(st) == 1) {
            if (ansS.compareTo(crt) > 0) {
                ansS = crt;
                return ;
            }
        }
        for (int j = 0; j < n; j++) if ((st & (1 << j)) != 0 && j != i) {
            if (dp[st][i] == dp[st & ~(1 << i)][j] + ss[i].length() - common[i][j]) {
                String nextS = crt + ss[j].substring(common[i][j]);
                if (nextS.compareTo(ansS) > 0) continue;
                dfs(dp, common, ss, n, j, st & ~(1 << i), nextS);
            }
        }
    }

    private int getCommon(String s, String t) {
        for (int i = Math.min(t.length(), s.length()); i > 0; i--) {
            if (s.substring(s.length() - i).equals(t.substring(0, i))) return i;
        }
        return 0;
    }

    private String[] IgnoreContains(String[] ss) {
        Arrays.sort(ss, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int l = o1.length() - o2.length();
                if (l != 0) return -l;
                return o1.compareTo(o2);
            }
        });
        List<String> ls = new ArrayList<String>();
        outer: for (String s : ss) {
            for (String t : ls) {
                if (t.contains(s)) continue outer;
            }
            ls.add(s);
        }
        return ls.toArray(new String[0]);
    }


}
