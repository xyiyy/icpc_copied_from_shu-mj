package main;

import com.shu_mj.ds.MatSum;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;

public class TaskL {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        while (in.hasNext()) {
            solve();
        }
    }

    private void solve() {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            y[i] = in.nextInt() - 1;
            x[y[i]] = i;
        }
        int[] del = new int[m];
        boolean[] removed = new boolean[n];
        for (int i = 0; i < m; i++) {
            del[i] = in.nextInt() - 1;
            removed[del[i]] = true;
        }
        Mat mat = new Mat(n);
        long res = 0;
        for (int i = 0; i < n; i++) {
            if (!removed[i]) {
                mat.add(x[i], i);
                res += mat.query(x[i], i);
            }
        }
        long[] ans = new long[m];
        for (int i = m - 1; i >= 0; i--) {
            mat.add(x[del[i]], del[i]);
            res += mat.query(x[del[i]], del[i]);
            ans[i] = res;
        }
        for (int i = 0; i < m; i++) {
            out.println(ans[i]);
        }
    }

    class Mat {
        final int N;
        final int B = 450;
        final int BS;
        int[] X;
        int[] Y;
        MatSum cnt;
        Mat(int n) {
            N = n;
            X = new int[n];
            Y = new int[n];
            BS = (n + B - 1) / B;
            cnt = new MatSum(BS, BS);
            Arrays.fill(X, -1);
            Arrays.fill(Y, -1);
        }

        void add(int x, int y) {
            Y[x] = y;
            X[y] = x;
            cnt.add(x / B, y / B, 1);
        }

        int query(int x, int y) {
            return sum(x, N) + sum(N, y) - 2 * sum(x, y);
        }

        int sum(int x, int y) {
            int w = x / B;
            int h = y / B;
            int res = cnt.sum(0, 0, w, h);
            for (int i = w * B; i < x; i++) {
                if (Y[i] != -1 && Y[i] < h * B) res++;
            }
            for (int i = h * B; i < y; i++) {
                if (X[i] != -1 && X[i] < x) res++;
            }
            return res;
        }
    }
}
