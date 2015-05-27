package main;
import java.util.*;

import com.shu_mj.graph.SCC;
import com.shu_mj.tpl.Algo;

public class TheTips {
    public double solve(String[] clues, int[] probability) {
        int n = clues.length;
        boolean[][] g = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                g[i][j] = clues[i].charAt(j) == 'Y' || i == j;
            }
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    g[i][j] |= g[i][k] & g[k][j];
                }
            }
        }
        double res = 0;
        for (int i = 0; i < n; i++) {
            double p = 1;
            for (int j = 0; j < n; j++) {
                if (g[j][i]) {
                    p *= 1 - probability[j] / 100.;
                }
            }
            res += 1 - p;
        }
        return res;
    }
    public double solve2(String[] clues, int[] probability) {
        int n = clues.length;
        SCC.V[] vs = new SCC.V[n];
        for (int i = 0; i < vs.length; i++) {
            vs[i] = new SCC.V();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) if (i != j) {
                if (clues[i].charAt(j) == 'Y') {
                    vs[i].add(vs[j]);
                }
            }
        }
        int k = SCC.scc(vs);
        double[] ps = new double[k];
        Arrays.fill(ps, 1.);
        int[] cnt = new int[k];
        for (int i = 0; i < n; i++) {
            ps[vs[i].comp] *= 1 - probability[i] / 100.;
            cnt[vs[i].comp]++;
        }
        boolean[][] g = new boolean[k][k];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (clues[i].charAt(j) == 'Y') {
                    g[vs[i].comp][vs[j].comp] = true;
                }
            }
        }
        for (int i = 0; i < k; i++) g[i][i] = false;
        int[] ind = new int[k];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                if (g[i][j]) {
                    ind[j]++;
                }
            }
        }
        double res = 0;
        Queue<Integer> que = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            if (ind[i] == 0) que.add(i);
        }
        while (!que.isEmpty()) {
            int crt = que.poll();
            for (int i = 0; i < k; i++) if (i != crt) {
                if (g[i][crt]) {
                    ps[crt] *= ps[i];
                }
                if (g[crt][i]) {
                    ind[i]--;
                    if (ind[i] == 0) {
                        que.add(i);
                    }
                }
            }
        }
        for (int i = 0; i < k; i++) {
            res += cnt[i] * (1 - ps[i]);
        }
        return res;
    }
}
