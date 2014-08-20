package main;

import com.shu_mj.tpl.Algo;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task1004 {
    Scanner in;
    PrintWriter out;
    private char[] cs;
    private int[] is;
    private int[] s;
    private int n;
    private int[] t;
    private int sa;
    private int sb;
    private int ta;
    private int tb;
    private final int INF = Integer.MAX_VALUE / 4;
    private int a;
    private int b;
    private int res;
    private int ss;
    private int st;
    private int[][] min;
    private int[][] max;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        while (in.hasNext()) {
            int n = in.nextInt();
            if (n == 0) break;
            solve(n);
        }
    }

    private void solve(int n) {
        this.n = n;
        cs = in.next().toCharArray();
        is = in.nextIntArray(n * 2);
        s = new int[n];
        t = new int[n];

        a = 0;
        for (char c : cs) if (c == 'a') a++;
        b = n * 2 - a;
        if (a % 2 != 0) {
            out.println(-1);
            return ;
        }
        sa = sb = ss = st = 0;
        res = INF;
        min = new int[n * 2][n + 1];
        max = new int[n * 2][n + 1];
        for (int i = 0; i < n * 2; i++) {
            for (int j = 0; j <= n && i + j <= n * 2; j++) {
                min[i][j] = min(is, i, j);
                max[i][j] = max(is, i, j);
            }
        }
        dfs(0, 0, 0);
        if (res >= INF) out.println(-1);
        else out.println(res);
    }

    private int max(int[] is, int i, int j) {
        List<Integer> list = new ArrayList<Integer>();
        for (int u = i; u < n * 2; u++) {
            list.add(is[i]);
        }
        Collections.sort(list);
        int sum = 0;
        for (int u = 0; u < j; u++) {
            sum += list.get(list.size() - u - 1);
        }
        return sum;
    }

    private int min(int[] is, int i, int j) {
        List<Integer> list = new ArrayList<Integer>();
        for (int u = i; u < n * 2; u++) {
            list.add(is[i]);
        }
        Collections.sort(list);
        int sum = 0;
        for (int u = 0; u < j; u++) {
            sum += list.get(u);
        }
        return sum;
    }

    private void dfs(int crt, int si, int ti) {
        if (crt == n * 2) {
            res = Math.min(res, Math.abs(ss - st));
            return ;
        }
        if (sa > a >> 1 || sb > b >> 1) return ;
        if (ta > a >> 1 || tb > b >> 1) return ;
        if (!check(crt, si, ti, ss, st)) return ;
        if (!check(crt, ti, si, st, ss)) return ;
        if (si < n && (ti <= si || cs[crt] == cs[t[si]])) {
            addS(cs[crt], 1);
            ss += is[crt];
            s[si] = crt;
            dfs(crt + 1, si + 1, ti);
            ss -= is[crt];
            addS(cs[crt], -1);
        }
        if (si != 0 && ti < n && (si <= ti || cs[crt] == cs[s[ti]])) {
            addT(cs[crt], 1);
            st += is[crt];
            t[ti] = crt;
            dfs(crt + 1, si, ti + 1);
            st -= is[crt];
            addT(cs[crt], -1);
        }
    }

    private boolean check(int crt, int si, int ti, int ss, int st) {
        return ss > st || (st + min[crt][n - ti] - (ss + max[crt][n - si]) < res);
    }

    private void addT(char c, int i) {
        if (c == 'a') ta += i;
        else tb += i;
    }

    private void addS(char c, int i) {
        if (c == 'a') sa += i;
        else sb += i;
    }

}
