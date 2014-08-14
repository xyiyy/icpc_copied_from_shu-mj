package main;

import com.shu_mj.ds.Intervals;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskF {
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
        Intervals<Integer, Boolean> Int = new Intervals<Integer, Boolean>(Integer.MIN_VALUE, Integer.MAX_VALUE, false);
        for (int i = 0; i < n; i++) {
            boolean f = in.next().charAt(0) == 'F';
            int x = in.nextInt();
            if (Int.get(x)) {
                if (f) {
                    Map.Entry<Integer, Boolean> e = Int.map.floorEntry(x);
                    x = e.getKey();
                    out.println(x - 1);
                    Int.paint(x - 1, x, true);
                } else {
                    Map.Entry<Integer, Boolean> e = Int.map.higherEntry(x);
                    x = e.getKey();
                    out.println(x);
                    Int.paint(x, x + 1, true);
                }
            } else {
                out.println(x);
                Int.paint(x, x + 1, true);
            }
        }
    }
}
