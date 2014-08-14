package test.on2014_07.on2014_07_27__TiaoZhan_3_6.TaskJ0;





import com.shu_mj.geo.P;
import com.shu_mj.tpl.Scanner;
import java.io.PrintWriter;
import java.util.*;
import com.shu_mj.tpl.Algo;

public class TaskJ {
    Scanner in;
    PrintWriter out;

    public void solve(int testNumber, Scanner in, PrintWriter out) {
        this.in = in;
        this.out = out;
        run();
    }

    void run() {
        int n = in.nextInt();
        P[] ps = new P[n];
        for (int i = 0; i < n; i++) {
            ps[i] = new P(in.nextDouble(), in.nextDouble());
        }
        if (false && n < 3) {
            while (in.hasNext()) {
                P p0 = new P(in.nextDouble(), in.nextDouble());
                P p1 = new P(in.nextDouble(), in.nextDouble());
                if (n <= 1 || allOneSide(ps, p0, p1)) out.println("GOOD");
                else out.println("BAD");
            }
            return ;
        }
        ps = P.convexHull(ps);
        n = ps.length;
        double[] ds = new double[n];

        for (int i = 0; i < n; i++) {
            P p0 = ps[i];
            P p1 = ps[(i + 1) % n];
            ds[i] = angle(p0, p1);
        }
        while (in.hasNext()) {
            P p0 = new P(in.nextDouble(), in.nextDouble());
            P p1 = new P(in.nextDouble(), in.nextDouble());
            if (n <= 1 || !cross(ps, ds, p0, p1)) out.println("GOOD");
            else out.println("BAD");
        }
    }

    private boolean cross(P[] ps, double[] ds, P p0, P p1) {
        int i = Algo.upperBound(ds, angle(p0, p1));
        int j = Algo.upperBound(ds, angle(p1, p0));
        i %= ps.length;
        j %= ps.length;
//        Algo.debug(i, j, ps[i], ps[j], p0, p1, P.crsLS(p0, p1, ps[i], ps[j]), ps, ds, angle(p0, p1), angle(p1, p0));
        return P.crsLS(p0, p1, ps[i], ps[j]);
    }

    private boolean binaryCross(P[] ps, P p0, P p1) {
        return binary(ps, 1, ps.length - 1, p0, p1);
    }

    private boolean binary(P[] ps, int b, int e, P p0, P p1) {
        if (e - b <= 3) {
            for (int i = b; i <= e; i++) if (P.crsLS(p0, p1, ps[0], ps[i])) return true;
            return false;
        }
        int m = (b + e) / 2;
        if (P.crsLS(p0, p1, ps[0], ps[m])) return true;
        if (ps[m].sub(ps[0]).dot(p1.sub(p0)) == 0) {
            if (ps[m].sub(ps[0]).det(p0) > 0) return binary(ps, m, e, p0, p1);
            else return binary(ps, b, m, p0, p1);
        } else {
            P c = P.isLL(ps[m], ps[0], p0, p1);
            if (c.sub(ps[0]).dot(ps[m].sub(ps[0])) > 0) { // 交点在上方
                double angPPm = angle(ps[0], ps[m]);
                double angPmPms1 = angle(ps[m], ps[m - 1]);
                double angPmPma1 = angle(ps[m], ps[m + 1]);
                double angL = angle(p0, p1);
                if (angL > angPPm) {
                    if (angL < angPmPms1) return binary(ps, b, m, p0, p1);
                } else {
                    if (angL > angPmPma1) return binary(ps, m, e, p0, p1);
                }
            } else {
                double angP0Pm = angle(ps[0], ps[m]);
                double angP0P1 = angle(ps[0], ps[b]);
                double angP0Pn = angle(ps[0], ps[e]);
                double angL = angle(p0, p1);
                if (angL > angP0Pm) {
                    if (angL < angP0Pn) return binary(ps, m, e, p0, p1);
                } else {
                    if (angL > angP0P1) return binary(ps, b, m, p0, p1);
                }
            }
        }
        return false;
    }

    private double angle(P p0, P p1) {
        P p = p1.sub(p0);
        double ang = Math.atan2(p.y, p.x);
        if (ang < -Math.PI / 2 + P.EPS) ang += Math.PI * 2;
        return ang;
    }

    private boolean allOneSide(P[] ps, P p0, P p1) {
        double r = p1.sub(p0).det(ps[0].sub(p0));
        for (P p : ps) {
            if (p1.sub(p0).det(p.sub(p0)) * r < 0) return false;
        }
        return true;
    }

}
