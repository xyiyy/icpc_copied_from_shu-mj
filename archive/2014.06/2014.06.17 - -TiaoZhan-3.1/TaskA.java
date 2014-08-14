package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskA {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt(), k = in.nextInt();
        int[] is = new int[n];
        for (int i = 0; i < n; i++) {
            String s = in.next();
            int p = s.indexOf('.');
            is[i] = Integer.parseInt(s.substring(0, p)) * 100 + Integer.parseInt(s.substring(p + 1));
        }
        int l = 1, r = Integer.MAX_VALUE / 2;
        while (l < r) {
            int m = (l + r) / 2;
            if (calc(is, m) < k) r = m;
            else l = m + 1;
        }
        l--;
        out.println((l / 100) + "." + String.format("%02d", l % 100));
    }

    private int calc(int[] is, int m) {
        int res = 0;
        for (int i = 0; i < is.length; i++) {
            int l = is[i];
            res += l / m;
        }
        return res;
    }
}
