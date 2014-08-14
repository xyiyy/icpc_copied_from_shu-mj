package main;

import com.shu_mj.ds.LongSegMaxC;
import com.shu_mj.ds.SegMaxC;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskB {
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
        int p = in.nextInt();
        int[][] mat = in.nextIntMatrix(n, m);
        int[] rows = new int[n];
        int[] cols = new int[m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rows[i] += mat[i][j];
                cols[j] += mat[i][j];
            }
        }
        LongSegMaxC row = new LongSegMaxC(n);
        LongSegMaxC col = new LongSegMaxC(m);
        for (int i = 0; i < n; i++) {
            row.update(i, 0, rows[i]);
        }
        for (int j = 0; j < m; j++) {
            col.update(j, 0, cols[j]);
        }
        long rowDelta = 0, colDelta = 0;
        long ans = 0;
        while (k-- != 0) {
            int rid = row.id[1];
            int cid = row.id[1];
            long r = row.is[1] + rowDelta;
            long c = col.is[1] + colDelta;

//            Algo.debug(rid, cid, r, c, row.is[1], col.is[1]);
            if (r > c) {
                ans += r;
                colDelta -= p;
                row.update(rid, 1, -p * m);
            } else {
                ans += c;
                rowDelta -= p;
                col.update(cid, 1, -p * n);
            }
        }
        out.println(ans);
    }
}
