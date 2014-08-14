package main;



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
        int k = in.nextInt();
        if (k == 1) {
            out.println(Algo.max(is));
            return ;
        }
        long l = 0, r = Long.MAX_VALUE / 2;
        while (l < r) {
            long m = (l + r) / 2;
            if (fit(is, m, k)) r = m;
            else l = m + 1;
        }
        out.println(l);
    }

    private boolean fit(int[] is, long m, int k) {
        long cnt = 0;
        for (int i = 0; i < is.length; i++) {
            long i1 = is[i];
            i1 -= m;
            if (i1 > 0) {
                cnt += (i1 + k - 2) / (k - 1);
            }
        }
        return cnt <= m;
    }
}
