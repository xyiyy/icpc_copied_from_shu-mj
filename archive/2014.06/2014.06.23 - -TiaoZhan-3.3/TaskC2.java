package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskC2 {
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
        Seg seg = new Seg(n);
        while (m-- != 0) {
            int a = in.nextInt() - 1, b = in.nextInt(), c = in.nextInt();
            out.println(seg.query(a, b, c));
        }
    }

    class Seg {
        int N;
        int[][] iss;
        private final int[] is;

        Seg(int n) {
            N = Integer.highestOneBit(n) << 1;
            is = in.nextIntArray(n);
            iss = new int[N * 2][];
            for (int i = 0; i < N; i++) {
                iss[i + N] = new int[1];
                if (i < n) iss[i + N][0] = is[i];
                else iss[i + N][0] = Integer.MAX_VALUE;
            }
            for (int i = N - 1; i > 0; i--) {
                iss[i] = merge(iss[i * 2], iss[i * 2 + 1]);
            }
            Arrays.sort(is);
        }

        int query(int s, int t, int v) {
            int l = 0, r = is.length;
            while (l < r) {
                int m = (l + r) / 2;
                if (count(s, t, is[m]) < v) l = m + 1;
                else r = m;
            }
            return is[l];
        }

        private int count(int s, int t, int v) {
            int res = 0;
            while (0 < s && s + (s & -s) <= t) {
                int i = (N + s) / (s & -s);
                res += Algo.upperBound(iss[i], v);
                s += s & -s;
            }
            while (s < t) {
                int i = (N + t) / (t & -t) - 1;
                res += Algo.upperBound(iss[i], v);
                t -= t & -t;
            }
            return res;
        }

        int[] merge(int[] a, int[] b) {
            int n = a.length;
            int[] res = new int[n * 2];
            for (int i = 0, j = 0, k = 0; k < n * 2; k++) {
                if (j >= n || i < n && a[i] < b[j]) res[k] = a[i++];
                else res[k] = b[j++];
            }
            return res;
        }
    }
}
