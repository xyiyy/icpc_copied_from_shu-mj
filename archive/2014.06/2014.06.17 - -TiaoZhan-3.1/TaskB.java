package main;

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
        int c = in.nextInt();
        int[] is = in.nextIntArray(n);
        Arrays.sort(is);
        int l = 0, r = Integer.MAX_VALUE / 2;
        while (l < r) {
            int m = (l + r) / 2;
            if (fit(is, m, c)) l = m + 1;
            else r = m;
        }
        l--;
        out.println(l);
    }

    private boolean fit(int[] is, int d, int c) {
        int last = is[0], cnt = 1;
        for (int i = 1; i < is.length; i++) {
            if (is[i] - last >= d) {
                cnt++;
                last = is[i];
                if (cnt >= c) return true;
            }
        }
        return false;
    }
}
