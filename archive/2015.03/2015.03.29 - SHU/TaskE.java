package main;

import com.shu_mj.ds.BIT;
import com.shu_mj.ds.SegMaxC;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskE {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        int[] is = in.nextIntArray(n);
        SegMaxC seg = new SegMaxC(n + 1);
        seg.update(0, 0, 0);
        for (int i = 0; i < n; i++) {
            seg.update(is[i], 0, seg.query(0, is[i]) + 1);
        }
        out.println(seg.query(0, n + 1));
    }
}
