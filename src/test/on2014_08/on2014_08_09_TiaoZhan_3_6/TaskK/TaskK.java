package test.on2014_08.on2014_08_09_TiaoZhan_3_6.TaskK;



import com.shu_mj.geo.P;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskK {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        while (in.hasNext()) {
            int n = in.nextInt();
            int m = in.nextInt();
            if (n == 0 && m == 0) break;
            solve(n, m);
        }
    }

    private void solve(int n, int m) {
        P[] ps1 = new P[n];
        P[] ps2 = new P[m];
        for (int i = 0; i < n; i++) {
            ps1[i] = new P(in.nextDouble(), in.nextDouble());
        }
        for (int i = 0; i < m; i++) {
            ps2[i] = new P(in.nextDouble(), in.nextDouble());
        }
        ps1 = P.convexHull(ps1);
        ps2 = P.convexHull(ps2);
        n = ps1.length;
        m = ps2.length;
        double res = ps1[0].sub(ps2[0]).abs();
        for (int i = 0; i < n; i++) {
            res = Math.min(res, P.disConvexP(ps2, ps1[i]));
        }
        for (int i = 0; i < m; i++) {
            res = Math.min(res, P.disConvexP(ps1, ps2[i]));
        }
        out.printf("%.5f%n", res);
    }
}
