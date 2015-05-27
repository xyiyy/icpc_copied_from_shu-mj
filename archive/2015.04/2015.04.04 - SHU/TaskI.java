package main;

import com.shu_mj.math.Num;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskI {
    Scanner in;
    PrintWriter out;
    private int n;
    private long[] ls;
    private int res;
    private List<Long>[] ps;
    private boolean[][] eq;
    private long[] lls;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        Num.primeTable(1000000, new ArrayList<>());
        n = in.nextInt();
        ls = in.nextLongArray(n);
        Arrays.sort(ls);
        lls = ls.clone();
        eq = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                eq[i][j] = ls[i] == ls[j];
            }
        }
        ps = new List[n];
        for (int i = 0; i < n; i++) {
            ps[i] = Num.primeFactors(ls[i]);
        }
        pre = new int[n];
        Arrays.fill(pre, -1);
        res = Integer.MAX_VALUE;
        dfs(0);
        out.println(res);
    }

    private void dfs(int crt) {
        if (crt == n) {
            long[] cs = ls.clone();
            int cnt = 0;
            boolean[] leaf = new boolean[n];
            Arrays.fill(leaf, true);
            boolean f = false;
            for (int i = 0; i < n; i++) {
                if (pre[i] != -1) leaf[pre[i]] = false;
                if (i < n - 1 && pre[i] == -1) f = true;
                if (Num.isPrime(ls[i]) && leaf[i]) {
                    continue;
                }
                for (long p : ps[i]) {
                    if (p * p > cs[i]) break;
                    if (cs[i] % p == 0) {
                        while (cs[i] % p == 0) {
                            cs[i] /= p;
                            cnt++;
                        }
                    }
                }
                if (cs[i] != 1) cnt++;
            }
            if (f) cnt++;
//            if (cnt + n == 11) {
//                Algo.debug(ls);
//                Algo.debug(pre);
//            }
            res = Math.min(res, cnt + n);
        } else {
            for (int i = crt + 1; i < n; i++) {
                if (ls[i] % lls[crt] == 0 && !eq[crt][i]) {
                    pre[crt] = i;
                    ls[i] /= lls[crt];
                    dfs(crt + 1);
                    ls[i] *= lls[crt];
                    pre[crt] = -1;
                }
            }
            dfs(crt + 1);
        }
    }

    int[] pre;

}
