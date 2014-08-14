package main;

import com.shu_mj.ds.Hash;
import com.shu_mj.math.Num;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskE {
    Scanner in;
    PrintWriter out;
    private Hash h;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        h = new Hash(1000010);
        for (;;) {
            String line = in.nextLine();
            if (line == null || line.length() == 1 && line.charAt(0) == '.') break;
            solve(line);
        }
    }

    private void solve(String s) {
        long[] hs = h.build(s.toCharArray());
        int n = s.length();
        List<Long> fs = Num.factors(n);
        Collections.sort(fs);
        for (long len : fs) {
            if (fit(hs, len, n)) {
                out.println(n / len);
                return ;
            }
        }
    }

    private boolean fit(long[] hs, long len, int n) {
        for (int i = (int) len; i < n; i += len) {
            if (h.get(hs, 0, (int) len) != h.get(hs, i, (int) (i + len))) return false;
        }
        return true;
    }
}
