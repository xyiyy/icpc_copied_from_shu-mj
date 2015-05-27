package main;

import com.shu_mj.tpl.Algo;
import com.shu_mj.tpl.PII;
import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class TaskITestCase {

    Random in = new Random();
    String ln = System.lineSeparator();

    @TestCase
    public Collection<Test> createTests() {
        List<Test> list = new ArrayList<Test>();
        for (int i = 0; i < 0; i++) {
            list.add(gen());
        }
        return list;
    }

    private Test gen() {
        StringBuilder input = new StringBuilder();
        StringBuilder output = new StringBuilder();
        int n = 10;
        int[] is = new int[n];
        for (int i = 0; i < n; i++) {
            is[i] = i + 1;
        }
        int k = in.nextInt(4);
        output.append(k + ln);
        for (int i = 0; i < k; i++) {
            int l = in.nextInt(n) + 1;
            int r = in.nextInt(n) + 1;
            while (l == r) r = in.nextInt(n) + 1;
            if (l > r) { int t = l; l = r; r = t; }
            output.append(l + " " + r + ln);
            Algo.reverse(is, l - 1, r);
        }
        input.append(n + ln);
        for (int i = 0; i < n; i++) {
            input.append(is[i] + " ");
        }
        input.append(ln);
        return new Test(input.toString(), output.toString());
    }
}
