package main;

import com.shu_mj.ds.MatSum;
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
        while (t-- != 0) {
            solve();
            if (t != 0) out.println();
        }
    }

    private void solve() {
        int n = in.nextInt();
        int q = in.nextInt();
        MatSum mat = new MatSum(n, n);
        while (q-- != 0) {
            if (in.next().charAt(0) == 'C') {
                int x0 = in.nextInt() - 1, y0 = in.nextInt() - 1;
                int x1 = in.nextInt(), y1 = in.nextInt();
                mat.add(x0, y0, 1);
                mat.add(x0, y1, -1);
                mat.add(x1, y0, -1);
                mat.add(x1, y1, 1);
            } else {
                int x = in.nextInt() - 1, y = in.nextInt() - 1;
                out.println(mat.sum(0, 0, x + 1, y + 1) % 2);
            }
        }
    }
}
