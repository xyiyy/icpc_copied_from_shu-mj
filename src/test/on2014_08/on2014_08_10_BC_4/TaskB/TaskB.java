package test.on2014_08.on2014_08_10_BC_4.TaskB;



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
        int t = in.nextInt();
        while (t-- != 0)
            solve();
    }

    private void solve() {
        int n = in.nextInt();
        long[] is = in.nextLongArray(n);
        Arrays.sort(is);
        long[] js = new long[n - 1];
        Arrays.sort(js);
        for (int i = 0; i < n - 1; i++) js[i] = is[i + 1] - is[i];
        double ans = 0;
        for (int j = n - 2; j >= 0; j--) if (fit(is, js[j])) { ans = js[j]; break; }
//        double l = 0, r = is[n - 1] - is[0];
//        for (int i = 0; i < 100; i++) {
//            double m = (l + r) / 2;
//            if (fit(is, m)) l = m;
//            else r = m;
//        }
        out.printf("%.3f%n", ans);
    }

    final double EPS = 1e-8;
    private boolean fit(long[] is, double m) {
        int n = is.length;
        double last = is[0];
        for (int i = 1; i < n; i++) {
            if (i < n - 1 && Math.abs(is[i] + m - is[i + 1]) < EPS) {
                last = is[i + 1];
                i++;
                continue;
            }
            if (is[i] - m < last - EPS && i < n - 1 && is[i] + m > is[i + 1] + EPS) return false;
            if (is[i] - m > last - EPS) last = is[i];
            else last = is[i] + m;
        }
        return true;
    }
}
