package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskF {
    Scanner in;
    PrintWriter out;
    private int[] ans;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        init();
        while (in.hasNext()) {
            int h = in.nextInt();
            if (h == 0) break;
            out.println(h + " " + ans[h]);
        }
    }

    private void init() {
        final int MAX_H = 1000000 + 1;
        int[] pri = new int[MAX_H + 1];
        for (int i = 5; i <= MAX_H; i += 4) {
            if (pri[i] == 0)
                for (int j = 5; (long) j * i <= MAX_H; j += 4) {
                if (pri[j] == 0) {
                    pri[j * i] = 2;
                } else {
                    pri[j * i] = 3;
                }
            }
        }
        ans = new int[MAX_H + 1];
        for (int i = 21; i <= MAX_H; i++) {
            ans[i] = ans[i - 1] + (pri[i] == 2 ? 1 : 0);
        }
    }
}
