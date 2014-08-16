package main;

import com.shu_mj.geo.P;
import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;

import java.math.MathContext;
import java.util.*;

public class TaskLTestCase {

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

    final int MAX_LEN = 100;
    private Test gen() {
        StringBuilder input = new StringBuilder();
        StringBuilder output = new StringBuilder();
        int n = in.nextInt(100) + 1;
        int[] x = new int[n];
        int[] y = new int[n];
        P[] ps = new P[n];
        for (int i = 0; i < n; i++) {
            x[i] = in.nextInt(MAX_LEN * 2) - MAX_LEN;
            y[i] = in.nextInt(MAX_LEN * 2) - MAX_LEN;
            ps[i] = new P(x[i], y[i]);
        }
        input.append(n + ln);
        for (int i = 0; i < n; i++) {
            input.append(x[i] + " " + y[i] + ln);
        }
        input.append(-1 + ln);
        double ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    ans = Math.max(ans, P.area(new P[] { ps[i], ps[j], ps[k] }));
                }
            }
        }
        output.append(String.format("%.2f%n", ans));
        return new Test(input.toString(), output.toString());
    }
}
