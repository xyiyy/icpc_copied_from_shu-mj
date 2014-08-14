package main;

import com.shu_mj.ds.Intervals;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class Task1769 {
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

    private void solve() {
        int n = in.nextInt();
        Intervals<Integer, Boolean> Int = new Intervals<>(Integer.MIN_VALUE, Integer.MAX_VALUE, false);
        for (int i = 0; i < n; i++) {
            boolean f = in.next().charAt(0) == 'F';
            int x = in.nextInt();
            boolean b = Int.get(x);
            if (f) {
                if (b) {
                    Map.Entry<Integer, Boolean> e = Int.map.floorEntry(x);
                    out.println(e.getKey() - 1);
                    Int.paint(e.getKey() - 1, e.getKey(), true);
                } else {
                    Int.paint(x, x + 1, true);
                    out.println(x);
                }
            } else {
                if (b) {
                    Map.Entry<Integer, Boolean> e = Int.map.higherEntry(x);
                    out.println(e.getKey());
                    Int.paint(e.getKey(), e.getKey() + 1, true);
                } else {
                    Int.paint(x, x + 1, true);
                    out.println(x);
                }
            }
        }
    }
}
