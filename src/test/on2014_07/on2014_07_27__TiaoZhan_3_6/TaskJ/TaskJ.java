package test.on2014_07.on2014_07_27__TiaoZhan_3_6.TaskJ;



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
        if (n < 3) {
            while (in.hasNext()) {
                P p0 = new P(in.nextDouble(), in.nextDouble());
                P p1 = new P(in.nextDouble(), in.nextDouble());
                if (n == 0 || allOneSide(ps, p0, p1)) out.println("GOOD");
                else out.println("BAD");
            }
            return ;
        }
        ps = P.convexHull(ps);
        List<L> ls = gen(ps);
        Collections.sort(ls);
//        Algo.debug(ls);
        while (in.hasNext()) {
            P p0 = new P(in.nextDouble(), in.nextDouble());
            P p1 = new P(in.nextDouble(), in.nextDouble());
            if (allOneSide(ls, p0, p1)) out.println("GOOD");
            else out.println("BAD");
        }
    }

    private boolean allOneSide(P[] ps, P p0, P p1) {
        double r = p1.sub(p0).det(ps[0].sub(p0));
        for (P p : ps) {
            if (p1.sub(p0).det(p.sub(p0)) * r < 0) return false;
        }
        return true;
    }

    private List<L> gen(P[] ps) {
        int n = ps.length;
        int i = 0, j = 0;
        for (int k = 0; k < n; k++) {
            if (ps[k].x < ps[i].x) i = k;
            if (ps[k].x > ps[j].x) j = k;
        }
        int i0 = i, j0 = j;
        List<L> ls = new ArrayList<L>();
//        Algo.debug(ps);
        do {
//            Algo.debug(i, j);
            ls.add(new L(ps[i], ps[j]));
            if (ps[(i + 1) % n].sub(ps[i]).det(ps[(j + 1) % n].sub(ps[j])) >= 0) j = (j + 1) % n;
            else i = (i + 1) % n;
//            Algo.debug(i, j, i0, j0);
//            while (ls.size() > ps.length + 1000);
        } while (i != i0 || j != j0);
        return ls;
    }

    class L implements Comparable<L> {
        P p0;
        P p1;
        double ang;

        L(P p0, P p1) {
            this.p0 = p0;
            this.p1 = p1;
            P p0p1 = p1.sub(p0);
            ang = Math.atan2(p0p1.y, p0p1.x);
            if (ang < 0) ang += Math.PI;
        }

        @Override
        public int compareTo(L o) {
            if (ang != o.ang) return ang < o.ang ? -1 : 1;
            return 0;
        }

        @Override
        public String toString() {
            return "L{" +
                    "p0=" + p0 +
                    ", p1=" + p1 +
                    ", ang=" + ang +
                    '}';
        }
    }
    private boolean allOneSide(List<L> ls, P p0, P p1) {
        int id = Algo.lowerBound(ls, new L(p0, p0.add(p1.sub(p0).rot90())));
        for (int i = Math.max(0, id - 1000); i < Math.min(ls.size(), id + 1000); i++) {
            if (!ok(ls.get(i), p0, p1)) return false;
        }
        return true;
    }

    private boolean ok(L l, P p0, P p1) {
//        Algo.debug(l, p0, p1);
        return p1.sub(p0).det(l.p0.sub(p0)) * p1.sub(p0).det(l.p1.sub(p0)) > 0;
    }
}
