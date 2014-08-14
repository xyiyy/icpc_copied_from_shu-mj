package test.on2014_08.on2014_08_12_Single_Round_Match_629.CandyCollection;


import java.util.*;

import com.shu_mj.graph.MinCostFlow;
import com.shu_mj.tpl.Algo;

public class CandyCollection {
    public int solve(int[] Type1, int[] Number1, int[] Type2, int[] Number2) {
        int n = Type1.length;

        V[] vs = new V[n];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new V();
        }
        boolean[] vis = new boolean[n];
        int res = 0;
        for (int i = 0; i < n; i++) {
            int u = Type1[i];
            int v = Type2[i];
            int c = Math.max(Number1[i], Number2[i]) + 1;
            vs[u].add(new E(c, v));
            vs[v].add(new E(c, u));
        }
        for (int i = 0; i < n; i++) if (!vis[i]) {
            List<Integer> list = new ArrayList<Integer>();
            int crt = i;
            outer: for (;;) {
                vis[crt] = true;
                System.err.print(crt + ", ");
                for (E e : vs[crt]) if (!vis[e.to]) {
                    list.add(e.cost);
                    crt = e.to;
                    continue outer;
                }
                break;
            }
            System.err.println();
            list.add(vs[i].get(1).cost);
            res += calc(Algo.unBox(list.toArray(new Integer[0])));
        }
        return res;
    }

    final int INF = Integer.MAX_VALUE / 4;
    private int calc(int[] is) {
        Algo.debug(is);
        int n = is.length;
        if (n <= 2) return Algo.min(is);
        if (n == 3) {
            return Math.min(Math.min(is[0] + is[1], is[0] + is[2]), is[1] + is[2]);
        }
        int res = work(is);
        for (int i = 1; i < n; i++) {
            res = Math.min(res, work(Algo.merge(Arrays.copyOfRange(is, n - i, n), Arrays.copyOf(is, n - i))));
        }
        return res;
    }

    private int work(int[] is) {
        Algo.debug(is);
        int n = is.length;
        int[] dp = new int[n];
        Arrays.fill(dp, INF);
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                dp[i] = is[0];
            } else if (i == 1) {
                dp[i] = is[0];
//            } else if (i == n - 1) {
//                dp[i] = Math.min(dp[i], dp[i - 1] + is[i]);
            } else {
                dp[i] = Math.min(dp[i - 2] + is[i - 1], dp[i - 1] + is[i]);
            }
//            Algo.debug(i, dp);
        }
        return dp[n - 1];
    }


    class E {
        int cost;
        int to;

        E(int cost, int to) {
            this.cost = cost;
            this.to = to;
        }
    }
    class V extends ArrayList<E> {

    }
}
