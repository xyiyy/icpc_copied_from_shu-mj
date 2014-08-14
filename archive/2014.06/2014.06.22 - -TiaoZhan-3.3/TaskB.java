package main;

import com.shu_mj.ds.LongSegSum;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;

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
        int q = in.nextInt();
//        SegLite seg = new SegLite(n + 1);
        LongSegSum seg = new LongSegSum(n + 1);
        for (int i = 1; i <= n; i++) {
//            seg.R(i, i + 1, in.nextInt());
            seg.update(i, i + 1, 0, in.nextInt());
        }
        while (q-- != 0) {
            char c = in.next().charAt(0);
            int a = in.nextInt(), b = in.nextInt() + 1;
            if (c == 'C') {
                int v = in.nextInt();
//                seg.R(a, b, v);
                seg.update(a, b, 1, v);
            } else {
//                out.println(seg.sum(a, b));
                out.println(seg.query2(a, b));
            }
        }
    }
}
