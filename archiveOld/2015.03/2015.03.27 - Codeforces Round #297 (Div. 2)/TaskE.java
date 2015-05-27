package main;

import com.shu_mj.math.Num;
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
        int k = in.nextInt();
        long s = in.nextLong();
        int[] is = in.nextIntArray(n);
        List<Integer> ls = Arrays.asList(Algo.box(is));
        Collections.shuffle(ls);
        is = Algo.unBox(ls.toArray(new Integer[0]));
        long[] fs = new long[n];
        boolean[] bs = new boolean[n];
        for (int i = 0; i < n; i++) {
            bs[i] = is[i] < 19;
            if (bs[i]) fs[i] = Num.factorial(is[i]);
        }
        long S = s + 1;
        Map<Long, Integer> cnt = new HashMap<>();
        cnt.put(0L, 1);
        int m = 12;
        if (n >= m) {
            int t = (int) Num.pow(3, m);
            outer: for (int x = 1; x < t; x++) {
                int y = x;
                long sum = 0;
                int c = 0;
                for (int i = 0; i < m; i++) {
                    int v = y % 3;
                    y /= 3;
                    if (v == 2 && !bs[i]) continue outer;
                    if (v == 1) sum += is[i];
                    else if (v == 2) {
                        sum += fs[i];
                        c++;
                    }
                    if (sum > s || c > k) continue outer;
                }
                Num.inc(cnt, c * S + sum);
            }
            for (int i = m; i < n; i++) {
                is[i - m] = is[i];
                fs[i - m] = fs[i];
                bs[i - m] = bs[i];
            }
            n -= m;
        }
        long res = 0;
        for (int i = 0; i <= k; i++) res += cnt.containsKey(i * S + s) ? cnt.get(i * S + s) : 0;
        int t = (int) Num.pow(3, n);
        outer: for (int x = 1; x < t; x++) {
            int y = x;
            long sum = 0;
            int c = 0;
            for (int i = 0; i < n; i++) {
                int v = y % 3;
                y /= 3;
                if (v == 2 && !bs[i]) continue outer;
                if (v == 1) sum += is[i];
                else if (v == 2) {
                    sum += fs[i];
                    c++;
                }
                if (sum > s || c > k) continue outer;
            }
            for (int i = 0; c + i <= k; i++) {
                res += cnt.containsKey(i * S + s - sum) ? cnt.get(i * S + s - sum) : 0;
            }
        }
        out.println(res);
    }
}
