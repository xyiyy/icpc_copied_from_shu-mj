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
        run();
    }

    void run() {
        int n = in.nextInt();
        int m = in.nextInt();
        long k = in.nextLong();
        long l = 1, r = (long) n * m + 1;
        while (l < r) {
            long mid = (l + r) / 2;
            if (calc(n, m, mid) >= k) r = mid;
            else l = mid + 1;
        }
        out.println(l);
    }
    long calc(int n, int m, long x) {
        long res = 0;
        for (int i = 1; i <= n; i++) {
            res += lowerBound(i, m, x);
        }
        return res;
    }
    int lowerBound(int begin, int max, long x) {
        return (int) Math.min(max, Math.max(0, x / begin));
    }
}
