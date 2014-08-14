package test.on2014_08.on2014_08_10_BC_4.TaskB;



import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class TaskBTestCase {

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
        input.append(1 + ln);
        int n = in.nextInt(48) + 3;
        input.append(n);
        int[] is = new int[n];
        for (int i = 0; i < n; i++) {
            is[i] = in.nextInt((int) (2e9 + 1)) - (int) (1e9);
            input.append(is[i]);
            if (i < n - 1) input.append(' ');
            else input.append(ln);
        }

        return new Test(input.toString(), output.toString());
    }
}
