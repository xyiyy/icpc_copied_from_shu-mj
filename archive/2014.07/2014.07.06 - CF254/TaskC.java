package main;

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
        int[] is = new int[n];
        for (int i = 0; i < n; i++) {
            is[i] = i + 1;
        }
        int[] ss = new int[n];
        for (int i = 0; i < m; i++) {
            int t = in.nextInt();
            int l = in.nextInt() - 1;
            int r = in.nextInt();
            if (t == 1) {
                int c = in.nextInt();
                for (int j = l; j < r; j++) {
                    ss[j] += Math.abs(is[j] - c);
                    is[j] = c;
                }
            } else {
                int s = 0;
                for (int j = l; j < r; j++) {
                    s += ss[j];
                }
                out.println(s);
            }
        }
    }
}
