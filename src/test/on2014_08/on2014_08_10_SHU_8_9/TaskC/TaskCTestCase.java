package test.on2014_08.on2014_08_10_SHU_8_9.TaskC;



import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class TaskCTestCase {

    Random in = new Random();
    String ln = System.lineSeparator();

    @TestCase
    public Collection<Test> createTests() {
        List<Test> list = new ArrayList<Test>();
        for (int i = 0; i < 1; i++) {
            list.add(gen());
        }
        return list;
    }

    private Test gen() {
        StringBuilder input = new StringBuilder();
        StringBuilder output = new StringBuilder();
        int n = 1000000;
        int[] is = new int[n];
        for (int i = 1; i < n; i++) {
            is[i] = is[i - 1];
            if (fit(i)) is[i]++;
        }
        for (int i = 0; i < 1000; i++) {
            int k = in.nextInt(n - 1) + 1;
            input.append(k + ln);
            output.append(is[k] + ln);
        }
        return new Test(input.toString(), output.toString());
    }

    private boolean fit(int x) {
        if (x % 13 != 0) return false;
        char[] cs = Integer.toString(x).toCharArray();
        for (int i = 0; i < cs.length - 1; i++) if (cs[i] == '1' && cs[i + 1] == '3') return true;
        return false;
    }
}
