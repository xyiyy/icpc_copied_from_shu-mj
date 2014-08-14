package main;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class TaskBTestCase {

    Random in = new Random();
    String ln = System.lineSeparator();

    @TestCase
    public Collection<Test> createTests() {
        List<Test> list = new ArrayList<Test>();
        for (int i = 0; i < 10; i++) {
            list.add(gen());
        }
        return list;
    }

    private Test gen() {
        StringBuilder input = new StringBuilder();
        StringBuilder output = new StringBuilder();
        int n = in.nextInt(10000) + 1;
        int m = in.nextInt(10000) + 1;
        if (n > m) { int t = n; n = m; m = t; }
        int res = 0;
        for (int i = n; i <= m; i++) if (fit(i)) res++;
        input.append(n + " " + m + ln);
        input.append("0 0" + ln);
        output.append(res + ln);
        return new Test(input.toString(), output.toString());
    }

    private boolean fit(int x) {
        char[] cs = Integer.toString(x).toCharArray();
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == '4' || i < cs.length - 1 && cs[i] == '6' && cs[i + 1] == '2') return false;
        }
        return true;
    }
}
