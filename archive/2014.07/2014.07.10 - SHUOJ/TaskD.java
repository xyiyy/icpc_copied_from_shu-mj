package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskD {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int t = in.nextInt();
        for (int i = 1; i <= t; i++) {
            out.printf("Case %d:%n", i);
            solve();
        }
    }

    int[] dys = {
            0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };
    private void solve() {
        int n = in.nextInt();
        int[] ds = new int[n];
        boolean[] bs = new boolean[n];
        for (int i = 0; i < n; i++) {
            ds[i] = in.nextInt();
            bs[i] = in.next().charAt(0) == 'B';
        }
        int cnt = 0;
        int date = 0;
        for (int i = 1; i <= 12; i++) {
            for (int j = 1; j <= dys[i]; j++) {
                int co = i * 100 + j;
                if (fit(co, ds, bs)) {
                    cnt++;
                    date = co;
                }
            }
        }
        if (cnt == 1) out.printf("%04d%n", date);
        else if (cnt == 0) out.println("Impossible");
        else out.println(cnt);
    }

    private boolean fit(int date, int[] ds, boolean[] bs) {
        for (int i = 0; i < ds.length; i++) {
            if (!(bs[i] && date < ds[i] || !bs[i] && date > ds[i]))
                return false;
        }
        return true;
    }
}
