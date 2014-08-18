package main;

import com.shu_mj.geo.P;
import com.shu_mj.tpl.Algo;
import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class TaskMTestCase {

    Random in = new Random();
    String ln = System.lineSeparator();

    @TestCase
    public Collection<Test> createTests() {
        List<Test> list = new ArrayList<Test>();
        for (int i = 0; i < 100; i++) {
            list.add(gen());
        }
        return list;
    }

    final int MAX_D = 1000;
    private Test gen() {
        StringBuilder input = new StringBuilder();
        StringBuilder output = new StringBuilder();
        int n = in.nextInt(100) + 4;
        P[] ps = new P[n];
        input.append(n + ln);
        for (int i = 0; i < n; i++) {
            int x = in.nextInt(MAX_D * 2) - MAX_D;
            int y = in.nextInt(MAX_D * 2) - MAX_D;
            input.append(x + " " + y + ln);
            ps[i] = new P(x, y);
        }
        input.append(0 + ln);
        double res = P.area(P.convexHull(ps));
        for (int i = 0; i < n; i++) {
            res = Math.min(res, P.area(P.convexHull(merge(Arrays.copyOf(ps, i), Arrays.copyOfRange(ps, i + 1, n)))));
        }
        output.append(String.format("%.2f%n", res));
        return new Test(input.toString(), output.toString());
    }
    public static P[] merge(P[] is, P[] js) {
        int in = is.length;
        int jn = js.length;
        P[] rs = new P[in + jn];
        System.arraycopy(is, 0, rs, 0, in);
        System.arraycopy(js, 0, rs, in, jn);
        return rs;
    }
}
