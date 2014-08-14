package main;

import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskN {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        for (;;) {
            String line = null;
            while ((line = in.nextLine()) != null && line.length() == 0)
                ;
            if (line == null) break;
            List<String> ls = new ArrayList<String>();
            while (line != null && line.length() != 0) {
                ls.add(line);
                line = in.nextLine();
            }
            long[][] iss = new long[ls.size()][3];
            for (int i = 0; i < ls.size(); i++) {
                String[] ss = ls.get(i).split(" ");
                iss[i][0] = Long.parseLong(ss[0]);
                iss[i][1] = Long.parseLong(ss[1]);
                iss[i][2] = Long.parseLong(ss[2]);
            }
            solve(iss);
        }
    }

    private void solve(long[][] iss) {
        int n = iss.length;
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += calc(iss[i][0], iss[i][1], iss[i][2]);
        }
        if (sum % 2 == 0) {
            out.println("no corruption");
            return ;
        }
        long yMax = 0;
        for (long[] is : iss) yMax = Math.max(yMax, is[1]);
        long l = 0, r = Math.min(Integer.MAX_VALUE * 4L, yMax + 1);
        while (l < r) {
            long m = (l + r) / 2;
            if (fit(iss, m)) l = m + 1;
            else r = m;
        }
        long c = count(iss, l);
        out.println(l + " " + c);
    }

    private long count(long[][] iss, long l) {
        long res = 0;
        for (long[] is : iss) {
            if (l <= is[1] && l >= is[0] && (l - is[0]) % is[2] == 0) res++;
        }
        return res;
    }

    private long calc(long x, long y, long z) {
        if (y < x) return 0;
        return (y - x) / z + 1;
    }

    private boolean fit(long[][] iss, long m) {
        long sum = 0;
        for (long[] is : iss) {
            sum += calc(is[0], Math.min(is[1], m), is[2]);
        }
//        Algo.debug(m, sum);
        return sum % 2 == 0;
    }
}
