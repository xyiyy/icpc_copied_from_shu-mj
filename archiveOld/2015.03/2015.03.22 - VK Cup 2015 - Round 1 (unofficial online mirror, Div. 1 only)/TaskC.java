package main;

import com.shu_mj.ds.SegMaxC;
import com.shu_mj.ds.SegMinC;
import com.shu_mj.tpl.PII;
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
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int q = in.nextInt();
        int[] xs = new int[k];
        int[] ys = new int[k];
        int[] x0 = new int[q];
        int[] y0 = new int[q];
        int[] x1 = new int[q];
        int[] y1 = new int[q];
        in.nextIntArray(k, xs, ys);
        in.nextIntArray(q, x0, y0, x1, y1);
        for (int i = 0; i < k; i++) {
            xs[i]--;
            ys[i]--;
        }
        for (int i = 0; i < q; i++) {
            x0[i]--;
            y0[i]--;
        }
        boolean[] ans = new boolean[q];
        go(n, m, k, q, xs, ys, x0, y0, x1, y1, ans);
        go(m, n, k, q, ys, xs, y0, x0, y1, x1, ans);
        for (boolean b : ans) out.println(b ? "YES" : "NO");
    }

    private void go(int n, int m, int k, int q, int[] xs, int[] ys, int[] x0, int[] y0, int[] x1, int[] y1, boolean[] ans) {
        List<Integer>[] ps = new List[n];
        for (int i = 0; i < n; i++) {
            ps[i] = new ArrayList<>();
        }
        List<Integer>[] qs = new List[n];
        for (int i = 0; i < n; i++) {
            qs[i] = new ArrayList<>();
        }
        for (int i = 0; i < k; i++) {
            ps[xs[i]].add(ys[i]);
        }
        for (int i = 0; i < q; i++) {
            qs[x1[i] - 1].add(i);
        }
        SegMinC seg = new SegMinC(m);
        Arrays.fill(seg.is, Integer.MIN_VALUE);
        for (int x = 0; x < n; x++) {
            for (int y : ps[x]) {
                seg.update(y, 0, x);
            }
            for (int i : qs[x]) {
                ans[i] |= seg.query(y0[i], y1[i]) >= x0[i];
            }
        }
    }
}
