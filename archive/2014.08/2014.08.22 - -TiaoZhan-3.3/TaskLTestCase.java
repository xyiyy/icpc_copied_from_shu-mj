package main;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class TaskLTestCase {

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
        int n = 100000;
        int m = 100000;
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i <= n; i++) list.add(i);
        Collections.shuffle(list);
        input.append(n + " " + m + ln);
        for (int i : list) input.append(i + ln);
        Collections.shuffle(list);
        for (int i : list) input.append(i + ln);
        return new Test(input.toString());
    }
}
