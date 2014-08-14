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
        while (in.hasNext())
            run();
    }

    void run() {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] is = in.nextIntArray(n);
        int l = 0, r = Integer.MAX_VALUE / 2;
        while (l < r) {
            int mid = (l + r) / 2;
            if (fit(is, mid, m)) r = mid;
            else l = mid + 1;
        }
        out.println(l);
    }

    private boolean fit(int[] is, int mid, int m) {
        int sum = 0, cnt = 1;
        for (int i = 0; i < is.length; i++) {
            int c = is[i];
            if (c > mid) return false;
            if (c + sum > mid) {
                cnt++;
                sum = c;
            } else {
                sum += c;
            }
        }
        return cnt <= m;
    }
}
