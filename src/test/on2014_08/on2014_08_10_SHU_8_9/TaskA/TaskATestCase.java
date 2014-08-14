package test.on2014_08.on2014_08_10_SHU_8_9.TaskA;



import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class TaskATestCase {

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
        int n = 1000;
        int N = 1000000;
        int[] is = new int[N];
        is[1] = 0;
        for (int i = 2; i < N; i++) {
            is[i] += is[i - 1];
            if (fit(i)) is[i]++;
        }
        input.append(n + ln);
        for (int i = 0; i < n; i++) {
            int k = in.nextInt(N - 1) + 1;
            input.append(k + ln);
            output.append(is[k] + ln);
        }
        return new Test(input.toString(), output.toString());
    }

    private boolean fit(int x) {
        String s = Integer.toString(x);
        for (int i = 0; i + 1 < s.length(); i++) {
            if (s.charAt(i) == '4' && s.charAt(i + 1) == '9') return true;
        }
        return false;
    }
}
